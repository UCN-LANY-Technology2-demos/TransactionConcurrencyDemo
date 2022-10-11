package nonRepeatableReadDemo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import database.DataContext;

public class T2 implements Runnable {

	@Override
	public void run() {

		String sql = "UPDATE Users SET Age = 21 WHERE Id = 1";

		try {
			
			Thread.sleep(1000);
			
			Connection conn = DataContext.getConnection(Connection.TRANSACTION_NONE);

			conn.setAutoCommit(false);

			Statement updateStatement = conn.createStatement();

			updateStatement.execute(sql);

			conn.commit();

		} catch (SQLException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
