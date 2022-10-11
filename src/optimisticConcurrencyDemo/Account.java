package optimisticConcurrencyDemo;

public class Account {

	private int id;
	private byte[] version;
	private float balance;

	public Account(int id, byte[] version) {
		super();
		this.id = id;
		this.version = version;
	}

	public int getId() {
		return id;
	}

	public byte[] getVersion() {
		return version;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return Thread.currentThread().getName() + " : Account " + id + " balance: " + balance;
	}
}
