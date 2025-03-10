package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCutils {
	//取得連線方法	
	public static Connection getConection() {
		//加載驅動
		Connection connection=null;
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			//設定連字串 
			//外部讀取檔案
			String url = "jdbc:sqlserver://localhost:1433;DatabaseName=JDBC_crud;encrypt=false"; // 替換為你的連線字串
            String user = "user"; // 直接在此輸入使用者名稱
            String password = "user"; // 直接在此輸入密碼
			//設定連線物件
			connection = DriverManager.getConnection(url, user, password);
			boolean status = !connection.isClosed();
			System.out.println("連線狀態:"+status);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	//關連線方法	
	/*
	 *  overload 多載
	 *  定義同名方法	
	 *  	參數個數或型別不同
	 */
	public static void closeResource(Connection connection) {
		if(connection !=null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void closeResource(Connection connection,Statement statement) {
		try {
			if(connection !=null) {
				connection.close();
			}if(statement !=null) {
				statement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void closeResource(Connection connection,Statement statement,ResultSet resultSet) {
		try {
			if(connection!=null) {
				connection.close();
			}if(statement!=null) {
				statement.close();
			}if (resultSet!=null) {
				resultSet.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
}