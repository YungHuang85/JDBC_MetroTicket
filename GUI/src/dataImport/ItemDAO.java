package dataImport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import guiExe.Item;
import utils.JDBCutils;

//目的是將 Item 物件屬性值存入SQL表格
public class ItemDAO {
	public void saveItem(Item item) {
		PreparedStatement preparedStatement = null;
		
		//定義SQL
		String sql = "INSERT INTO Ticket_price (起站, 訖站, 全票票價, 敬老卡愛心卡愛心陪伴卡及新北市兒童優惠票價, 臺北市兒童優惠票價, 距離, UpdateTime)"
				+ "VALUES(?,?,?,?,?,?,?)";
		
		//資料庫連線
		Connection connection = JDBCutils.getConection();	
		try {
			//prepareStatement連接SQL, 可防止SQL Injection
			preparedStatement = connection.prepareStatement(sql);
			//將Item屬性設置到對應的header
			preparedStatement.setString(1, item.get起站());
			preparedStatement.setString(2, item.get訖站());
			preparedStatement.setInt(3, item.get全票票價());
			preparedStatement.setInt(4, item.get敬老卡愛心卡愛心陪伴卡及新北市兒童優惠票價());
			preparedStatement.setInt(5, item.get臺北市兒童優惠票價());
			preparedStatement.setDouble(6, item.get距離());
			
			//DateTime屬性設置
			LocalDateTime now = LocalDateTime.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	        String formattedDateTime = now.format(formatter);
	        preparedStatement.setString(7, formattedDateTime);
	        
	        //執行SQL
			preparedStatement.execute();
			System.out.println("新增成功"+formattedDateTime);
						
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCutils.closeResource(connection, preparedStatement);
		}
	}
}

