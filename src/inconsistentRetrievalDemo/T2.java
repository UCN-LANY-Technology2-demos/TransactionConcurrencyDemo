package inconsistentRetrievalDemo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.Account;
import database.DataContext;

public class T2 extends Account implements Runnable {

		
	@Override
	public void run() {

		try {
			
			System.out.println(Thread.currentThread().getName() + " start");
			
			Connection conn = DataContext.getConnection();
			
			float balance1 = getBalance(conn, 1);
			
			System.out.println(Thread.currentThread().getName() + " - balance: " + balance1);
			
			float balance2 = getBalance(conn, 1);
			
			System.out.println(Thread.currentThread().getName() + " - balance: " + balance2);
			
			System.out.println(Thread.currentThread().getName() + " finish");
			
			
		} catch (SQLException e) {
			
			System.out.println(Thread.currentThread().getName() + " Failed!");
			System.out.println(e.getMessage());			
		}
		
	}

}
