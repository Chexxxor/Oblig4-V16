package task3;

public class Account extends Thread {
	private Bank b;
	private int id;
	private int amount;
	boolean running;

	public Account(Bank b, int amount, int id){
		this.id = id;
		this.b = b;
		this.amount = amount;
		running = true;
	}
	
	public int accountId(){
		return id;
	}
	public int saldo(){
		return amount;
	}
	
	public void deposit(int amount){
		this.amount += amount;
	}
	public void withdraw(int amount){
		this.amount -= amount;
	}
	
	public void kill(){
		running = false;
	}
	@Override
	public void run(){
		while(running){
			Account toAccount = this;
			while(toAccount == this){
				toAccount = b.getRandom();
			}
			try {
				sleep((long) (Math.random() * Bank.BREAK_TIME));
				b.transfer(this, toAccount, (int) (Math.random() * Bank.INITIAL_BALANCE));
			} catch (InterruptedException e) {}
		}
	}

}
