package accounts;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import people.Transaction;
import people.User;
/**
 * @author Michael Dahle - Code Solution for Homework Assignment 2
 */

public abstract class Account {

	private int uniqueID;
	private String primaryAccountHolder;
	private ArrayList<User> authorizedUsers;
	private BigDecimal accountBalance;
	private LocalDate yearOpened;
	private LocalDate yearClosed;
	private AccountStatus accountStatus;
	private ArrayList<Transaction> transactionList;

	public Account(String primaryAccountHolder, ArrayList<User> authorizedUsers, BigDecimal accountBalance,int uniqueID) {
		this.uniqueID = uniqueID;
		this.primaryAccountHolder = primaryAccountHolder;
		this.setAuthorizedUsers(authorizedUsers);
		this.accountBalance = accountBalance;
		this.setYearOpened(LocalDate.now());
		this.setYearClosed(null);
		this.setStatus(accountStatus.ACTIVE);
		this.setTransactionList(new ArrayList<Transaction>());

	}

	/**
	 * 
	 * Used to deposit money into the account.
	 * 
	 * @param amount : The amount to withdraw from the account balance.
	 * @return newBalance : The account balance after money has been deposited or withdrawn.
	 */
	public abstract BigDecimal depositMoney(BigDecimal amount);

	/**
	 * 
	 * Used to withdraw money from the account.
	 * 
	 * @param amount : The amount to withdraw from the account balance.
	 * @return newBalance : : The account balance after money has been deposited or withdrawn.
	 */
	public abstract BigDecimal withdrawMoney(BigDecimal amount);

	/**
	 * Verifies the amount is a valid amount and returns a BigDecimal
	 * representation.
	 * 
	 * @param amount : The amount to verify.
	 * @throws IllegalArgumentException: If the amount is not a valid currency such
	 *                                   as "10.00"
	 * @return A big decimal representation of the amount.
	 */
	protected BigDecimal getValidAmount(BigDecimal amount) throws IllegalArgumentException {

		if (amount.toString().matches("^\\d+\\.\\d{2}$")) {
			return amount;
		}
		throw new IllegalArgumentException(amount + " is not valid.");
	}
	/**
	 * Verifies the user's age in order to open a specific type of account.
	 * 
	 * @param The user to get the user's current age, and then the minimum allowed age
	 * 			and the maximum allowed age.
	 * 
	 * @return whether the user is the right age to open the specified account.
	 */
	public boolean verifyAge(User u, int minAge, int maxAge) {
		return u.getAge() >= minAge && u.getAge() <= maxAge;

	}

	public int getUniqueID() {
		return uniqueID;
	}

	public void setUniqueID(int uniqueID) {
		this.uniqueID = uniqueID;
	}

	public String getPrimaryAccountHolder() {
		return primaryAccountHolder;
	}

	public void setPrimaryAccountHolder(String primaryAccountHolder) {
		this.primaryAccountHolder = primaryAccountHolder;
	}

	public BigDecimal getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}

	public LocalDate getYearOpened() {
		return yearOpened;
	}

	public void setYearOpened(LocalDate yearOpened) {
		this.yearOpened = yearOpened;
	}

	public LocalDate getYearClosed() {
		return yearClosed;
	}

	public void setYearClosed(LocalDate yearClosed) {
		this.yearClosed = yearClosed;
	}

	public ArrayList<User> getAuthorizedUsers() {
		return authorizedUsers;
	}

	public void setAuthorizedUsers(ArrayList<User> authorizedUsers) {
		this.authorizedUsers = authorizedUsers;
	}

	public AccountStatus getStatus() {
		return getStatus();
	}

	public void setStatus(AccountStatus status) {
		this.accountStatus = status;
	}

	public ArrayList<Transaction> getTransactionList() {
		return transactionList;
	}

	public void setTransactionList(ArrayList<Transaction> transactionList) {
		this.transactionList = transactionList;
	}
	
	public String toString() {
		return primaryAccountHolder + "," + authorizedUsers + "," + accountBalance + "," + uniqueID;
	}


}

