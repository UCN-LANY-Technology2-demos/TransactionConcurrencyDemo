package optimisticConcurrencyDemo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.DataContext;

public class T1 implements Runnable {

	public Account getAccount(Connection conn, int id) throws SQLException {

		String sql = "SELECT Balance, Version FROM Account WHERE Id = ?";
		Account result = null;

		PreparedStatement getBalanceStatement = conn.prepareStatement(sql);
		getBalanceStatement.setInt(1, id);

		ResultSet records = getBalanceStatement.executeQuery();

		if (records.next()) {
			float balance = records.getFloat(1);
			byte[] version = records.getBytes(2);

			result = new Account(id, version);
			result.setBalance(balance);
		}
		return result;
	}

	public boolean addInterest(Connection conn, Account account) throws SQLException {

		String sql = "UPDATE Account SET Balance = ? WHERE Id = ? AND Version = ?";

		PreparedStatement updateBalanceStatement = conn.prepareStatement(sql);
		updateBalanceStatement.setInt(2, account.getId());
		updateBalanceStatement.setFloat(1, account.getBalance());
		updateBalanceStatement.setBytes(3, account.getVersion());

		return updateBalanceStatement.executeUpdate() == 1;
	}

	private void printInfo(Account account) {
		System.out.println(account);
	}

	@Override
	public void run() {

		try {
			Connection conn = DataContext.getConnection(); // add a isolationlevel before running in a transaction

			Account account = getAccount(conn, 2);

			printInfo(account);

			account.setBalance(account.getBalance() * 1.1f);

			if (addInterest(conn, account)) {

				account = getAccount(conn, 2);

				printInfo(account);
				
			} else {
				System.out.println("Something went wrong! Try again...");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
