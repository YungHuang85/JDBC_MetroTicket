package guiExe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import utils.JDBCutils;

public class deleteAll {
	public void deleteAllData() {
		String sql = "DELETE FROM Ticket_price";
		Connection connection = JDBCutils.getConection();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();
			System.out.printf("全部刪除成功");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCutils.closeResource(connection, preparedStatement);
		}
	}
}
