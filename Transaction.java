import java.util.Date;


/*
 	amount, date/time of transaction, memo of transaction, which account the transaction occurs in
 * */
public class Transaction {
	
	private double amount;
	
	private Date timestamp;
	
	private String memo;
	
	private Account inAccount;
	
	// create a new transaction
	public Transaction(double amount, Account inAccount) {
		
		this.amount = amount;
		this.inAccount = inAccount;
		this.timestamp = new Date();
		this.memo = "";
	}
	

	public Transaction(double amount, String memo, Account inAccount) {
		// call constructor 
		this(amount, inAccount);
		
		// set memo
		this.memo = memo;
		
	}
	
	
	// get amount of transaction
	
	public double getAmount() {
		return this.amount;
	}
	
	// get summary of transaction
	public String getSummaryLine() {
		if (this.amount >= 0) {
			return String.format("%s : $%.02f : %s", this.timestamp.toString(), 
					this.amount, this.memo);
		} else {
			return String.format("%s : $(%.02f) : %s", 
					this.timestamp.toString(), 
					-this.amount, this.memo);
		}
	}
	
}
