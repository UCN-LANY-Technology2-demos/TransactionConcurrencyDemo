package inconsistentRetrievalDemo;

import java.sql.Connection;
import java.sql.SQLException;
import database.DataContext;

public class T1 extends InconsistentRetrieval implements Runnable {

	
	@Override
	public void run() {

		int a1 = 1;
		int a2 = 2;

		try {
			
			System.out.println(Thread.currentThread().getName() + " start");
			
			Connection conn = DataContext.getConnection(Connection.TRANSACTION_SERIALIZABLE); // add a isolationlevel before running in a transaction
			
			// set autocommit to false
			conn.setAutoCommit(false);
			
			withdraw(conn, a2, 100);
			
			Thread.sleep(2000);
			
			deposit(conn, a1, 100);
			
			// commit transaction here
			conn.commit();

			System.out.println(Thread.currentThread().getName() + " finish");

		} catch (SQLException | InterruptedException e) {
			
			System.out.println(Thread.currentThread().getName() + " Failed!");
			System.out.println(e.getMessage());			
		}
		
	}

}
