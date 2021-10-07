package daos;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import accounts.Account;
import accounts.BusinessCheckingAccount;
import accounts.BusinessSavingsAccount;
import accounts.PersonalCheckingAccount;
import accounts.PersonalSavingsAccount;
import accounts.StudentCheckingAccount;
import accounts.StudentSavingsAccount;
import people.Transaction;
import people.User;

/**
 * @author Michael Dahle - Code Solution for Homework Assignment 2
 */

public class BankInformationDAO {

	/**
	 * Reads in users from User.txt file.
	 * 
	 * @return list : returns a list of all the users in the .txt file.
	 */
	public static ArrayList<User> readUsers() throws FileNotFoundException {
		File file = new File("Users.txt");
		Scanner sc = new Scanner(file);
		ArrayList<User> list = new ArrayList<User>();
		while (sc.hasNextLine()) {
			String data[] = sc.nextLine().split(",");
			list.add(new User(data[0], data[1], data[2], data[3], data[4], data[5], Integer.parseInt(data[6]),
					Integer.parseInt(data[7]), new ArrayList<Account>()));
		}
		return list;
	}
	/**
	 * Reads in the accounts in the Accounts.txt file.
	 * 
	 * @return list : returns a list of all accounts from the .txt file. 
	 */
	public static ArrayList<Account> readAccounts() throws FileNotFoundException {
		File file = new File("Accounts.txt");
		Scanner sc = new Scanner(file);
		ArrayList<Account> list = new ArrayList<Account>();
		while (sc.hasNext()) {
			String data[] = sc.nextLine().split(",");
			Account a;

			switch (data[0]) {
			case "BCA":
				a = new BusinessCheckingAccount(data[1], new ArrayList<User>(), new BigDecimal(data[2]), Integer.parseInt(data[3]));
				break;
			case "BSA":
				a = new BusinessSavingsAccount(data[1], new ArrayList<User>(), new BigDecimal(data[2]), Integer.parseInt(data[3]));
				break;
			case "PCA":
				a = new PersonalCheckingAccount(data[1], new ArrayList<User>(), new BigDecimal(data[2]), Integer.parseInt(data[3]));
				break;
			case "PSA":
				a = new PersonalSavingsAccount(data[1], new ArrayList<User>(), new BigDecimal(data[2]), Integer.parseInt(data[3]));
				break;
			case "SCA":
				a = new StudentCheckingAccount(data[1], new ArrayList<User>(), new BigDecimal(data[2]), Integer.parseInt(data[3]));
				break;
			case "SSA":
				a = new StudentSavingsAccount(data[1], new ArrayList<User>(), new BigDecimal(data[2]), Integer.parseInt(data[3]));
				break;
			default:
				a = null;
			}
			list.add(a);
		}

		return list;
	}
	/**
	 * Reads in the transactions from the Transactions.txt
	 * 
	 * @return list : returns a list of all transactions from all accounts from a specified user.
	 */
	public static ArrayList<Transaction> readTransactions() throws FileNotFoundException {
		File file = new File("Transactions.txt");
		Scanner sc = new Scanner(file);
		ArrayList<Transaction> list = new ArrayList<Transaction>();
		while (sc.hasNextLine()) {
			String data[] = sc.nextLine().split(",");
			list.add(new Transaction(data[0], data[1], new BigDecimal(data[2]), data[3], data[4]));
		}
		return list;
	}

	
	/**
	 * Writes all created users into the Users.txt file.
	 * @param userMap : a hashmap that contains all of the current users.
	 * 
	 */
	public static void writeUsers(HashMap<String, User> userMap) throws FileNotFoundException {
		File file = new File("Users.txt");
		PrintWriter pw = new PrintWriter(file);

		Iterator it = userMap.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();
			pw.println(pair.getValue());
		}
		pw.close();
	}
	/**
	 * Writes all created accounts into the Accounts.txt file.
	 * @param userMap, u, a : Takes in the userMap that contains all users, a specified user that contains the needed account, and the specified account.
	 * 
	 */
	public static void writeAccounts(HashMap<String, User> userMap, User u, Account a) throws FileNotFoundException {
		File file = new File("Accounts.txt");
		PrintWriter pw = new PrintWriter(file);

		for(Map.Entry<String, User> entry : userMap.entrySet()) {
			for (int j = 0; j < entry.getValue().getAccountList().size(); j++) {
				pw.println(entry.getValue().getAccountList().get(j));
			}
		}
		pw.close();
	}
	/**
	 * Writes all transactions for an account to Transactions.txt
	 * @param userMap, u, a : Takes in the userMap that contains all users, a specified user that contains the account, and the account.
	 * 
	 */
	public static void writeTransactions(HashMap<String, User> userMap, User u, Account a) throws FileNotFoundException {
		File file = new File("Transactions.txt");
		PrintWriter pw = new PrintWriter(file);

		for(Map.Entry<String, User> entry : userMap.entrySet()) {
			for (int i = 0; i < entry.getValue().getAccountList().size(); i++) {
				for(int j = 0; j < entry.getValue().getAccountList().get(i).getTransactionList().size(); j++) { 
					pw.println(entry.getValue().getAccountList().get(i).getTransactionList().get(j));
				}
			}
		}
		pw.close();
	}

}

