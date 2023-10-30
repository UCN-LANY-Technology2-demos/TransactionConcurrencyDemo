package lostUpdateDemo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.Account;
import database.DataContext;

public class T1 extends Account implements Runnable {

	
	private void printInfo(int id, float balance) {

		System.out.println(Thread.currentThread().getName() + " - Account " + id + " balance: " + balance);
	}

	@Override
	public void run() {

		int id = 2;
		Connection conn;
		try {
			conn = DataContext.getConnection();

			DataContext.printSessionInfo(conn);

			float balance = getBalance(conn, id);

			printInfo(id, balance);

			setBalance(conn, id, balance * 1.1f);

			balance = getBalance(conn, id);

			printInfo(id, balance);

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} // add a isolationlevel before running in a transaction

	}
}
