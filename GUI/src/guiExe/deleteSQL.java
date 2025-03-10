package guiExe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import utils.JDBCutils;



public class deleteSQL {
	public void deleteByProductId(String startStation, String finalStation) {
		String sql = "DELETE FROM Ticket_price WHERE 起站 = ? AND 訖站 = ?";
		Connection connection = JDBCutils.getConection();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, startStation);
			preparedStatement.setString(2, finalStation);
			preparedStatement.execute();
			System.out.printf("刪除起站:%s%n刪除訖站:%s", startStation, finalStation);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCutils.closeResource(connection, preparedStatement);
		}
	}
}
