package people;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

import accounts.Account;
import banking.BankingSystem;
import exceptions.UserAlreadyExistsException;
import exceptions.UserDoesNotExistException;
/**
 * @author Michael Dahle - Code Solution for Homework Assignment 2
 */

public class Teller {

	private BankingSystem bs;
	
	public Teller() {
		this.bs = BankingSystem.getInstance();
	}
	
	public BigDecimal withdrawMoney(User u, Account a, BigDecimal amount) {
		return this.bs.withdrawMoney(u, a, amount);
	}
	
	public BigDecimal depositMoney(User u, Account a, BigDecimal amount) {
		return this.bs.depositMoney(u, a, amount);
	}
	
	public User enrollUser(User u) throws UserAlreadyExistsException {
		return this.bs.addUser(u);
	}
	
	public void removeUser(String licenseNumber) throws UserDoesNotExistException {
		this.bs.removeUser(licenseNumber);
	}
	
	public Account openAccount(User u, int accountType, ArrayList<User> authorizedUsers, BigDecimal accountBalance) throws UserAlreadyExistsException {
		return this.bs.openAccount(u, accountType, authorizedUsers, accountBalance);
	}
	
	public void closeAccount(User u, Account a) {
		this.bs.closeAccount(u, a);
	}
	
	public void closeSystem(User u, Account a) {
	this.bs.closeSystem(u, a);	
	}
	
}
