package task3;

public class Bank {
	public static final int NACCOUNTS = 10;
	public static final int INITIAL_BALANCE = 10000;
	public static final int NTEST = 50;
	public static final int BREAK_TIME = 50000;
	private Account[] accounts = new Account[NACCOUNTS];
	private int nTransactions;
	
	public static void main(String[] args) throws InterruptedException{
		Bank b = new Bank();
		b.run();
	}
	
	public Bank(){
		nTransactions = 0;
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
	
	public void transfer(Account fromAccount, Account toAccount, int amount) throws InterruptedException{
		System.out.println(nTransactions + ": Initializing transfer of £" + amount +
				" from #" + fromAccount.accountId() +
				" to #" + toAccount.accountId());
		synchronized (fromAccount) { //Withdraw
			while(fromAccount.saldo() < amount){
				System.out.println("Insufficent funds on #" + fromAccount.accountId() + ", need £" + (amount - fromAccount.saldo()) + " more");
				fromAccount.wait();
				System.out.println("Transfer recieved on #" + fromAccount.accountId());
				if(fromAccount.saldo() >= amount)
					System.out.println("Continuing transaction from #" + fromAccount.accountId() + " to #" + toAccount.accountId());
				else
					System.out.print("Still ");
			}
			fromAccount.withdraw(amount);
		}
		//Money will temporarily disappear from one account before appearing on another
		//this will sometimes seem like "missing money" during the tests  
		//System.out.println(nTransactions + ": Credit on hold");
		synchronized (toAccount) { //Deposit
			toAccount.deposit(amount);
			toAccount.notify();
		}
		System.out.println(nTransactions + ": Transfer complete");
		nTransactions++;
		if(nTransactions % NTEST == 0){
			int sum = 0;
			for(int i = 0; i < NACCOUNTS; i++){
				sum += accounts[i].saldo();
			}
			System.out.println("Sum: " + sum);
		}
	}
	public void run() throws InterruptedException{
		for(int i = 0; i < NACCOUNTS; i++){
			accounts[i].start();
		}
	}
}
