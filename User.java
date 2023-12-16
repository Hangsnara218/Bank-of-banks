import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {


		/*/
		 name, uuid, MS5 hashed pin and list of accounts of the user 
		 */
		private String firstName;
		
		private String lastName;
		
		private String uuid;
		
		private byte pinHash[];
		
		private ArrayList<Account> accounts;
		
		
		public User(String firstName, String lastName, String pin, Bank theBank){
		
			// set user name
			this.firstName = firstName;
			this.lastName = lastName;
			
			// pin MD5 hash
			
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				this.pinHash = md.digest(pin.getBytes());
			} catch (NoSuchAlgorithmException e) {
				System.err.println("Error, caught no such algorithmException");
				e.printStackTrace();
				System.exit(1);
			}
			
			// get new uuid
			
			this.uuid = theBank.getNewUserUUID();
			
			// empty list of accounts
			
			this.accounts = new ArrayList<Account>();
			
			// log message
			
			System.out.printf("New user %s, %s with ID %s created \n", lastName,
					firstName, this.uuid);
		}
		
		public void addAccount(Account aAcct) {
			
			this.accounts.add(aAcct);
			
		}
		
		// return uuid
		
		public String getUUID() {
			return this.uuid;
		
		}
		
		// check if pin is correct user pin
		
		public boolean validatePin(String aPin) {
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				return MessageDigest.isEqual(md.digest(aPin.getBytes()),
						this.pinHash);
			} catch (NoSuchAlgorithmException e) {
				System.err.println("Error, caught no such algorithmException");
				e.printStackTrace();
				System.exit(1);
			}
			
			return false;
		}
		
		public String getFirstName() {
			return this.firstName;
		}
		
		
		// print summary for account
		public void printAccountsSummary() {
			System.out.printf("\n\n %s's account summary\n", this.firstName);
			for (int a = 0; a < this.accounts.size(); a++) {
				System.out.printf("(%d) %s \n", a+1, 
						this.accounts.get(a).getSummaryLine());
			}
			
			System.out.println();
		}
		
		// get number of accounts for user
		
		public int numAccounts() {
			return this.accounts.size();
		}
		
		// get transaction history for specific account
		public void printAcctTransactionHistory(int acctIndex) {
			this.accounts.get(acctIndex).printTransactionHistory();
		}
		
		// get account balance
		
		public double getAcctBalance(int acctIndex) {
			return this.accounts.get(acctIndex).getBalance();
		}
		
		// get uuid for an account
		public String getAcctUUID(int acctIndex) {
			return this.accounts.get(acctIndex).getUUID();
		}
		
		// add transaction to account
		public void addAcctTransaction(int acctIndex, double amount, String memo) {
			this.accounts.get(acctIndex).addTransaction(amount, memo);
			
		}
		}




