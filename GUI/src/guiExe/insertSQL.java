package guiExe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import utils.JDBCutils;


public class insertSQL {
	
	//insert
	public void saveItem(Item item) {
		PreparedStatement preparedStatement = null;
		String sql = "INSERT INTO Ticket_price (起站, 訖站, 全票票價, 敬老卡愛心卡愛心陪伴卡及新北市兒童優惠票價, 臺北市兒童優惠票價, 距離,UpdateTime)"
				+ "VALUES(?,?,?,?,?,?, GETDATE())";
		Connection connection = JDBCutils.getConection();		
	
	
	try {
		preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, item.get起站());
        preparedStatement.setString(2, item.get訖站());
        preparedStatement.setInt(3, item.get全票票價());
        preparedStatement.setInt(4, item.get敬老卡愛心卡愛心陪伴卡及新北市兒童優惠票價());
        preparedStatement.setInt(5, item.get臺北市兒童優惠票價());
        preparedStatement.setDouble(6, item.get距離());
        
        preparedStatement.executeUpdate();
     
    } catch (SQLException e) {
        System.err.println("SQL Exception occurred while inserting data: " + e.getMessage());
        e.printStackTrace();
    } finally {
        JDBCutils.closeResource(connection, preparedStatement);
    	}
	}
}