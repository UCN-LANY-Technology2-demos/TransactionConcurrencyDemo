import java.sql.*;

import model.Customer;

public class OptimisticConcurrencyDemo {

	public void updateCustomer(int customerId, String name) throws Exception {
		
		String updateCustomerSql = "UPDATE Customers SET Name = ? WHERE Id = ?";
		
		Connection conn = Database.getConnection(Connection.TRANSACTION_NONE);
		
		try {
			
			Customer customer = getCustomer(customerId);
						
			System.out.println(Thread.currentThread().getName() +" - Updating: "+ customer +" to "+ name);

			PreparedStatement updateCustomer = conn.prepareStatement(updateCustomerSql);
			updateCustomer.setString(1, name);
			updateCustomer.setInt(2, customerId);			
			
			int rowsUpdated = updateCustomer.executeUpdate();				
			
			System.out.println(Thread.currentThread().getName() +" - "+ rowsUpdated +" rows updated to: "+ getCustomer(customerId));
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
	
	public Customer getCustomer(int customerId) {
		
		String selectCustomerSql = "SELECT * FROM Customers WHERE Id = ?";

		Connection conn = Database.getConnection(Connection.TRANSACTION_NONE);
		
		try {
			
			PreparedStatement selectCustomer = conn.prepareStatement(selectCustomerSql);
			selectCustomer.setInt(1, customerId);
			
			ResultSet rs = selectCustomer.executeQuery();
			
			if(rs.next()) {
				
				return new Customer(
						rs.getInt(1), // CustomerId 
						rs.getString(2), // Name
						rs.getString(3), // LatestOrderStatus
						rs.getBytes(5) // Rowversion
						);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return null;		
	}
}