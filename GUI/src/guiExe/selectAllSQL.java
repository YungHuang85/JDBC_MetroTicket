package guiExe;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import utils.JDBCutils;


public class selectAllSQL {
	String sql = "SELECT * FROM Ticket_price";
	Connection connection = JDBCutils.getConection();
	ResultSet resultSet = null;
	public List<Item> selectAllSQL() {		
		PreparedStatement preparedStatement = null;	
		ArrayList<Item> itemList = new ArrayList<Item>();
				
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				String 起站 = resultSet.getString("起站");
				String 訖站 = resultSet.getString("訖站");
				int 全票票價 = resultSet.getInt("全票票價");
				int 敬老卡愛心卡愛心陪伴卡及新北市兒童優惠票價 = resultSet.getInt("敬老卡愛心卡愛心陪伴卡及新北市兒童優惠票價");
				int 臺北市兒童優惠票價 = resultSet.getInt("臺北市兒童優惠票價");
				Double 距離 = resultSet.getDouble("距離");
				Timestamp timestamp = resultSet.getTimestamp("updateTime");
				//DateTime
				LocalDateTime updateTime = timestamp != null ? timestamp.toLocalDateTime():null;
				Item item = new Item(起站,訖站,全票票價,敬老卡愛心卡愛心陪伴卡及新北市兒童優惠票價,臺北市兒童優惠票價,距離,updateTime);
				itemList.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCutils.closeResource(connection, preparedStatement, resultSet);
		}
		return itemList;	
	}
	
	public void exportCSV(String csvPath) {
		String csvFilePath = csvPath;
		try (Statement statement = connection.createStatement();
	         ResultSet resultSet = statement.executeQuery("SELECT * FROM Ticket_price");
	         FileOutputStream fos = new FileOutputStream(csvFilePath);
			 OutputStreamWriter osw = new OutputStreamWriter(fos, Charset.forName("MS950"));
	         PrintWriter writer = new PrintWriter(osw)) {
            // 獲取列的元數據
            int columnCount = resultSet.getMetaData().getColumnCount();
            // 寫入CSV的標頭行
            for (int i = 1; i <= columnCount; i++) {
                writer.print(resultSet.getMetaData().getColumnName(i));
                if (i < columnCount) writer.print(",");
            }
            writer.println();
            // 寫入CSV的數據行
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    writer.print(resultSet.getString(i));
                    if (i < columnCount) writer.print(",");
                }
                writer.println();	
            } System.out.println("Data has been exported to " + csvFilePath);
        	} catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
	
}
