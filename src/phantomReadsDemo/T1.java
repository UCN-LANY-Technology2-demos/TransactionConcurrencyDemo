package phantomReadsDemo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.DataContext;

public class T1 implements Runnable {

	@Override
	public void run() {

		String sql = "SELECT Name, Age FROM Users WHERE Age BETWEEN 10 AND 30";

		try {
			Connection conn = DataContext.getConnection();

			conn.setAutoCommit(false);

			Statement selectStatement1 = conn.createStatement();

			ResultSet result1 = selectStatement1.executeQuery(sql);

			while (result1.next()) {
				String name = result1.getString(1);
				int age = result1.getInt(2);

				System.out.println(name + " is " + age + " years old");
			}
			
			Thread.sleep(2000);

			System.out.println("----");

			Statement selectStatement2 = conn.createStatement();

			ResultSet result2 = selectStatement2.executeQuery(sql);

			while (result2.next()) {
				String name = result2.getString(1);
				int age = result2.getInt(2);

				System.out.println(name + " is " + age + " years old");
			}

			conn.commit();
			
		} catch (SQLException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}