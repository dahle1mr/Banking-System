package banking;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import accounts.Account;
import accounts.AccountStatus;
import accounts.BusinessCheckingAccount;
import accounts.BusinessSavingsAccount;
import accounts.PersonalCheckingAccount;
import accounts.PersonalSavingsAccount;
import accounts.StudentCheckingAccount;
import accounts.StudentSavingsAccount;
import daos.BankInformationDAO;
import exceptions.UserAlreadyExistsException;
import exceptions.UserDoesNotExistException;
import people.User;

/**
 * @author Michael Dahle - Code Solution for Homework Assignment 2
 */

public class BankingSystem {

	private static BankingSystem bs;
	Scanner s = new Scanner(System.in);
	HashMap<String, User> userMap = new HashMap<String, User>();
	private static int nextAccountID = 0;

	private BankingSystem(){
		try {
			loadUsers();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
	}

	/**
	 * Used to create the BankingSystem, and then get the instance of it once one has been created.
	 * 
	 * @return bs : returns the instance of the BankingSystem.
	 * @throws FileNotFoundException 
	 */
	public static BankingSystem getInstance() {
		if (bs == null) {
			bs = new BankingSystem();
		}

		return bs;
	}

	/**
	 * Checks to see if a user exists within the Hash map.
	 * @param a string of the license number of the user to check the hash map.
	 * @return true or false depending on if the user exists.
	 */
	public boolean userExists(String licenseNumber) {
		if (userMap.containsKey(licenseNumber)) {
			return true;
		} else
			return false;
	}

	/**
	 * Checks to see if a user's specific account exists.
	 * @param u, uniqueID : takes in a user that has the specified account, and the uniqueID of that account.
	 * @return true or false depending on if the account exists.
	 */
	public boolean accountExists(User u, int uniqueID) {

		for (int i = 0; i < u.getAccountList().size(); i++) {
			if (u.getAccountList().get(i).getUniqueID() == uniqueID)
				return true;
		}
		return false;
	}

	/**
	 * Adds a user to the to Hash Map.
	 * @param user : the user that will be added to the hashMap.
	 * @return the user.
	 */
	public User addUser(User u) throws UserAlreadyExistsException {

		if (userExists(u.getLicenseNumber())) {
			throw new UserAlreadyExistsException(
					"A user with the license number of " + u.getLicenseNumber() + " already exists in the system.");
		} else {
			userMap.put(u.getLicenseNumber(), u);
		}
		return u;
	}

	/**
	 * Removes the user from the Hash Map.
	 * @param licenseNumber : license that identifies the user that will be removed.
	 * 
	 */
	public void removeUser(String licenseNumber) throws UserDoesNotExistException {

		if (userMap.containsKey(licenseNumber)) {
			userMap.remove(licenseNumber);
		} else
			throw new UserDoesNotExistException(
					"A user with the license number of " + licenseNumber + " does not exist.");

	}

	/**
	 * Loads all users from the userMap.
	 * 
	 * 
	 */
	public void loadUsers() throws FileNotFoundException {
		ArrayList<User> list = BankInformationDAO.readUsers();

		for (User u : list) {
			userMap.put(u.getLicenseNumber(), u);
		}
	}

	/**
	 * Opens the specified account for a specified user.
	 * @param u, accountType, authorizedUsers, accountBalance : takes the user that the account belongs to, the type of account,
	 * all authorizedUsers for the account, and the balance of the account.
	 * @return the account.
	 */
	public Account openAccount(User u, int accountType, ArrayList<User> authorizedUsers, BigDecimal accountBalance)
			throws UserAlreadyExistsException {

		Account a;
		int minAge;
		int maxAge;

		switch (accountType) {
		case 1:
			a = new BusinessCheckingAccount(u.getLicenseNumber(), authorizedUsers, accountBalance, getNextAccountID());
			break;
		case 2:
			System.out.println(u);
			a = new BusinessSavingsAccount(u.getLicenseNumber(), authorizedUsers, accountBalance, getNextAccountID());
			break;
		case 3:
			a = new PersonalCheckingAccount(u.getLicenseNumber(), authorizedUsers, accountBalance, getNextAccountID());
			break;
		case 4:
			a = new PersonalSavingsAccount(u.getLicenseNumber(), authorizedUsers, accountBalance, getNextAccountID());
			break;
		case 5:
			minAge = 17;
			maxAge = 23;
			a = new StudentCheckingAccount(u.getLicenseNumber(), authorizedUsers, accountBalance, getNextAccountID());
			// This is still throwing the exception even if the user is the correct age
			//if(!a.verifyAge(u, minAge, maxAge)) {
			//	throw new IllegalStateException("User is not the appropriate age to open this account.");
			//}else
			break;
		case 6:
			minAge = 17;
			maxAge = 23;
			a = new StudentSavingsAccount(u.getLicenseNumber(), authorizedUsers, accountBalance, getNextAccountID());
			//if(!a.verifyAge(u, minAge, maxAge)) {
			//	throw new IllegalStateException("User is not the appropriate age to open this account.");
			//}else
				break;
			default:
				a = null;
			}

			if (!userExists(u.getLicenseNumber())) {
				addUser(u);
			}
			userMap.get(u.getLicenseNumber()).getAccountList().add(a);
			return a;

		}
		/**
		 * Closes a specified account.
		 * @param u, a : takes in the user of the specified account and the account itself.
		 * @return the account.
		 */
		public Account closeAccount(User u, Account a) {
			for(int i = 0; i < userMap.get(u.getLicenseNumber()).getAccountList().size(); i++){
				if(a.getUniqueID() == userMap.get(u.getLicenseNumber()).getAccountList().get(i).getUniqueID()) {		
					userMap.get(u.getLicenseNumber()).getAccountList().get(i).setStatus(AccountStatus.CLOSED);
				}
			}
			return a;
		}

		/**
		 * Withdraws money from an account tied to a user.
		 * @param u, a, amount : takes in the user that contains the account, the account itself, and the amount to be withdrawn from the account.
		 * @return the balance of the account.
		 */
		public BigDecimal withdrawMoney(User u, Account a, BigDecimal amount) {
			for(int i = 0; i < userMap.get(u.getLicenseNumber()).getAccountList().size(); i++){
				if(a.getUniqueID() == userMap.get(u.getLicenseNumber()).getAccountList().get(i).getUniqueID()) {
					userMap.get(u.getLicenseNumber()).getAccountList().get(i).withdrawMoney(amount);
				}
			}
			return a.getAccountBalance();
		}
		/**
		 * Deposits money from an account tied to a user.
		 * @param u, a, amount : takes in the user that contains the account, the account itself, and the amount to be deposited to the account.
		 * @return the balance of the account.
		 */
		public BigDecimal depositMoney(User u, Account a, BigDecimal amount) {

			for(int i = 0; i < userMap.get(u.getLicenseNumber()).getAccountList().size(); i++){
				if(a.getUniqueID() == userMap.get(u.getLicenseNumber()).getAccountList().get(i).getUniqueID()) {
					userMap.get(u.getLicenseNumber()).getAccountList().get(i).depositMoney(amount);
				}
			}
			return a.getAccountBalance();
		}
		/**
		 * Creates a new accountID and returns it.
		 * 
		 * @return the new Account ID. 
		 */
		public static int getNextAccountID() {
			nextAccountID++;
			return nextAccountID;
		}
		/**
		 * Closes the system after it is done being used, and writes the users, accounts, and transactions to the appropriate files.
		 * @param a, u : Takes in a user and account for the writeAccount and writeTransactions parameters.
		 * 
		 */
		public void closeSystem(User u, Account a) {
			try {
				BankInformationDAO.writeUsers(userMap);
				BankInformationDAO.writeAccounts(userMap, u, a);
				BankInformationDAO.writeTransactions(userMap, u, a);
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
		}
	}
