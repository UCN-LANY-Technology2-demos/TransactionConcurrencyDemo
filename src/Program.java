
public class Program {
	public static void main(String[] args) {

		OptimisticConcurrencyDemo demo = new OptimisticConcurrencyDemo(); 
		//PessimisticConcurrencyDemo demo = new PessimisticConcurrencyDemo();

		Thread t1 = new Thread("T1") {
			
			public void run() {
				try {
					
//					demo.updateCustomerOrderCount(1);
					
					demo.updateCustomer(1, "Klaus");
					
				} catch (Exception e) {

					e.printStackTrace();
				}
			}
		};
		
		Thread t2 = new Thread("T2") {
			
			public void run() {
				try {
					
//					demo.createOrder(1, 100);
						
					demo.updateCustomer(1, "Peter");
					
				} catch (Exception e) {

					e.printStackTrace();
				}
			}
		};
		
		t1.start();
		t2.start();
	}
}
