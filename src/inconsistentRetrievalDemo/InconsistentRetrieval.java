package inconsistentRetrievalDemo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InconsistentRetrieval {

	protected void withdraw(Connection conn, int id, float amount) throws SQLException {

		String sql = "UPDATE Account SET Balance = Balance - ? WHERE Id = ?";

		PreparedStatement updateBalanceStatement = conn.prepareStatement(sql);
		updateBalanceStatement.setInt(2, id);
		updateBalanceStatement.setFloat(1, amount);

		updateBalanceStatement.executeUpdate();
	}

	protected void deposit(Connection conn, int id, float amount) throws SQLException {

		String sql = "UPDATE Account SET Balance = Balance + ? WHERE Id = ?";

		PreparedStatement updateBalanceStatement = conn.prepareStatement(sql);
		updateBalanceStatement.setInt(2, id);
		updateBalanceStatement.setFloat(1, amount);

		updateBalanceStatement.executeUpdate();
	}

	protected float total(Connection conn) throws SQLException {

		String sql = "SELECT SUM(Balance) FROM Account";

		Statement getTotalStatement = conn.createStatement();

		ResultSet result = getTotalStatement.executeQuery(sql);

		if (result.next()) {
			return result.getFloat(1);
		}
		return 0;
	}
}
