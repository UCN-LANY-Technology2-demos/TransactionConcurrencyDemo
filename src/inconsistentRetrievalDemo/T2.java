package inconsistentRetrievalDemo;

import java.sql.Connection;
import java.sql.SQLException;
import database.DataContext;

public class T2 extends InconsistentRetrieval implements Runnable {

		
	@Override
	public void run() {

		try {
			
			System.out.println(Thread.currentThread().getName() + " start");
			
			Connection conn = DataContext.getConnection(Connection.TRANSACTION_NONE); 
			conn.setAutoCommit(false);
			
			float total = total(conn);
			
			System.out.println(Thread.currentThread().getName() + " - total: " + total);
			
			System.out.println(Thread.currentThread().getName() + " finish");
			
			conn.commit();
			

		} catch (SQLException e) {
			
			System.out.println(Thread.currentThread().getName() + " Failed!");
			System.out.println(e.getMessage());			
		}
		
	}

}
