package phantomReadsDemo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import database.DataContext;

public class T2 implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO Users (Id, Name, Age) VALUES (3, 'Chalie', 27)";

		try {
			Thread.sleep(1000);

			Connection conn = DataContext.getConnection();

			conn.setAutoCommit(false);

			Statement insertStatement = conn.createStatement();

			insertStatement.execute(sql);

			conn.commit();
			
		} catch (InterruptedException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
