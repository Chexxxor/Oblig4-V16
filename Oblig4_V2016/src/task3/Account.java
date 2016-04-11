package task3;

public class Account extends Thread {
	private Bank b;
	private int id;
	private int amount;

	public Account(Bank b, int amount, int id){
		this.id = id;
		this.b = b;
		this.amount = amount;
		this.start();
	}
	
	public int accountId(){
		return id;
	}
	
	public int saldo(){
		return amount;
	}
	
	public synchronized void deposit(int amount){
		amount++;
	}

	public synchronized void withdraw(int amount){
		amount--;
	}
	
	@Override
	public void run(){
		while(true){
			synchronized (this) {
				Account toAccount = this;
				while(toAccount == this){
					toAccount = b.getRandom();
				}
				b.transfer(this, toAccount, (int) (Math.random() * Bank.INITIAL_BALANCE));
				try {
					wait((long) (Math.random() * 5000));
				} catch (InterruptedException e) {}
			}
		}
	}

}
