import java.util.ArrayList;

public class Account {
	
	/*
		name, uuid and user of account, list of transactions 
	
	 */
	
	
	private String name;
	
	
	private String uuid;
	
	private User holder;
	
	private ArrayList<Transaction> transactions;
	
	
	
	public Account(String name, User holder, Bank theBank){
		
		// account name and holder of account
		
		this.name = name;
		this.holder = holder;
		
		// get account uuid
		this.uuid = theBank.getNewAccountUUID();
		
		
		// initialize transaction
		
		this.transactions = new ArrayList<Transaction>();
		
	
		
		
	}
	
	public String getUUID() {
		return this.uuid;
	}
	
	//get summary for account
	public String getSummaryLine() {
				// retrieve acct balance
				
		double balance = this.getBalance();
				
				// if account is overdrawn, format summary line 
		if (balance >= 0) {
			return String.format("%s : $%.02f : %s", 
					this.uuid, balance, this.name);
				}
			else {
				return String.format("%s : ($%.02f) : %s", 
						this.uuid, balance, this.name);
				}
			}
			
	public double getBalance() {
		double balance = 0;
		for (Transaction t : this.transactions) {
				balance += t.getAmount();
					
		}
		return balance;
			}
	
	
	// print transaction history
	public void printTransactionHistory() {
		System.out.printf("\nTransaction history for account %s\n", this.uuid);
		for (int t = this.transactions.size()-1; t >= 0; t--) {
			System.out.println(this.transactions.get(t).getSummaryLine());
			
		}
		
		System.out.println();
	}
	
	public void addTransaction(double amount, String memo) {
		// create new transaction and add to list
		Transaction newTransaction = new Transaction(amount, memo, this);
		this.transactions.add(newTransaction);
	}
	
			
			
	

}
