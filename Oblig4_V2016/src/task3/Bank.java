package task3;

public class Bank {
	public static final int NACCOUNTS = 10;
	public static final int INITIAL_BALANCE = 10000;
	private Account[] accounts = new Account[NACCOUNTS];
	
	public static void main(String[] args){
		new Bank();
	}
	
	public Bank(){
		for(int i = 0; i < NACCOUNTS; i++){
			accounts[i] = new Account(this, INITIAL_BALANCE, i);
			accounts[i].setPriority(Thread.NORM_PRIORITY + i % 2);
		}
	}
	
	public Account getAccount(int i) throws ArrayIndexOutOfBoundsException {
		return accounts[i];
	}
	
	public Account getRandom(){
		return accounts[(int) (Math.random() * NACCOUNTS)];
	}
	
	public int size(){
		return NACCOUNTS;
	}
	
	public void transfer(Account fromAccount, Account toAccount, int amount){
		System.out.println("Initializing transfer of " + amount +
				" from #" + fromAccount.accountId() +
				" to #" + toAccount.accountId());
		while(fromAccount.saldo() < amount){
			System.out.println("Insufficent funds on #" + fromAccount.accountId());
			try {
				wait(1000);
			} catch (InterruptedException e) {}
		}
		fromAccount.withdraw(amount);
		System.out.println("Credit on hold");
		toAccount.deposit(amount);
		System.out.println("Transfer complete");
		//toAccount.notify();
	}

}
