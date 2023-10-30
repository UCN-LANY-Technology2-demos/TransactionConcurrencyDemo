package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Account {

	public float getBalance(Connection conn, int id) throws SQLException {

		String sql = "SELECT Balance FROM Account WHERE Id = ?";

		PreparedStatement getBalanceStatement = conn.prepareStatement(sql);
		getBalanceStatement.setInt(1, id);

		ResultSet result = getBalanceStatement.executeQuery();

		if (result.next()) {
			return result.getFloat(1);
		}
		return 0;
	}

	public void setBalance(Connection conn, int id, float amount) throws SQLException {

		String sql = "UPDATE Account SET Balance = ? WHERE Id = ?";

		PreparedStatement updateBalanceStatement = conn.prepareStatement(sql);
		updateBalanceStatement.setInt(2, id);
		updateBalanceStatement.setFloat(1, amount);

		updateBalanceStatement.executeUpdate();
	}
}
