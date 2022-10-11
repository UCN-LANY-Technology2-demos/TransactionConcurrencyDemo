package dirtyReadDemo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import database.DataContext;

public class T1 implements Runnable {

	@Override
	public void run() {

		String sql = "UPDATE Users SET Age = 21 WHERE Id = 1";
		
		try {
			Connection conn = DataContext.getConnection(Connection.TRANSACTION_READ_UNCOMMITTED);
			
			conn.setAutoCommit(false);
			
			Statement updateStatement = conn.createStatement();
			
			updateStatement.execute(sql);
			
			Thread.sleep(3000);
			
			conn.rollback();
			
		} catch (SQLException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
