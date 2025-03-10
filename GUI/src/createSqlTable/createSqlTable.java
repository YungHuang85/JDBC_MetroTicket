package createSqlTable;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import utils.JDBCutils;

public class createSqlTable {

    public static void main(String[] args) {
        Statement statement = null;
        Connection connection = null;
        
        try {   
        	//sql連線
        	connection = JDBCutils.getConection();
        	
        	//創建Statement 對象用於執行 SQL 語句
            statement = connection.createStatement();
            
            //sql語法
            String sql = "CREATE TABLE Ticket_price (ID INT IDENTITY PRIMARY KEY,起站 NVARCHAR(50),訖站 NVARCHAR(50),"
            		+ "全票票價 INT,敬老卡愛心卡愛心陪伴卡及新北市兒童優惠票價 INT,臺北市兒童優惠票價 INT,距離 FLOAT(2), UpdateTime DATETIME2(0))";
                   
            //執行sql
            statement.executeUpdate(sql);
            System.out.println("建立成功");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	JDBCutils.closeResource(connection, statement);
        }
    }
}