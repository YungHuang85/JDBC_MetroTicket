package guiExe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import utils.JDBCutils;

public class updateSQL {
    public boolean updateItem(Item item) {
        String sql = "UPDATE Ticket_price SET 全票票價=?, 敬老卡愛心卡愛心陪伴卡及新北市兒童優惠票價=?, 臺北市兒童優惠票價=? WHERE 起站=? AND 訖站=?";
        Connection connection = JDBCutils.getConection();
        PreparedStatement preparedStatement = null;
        boolean isUpdated = false;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, item.get全票票價());
            preparedStatement.setInt(2, item.get敬老卡愛心卡愛心陪伴卡及新北市兒童優惠票價());
            preparedStatement.setInt(3, item.get臺北市兒童優惠票價());
            preparedStatement.setString(4, item.get起站());
            preparedStatement.setString(5, item.get訖站());

            int rowsAffected = preparedStatement.executeUpdate();
            isUpdated = rowsAffected > 0;

            System.out.printf("更新資料為: %s,%s,%d,%d,%d%n",item.get起站(), item.get訖站(),item.get全票票價(), item.get敬老卡愛心卡愛心陪伴卡及新北市兒童優惠票價(),item.get臺北市兒童優惠票價());
            
        } catch (SQLException e) {
            System.err.printf("更新失敗: %s, %s, %d, %d, %d%n",item.get起站(), item.get訖站(),item.get全票票價(), item.get敬老卡愛心卡愛心陪伴卡及新北市兒童優惠票價(),item.get臺北市兒童優惠票價());
            e.printStackTrace();
        } finally {
            JDBCutils.closeResource(connection, preparedStatement);
        }
        return isUpdated;
    }
}
