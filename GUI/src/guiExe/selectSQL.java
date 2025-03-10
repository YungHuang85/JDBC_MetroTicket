package guiExe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import utils.JDBCutils;


public class selectSQL {
	public Item readItem(String startStation, String finalStation) {
		PreparedStatement preparedStatement = null;
		String sql = "SELECT * FROM Ticket_price WHERE 起站 = ? and 訖站 = ?";
		Connection connection = JDBCutils.getConection();		
		Item item = null;
		ResultSet resultSet = null;
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, startStation);
			preparedStatement.setString(2, finalStation);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			
			
			String 起站 = resultSet.getString("起站");
			String 訖站 = resultSet.getString("訖站");
			int 全票票價 = resultSet.getInt("全票票價");
			int 敬老卡愛心卡愛心陪伴卡及新北市兒童優惠票價 = resultSet.getInt("敬老卡愛心卡愛心陪伴卡及新北市兒童優惠票價");
			int 臺北市兒童優惠票價 = resultSet.getInt("臺北市兒童優惠票價");
			Double 距離 = resultSet.getDouble("距離");
			Timestamp timestamp = resultSet.getTimestamp("updateTime");
			LocalDateTime updateTime = timestamp != null ? timestamp.toLocalDateTime():null;
			
			item = new Item(起站,訖站,全票票價,敬老卡愛心卡愛心陪伴卡及新北市兒童優惠票價,臺北市兒童優惠票價,距離, updateTime);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCutils.closeResource(connection, preparedStatement, resultSet);
		}
		return item;
	}
}
