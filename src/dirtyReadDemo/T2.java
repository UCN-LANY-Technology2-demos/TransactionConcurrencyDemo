package dirtyReadDemo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.DataContext;

public class T2 implements Runnable {

	@Override
	public void run() {

		String sql = "SELECT Age FROM Users WHERE Id = 1";

		try {
			Thread.sleep(1000);

			Connection conn = DataContext.getConnection(Connection.TRANSACTION_READ_COMMITTED);

			Statement selectStatement = conn.createStatement();

			ResultSet result = selectStatement.executeQuery(sql);

			if (result.next()) {
				int age = result.getInt(1);
				
				System.out.println("Age is "+ age);
			}

		} catch (SQLException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
