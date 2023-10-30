package inconsistentRetrievalDemo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.Account;
import database.DataContext;

public class T1 extends Account implements Runnable {

	@Override
	public void run() {

		int a1 = 1;
		int a2 = 2;

		try {
			
			System.out.println(Thread.currentThread().getName() + " start");
				
			Connection conn = DataContext.getConnection(); 
			
			float balance = getBalance(conn, 2);
			
			setBalance(conn, 1, balance + 100);
			
			System.out.println(Thread.currentThread().getName() + " finish");

		} catch (SQLException e) {
			
			System.out.println(Thread.currentThread().getName() + " Failed!");
			System.out.println(e.getMessage());			
		}		
	}
}
