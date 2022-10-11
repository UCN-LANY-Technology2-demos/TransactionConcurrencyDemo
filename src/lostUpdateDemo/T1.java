package lostUpdateDemo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.DataContext;


public class T1 implements Runnable {

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

	public void addInterest(Connection conn, int id, float amount) throws SQLException {

		String sql = "UPDATE Account SET Balance = ? WHERE Id = ?";

		PreparedStatement updateBalanceStatement = conn.prepareStatement(sql);
		updateBalanceStatement.setInt(2, id);
		updateBalanceStatement.setFloat(1, amount);

		updateBalanceStatement.executeUpdate();
	}

	private void printInfo(int id, float balance) {

		System.out.println(Thread.currentThread().getName() + " - Account " + id + " balance: " + balance);
	}

	@Override
	public void run() {

		int id = 2;

		try {
			Connection conn = DataContext.getConnection(); // add a isolationlevel before running in a transaction
			
			// set autocommit to false
			
			float balance = getBalance(conn, id);

			printInfo(id, balance);

			addInterest(conn, id, balance * 1.1f);

			balance = getBalance(conn, id);

			printInfo(id, balance);
			
			// commit transaction here

		} catch (SQLException e) {
			
			System.out.println(Thread.currentThread().getName() + " Failed!");
			System.out.println(e.getMessage());			
		}

	}
}
