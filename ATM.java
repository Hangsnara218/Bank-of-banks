import java.util.Scanner;

public class ATM {

	public static void main(String[] args) {
			// initialize scanner
		Scanner sc = new Scanner(System.in);
		
		// initialize banks
		Bank theBank = new Bank("Bank of banks");
		
		// add a user
		
		User aUser = theBank.addUser("John", "Johnson", "4321");
		
		// add account
		
		Account newAccount = new Account("Checking", aUser, theBank);
		aUser.addAccount(newAccount);
		theBank.addAccount(newAccount);
		
		User curUser;
		while (true) {
			// login prompt until successful login
			curUser = ATM.mainMenuPrompt(theBank, sc);
			
			// main menu until user quits
			
			ATM.printUserMenu(curUser, sc);
			
		}
		

	}
	
	public static User mainMenuPrompt(Bank theBank, Scanner sc) {
		
		String userID;
		String pin;
		User authUser;
		
		// loop login credentials until successfull
		do {
			System.out.printf("\n\nWelcome to %s\n\n", theBank.getName());
			System.out.print("Enter user ID: ");
			userID = sc.nextLine();
			System.out.print("Enter pin code: ");
			pin = sc.nextLine();
			
			// try to get user corresponding to combo above
			
			authUser = theBank.userLogin(userID, pin);
			if (authUser == null) {
				System.out.println("Incorrect credentials" + "Please try again");
				
			}
		} while(authUser == null); //loop until successful login
		
		return authUser;
	}
	
	public static void printUserMenu(User theUser, Scanner sc) {
		// summary of user account
		
		theUser.printAccountsSummary();
		
		// initialize
		int choice;
		// user menu
		
		do {
			System.out.printf("Welcome to %s", theUser.getFirstName());
			System.out.println("Choose an option: ");
			System.out.println("1) Show transaction history");
			System.out.println("2) Withdrawal");
			System.out.println("3) Deposit");
			System.out.println("4) Transfer");
			System.out.println("5) Exit");
			System.out.println("");
			System.out.println("");
			System.out.println("Enter your choice: ");
			choice = sc.nextInt();
			
			if (choice < 1 || choice > 5) {
				System.out.println("Invalid option. Please try again");
			} 
		}while(choice < 1 || choice > 5);
			
			// process option
			
			switch (choice) {
			case 1:
				ATM.showTransactionHistory(theUser, sc);
				break;
				
			case 2: 
				ATM.withdrawalFunds(theUser, sc);
				break;
				
			case 3:
				ATM.depositFunds(theUser, sc);
				break;
			
			case 4:
				ATM.transferFunds(theUser, sc);
				break;
			
			case 5:
				// remove rest of previous input
				sc.nextLine();
				break;
			}
			// display again unless user wants to quit
			
			if (choice !=5) {
				ATM.printUserMenu(theUser, sc);
				
			}
		
		}
	

			// show transaction history for account
			
	public static void showTransactionHistory(User theUser, Scanner sc) {
				
		int theAcct;
				

					// get account to look history
		do {
			System.out.printf("Enter the account number (1-%d of the account\n" +
					"to see transactions: ", 
					theUser.numAccounts());
					
			theAcct = sc.nextInt()-1;
			if (theAcct < 0 || theAcct >= theUser.numAccounts()) {
				System.out.println("Error, invalid account. Try again");
						
				}
			} while(theAcct < 0 || theAcct >= theUser.numAccounts());
				
				//print transaction history
			theUser.printAcctTransactionHistory(theAcct);
			
		}
	
	public static void transferFunds(User theUser, Scanner sc) {
		
		
		int fromAcct;
		int toAcct;
		double amount;
		double acctBalance;
		
		// get account to transfer from
		do {
			System.out.printf("Enter number (1-%d) of account\n " +
					"to transfer from:", theUser.numAccounts());
			fromAcct = sc.nextInt()-1;
			if (fromAcct < 0 || fromAcct >= theUser.numAccounts()) {
				System.out.println("Invalid account. Please try again");
			}
			
		} while(fromAcct < 0 || fromAcct >= theUser.numAccounts());
		acctBalance = theUser.getAcctBalance(fromAcct);
		
		// get account to transfer to
		do {
			System.out.printf("Enter number (1-%d) of account\n" +
					"to transfer to:",  theUser.numAccounts());
			toAcct = sc.nextInt()-1;
			if (toAcct < 0 || toAcct >= theUser.numAccounts()) {
				System.out.println("Invalid account. Please try again");
			}
			
		} while(toAcct < 0 || toAcct >= theUser.numAccounts());
		
		// get amount for transfer
		do {
			
			System.out.printf("Enter amount to transfer (max $%.02f): ", acctBalance);
			amount = sc.nextDouble();
			if (amount < 0) {
				System.out.println("Amount must be higher than zero. ");
				
			}
			else if (amount > acctBalance) {
				System.out.printf("Amount cannot be higher than\n" +
			"balance of $%.02f.\n", acctBalance);
			
				
			}
			
		} while(amount <0 || amount > acctBalance);
		
		// do transfer
		theUser.addAcctTransaction(fromAcct, -1*amount, String.format("Transfer to account %s", theUser.getAcctUUID(toAcct)));
		theUser.addAcctTransaction(toAcct, amount, String.format("Transfer to account %s", theUser.getAcctUUID(fromAcct)));
	}
	// withdrawal from account
	public static void withdrawalFunds(User theUser, Scanner sc) {
		
		int fromAcct;
		double amount;
		double acctBalance;
		String memo;
		// get account to transfer from
		do {
			System.out.printf("Enter number (1-%d) of account\n" + 
					"to transfer from: ", theUser.numAccounts());
			fromAcct = sc.nextInt()-1;
			if (fromAcct < 0 || fromAcct >= theUser.numAccounts()) {
				System.out.println("Invalid account. Try again");
				
			}
		} while(fromAcct < 0 || fromAcct >= theUser.numAccounts());
		acctBalance = theUser.getAcctBalance(fromAcct);
		
		// get amount to transfer
		do {
			System.out.printf("Enter amount to withdraw (max $%.02f): ", acctBalance);
			amount = sc.nextDouble();
			if (amount < 0) {
				System.out.println("Amount must be higher than zero. ");
				
			}
			else if (amount > acctBalance) {
				System.out.printf("Amount cannot be higher than\n" +
			"balance of $%.02f.\n", acctBalance);
			
				
			}
			
		} while(amount <0 || amount > acctBalance);
		
		
		sc.nextLine();
		
		//get memo
		System.out.println("Enter memo: ");
		memo = sc.nextLine();
		
		
		// withdrawal
		theUser.addAcctTransaction(fromAcct, -1*amount, memo);
		}
	
	public static void depositFunds(User theUser, Scanner sc) {
		
		int toAcct;
		double amount;
		double acctBalance;
		String memo;
		
		do {
			System.out.printf("Enter number (1-%d) of account\n" + 
					"to transfer to: ", theUser.numAccounts());
			toAcct = sc.nextInt()-1;
			if (toAcct < 0 || toAcct >= theUser.numAccounts()) {
				System.out.println("Invalid account. Try again");
			
		}
		}
			while(toAcct < 0 || toAcct >= theUser.numAccounts());
			acctBalance = theUser.getAcctBalance(toAcct);
			
			// get amount to transfer
			do {
				System.out.printf("Enter amount to transfer (max $%.02f): ", acctBalance);
				amount = sc.nextDouble();
				if (amount < 0) {
					System.out.println("Amount must be higher than zero. ");
					
				}
				
					
				
				
			} while(amount <0 );
			sc.nextLine();
			
			// memo
			System.out.println("Enter memo: ");
			memo = sc.nextLine();
			
			// make deposit
			
			theUser.addAcctTransaction(toAcct, amount, memo);
			
		
	}
	}
	
	

		
	
		
	
	
	


