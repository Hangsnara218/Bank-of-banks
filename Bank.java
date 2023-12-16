import java.util.ArrayList;
import java.util.Random;

public class Bank {

	
	/* bank info: user name, list of users, list of accounts 
	 * */
	
	private String name;
	
	private ArrayList<User> users;
	
	private ArrayList<Account> accounts;
	
	public Bank(String name) {
		this.name = name;
		this.users = new ArrayList<User>();
		this.accounts = new ArrayList<Account>();
	}
	
	// generate uuid
	public String getNewUserUUID() {
		
		String uuid;
		Random rn = new Random();
		int len = 6;
		boolean notUnique;
		
		// loop to it gets a unique ID number
		do {
			
			// generate new number
			uuid = "";
			for (int c = 0; c < len; c++) {
				uuid += ((Integer)rn.nextInt(10)).toString();
			}

			// make sure it is unique
			notUnique = false;
			for (User u : this.users) {
				if (uuid.compareTo(u.getUUID()) == 0) {
					notUnique = true;
					break;
				}
			}
			
			
		} while(notUnique);
		
		return uuid;
		
	}
	
	public String getNewAccountUUID(){
		
		String uuid;
		Random rn = new Random();
		int len = 10;
		boolean notUnique;
		
		// loop to it gets a unique account number
		do {
			
			// generate new number
			uuid = "";
			for (int c = 0; c < len; c++) {
				uuid += ((Integer)rn.nextInt(10)).toString();
			}

			// make sure it is unique
			notUnique = false;
			for (Account a : this.accounts) {
				if (uuid.compareTo(a.getUUID()) == 0) {
					notUnique = true;
					break;
				}
			}
			
			
		} while(notUnique);
		
		return uuid;
				
		
	}
	
	public void addAccount(Account aAcct){
		this.accounts.add(aAcct);
	}
	

	
	
	public User addUser(String firstName, String lastName, String pin) {
		// create a user object, add to bank and user
		User newUser = new User(firstName, lastName, pin, this);
		this.users.add(newUser);
		
		// create an account
		
		Account newAccount = new Account("Savings", newUser, this);
		newUser.addAccount(newAccount);
		this.accounts.add(newAccount);
		
		return newUser;
	}
	
	// login 
	public User userLogin(String userID, String pin) {
	
		// find user
		
		for (User u : this.users) {
			// if user is found
			if (u.getUUID().compareTo(userID) == 0 && u.validatePin(pin)) {
				return u;
			}
		}
		// if user not found / pin not valid, 
		return null;
	}
	
	
	// get name of bank
	public String getName() {
		
		return this.name;
		
	}
}


