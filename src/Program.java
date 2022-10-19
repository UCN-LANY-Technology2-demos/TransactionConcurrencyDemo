import database.DataContext;

public class Program {
	public static void main(String[] args) {

//		Thread transaction1 = new Thread(new lostUpdateDemo.T1());
//		Thread transaction2 = new Thread(new lostUpdateDemo.T2());
		
//		Thread transaction1 = new Thread(new inconsistentRetrievalDemo.T1());
//		Thread transaction2 = new Thread(new inconsistentRetrievalDemo.T2());
		
//		Thread transaction1 = new Thread(new dirtyReadDemo.T1());
//		Thread transaction2 = new Thread(new dirtyReadDemo.T2());

//		Thread transaction1 = new Thread(new nonRepeatableReadDemo.T1());
//		Thread transaction2 = new Thread(new nonRepeatableReadDemo.T2());

//		Thread transaction1 = new Thread(new phantomReadsDemo.T1());
//		Thread transaction2 = new Thread(new phantomReadsDemo.T2());

		Thread transaction1 = new Thread(new optimisticConcurrencyDemo.T1());
		Thread transaction2 = new Thread(new optimisticConcurrencyDemo.T1());

		transaction1.start();
		transaction2.start();

		
		try {
			
			transaction1.join();
			transaction2.join();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		DataContext.resetDatabase();
	}
}
