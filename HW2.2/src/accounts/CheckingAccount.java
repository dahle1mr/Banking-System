package accounts;

import java.math.BigDecimal;
import java.util.ArrayList;

import people.User;

/**
 * @author Michael Dahle - Code Solution for Homework Assignment 2
 * 
 * Creates a checking account that can have money deposited into it and money withdrawn from it. 
 * 
 */


public abstract class CheckingAccount extends Account {

	public CheckingAccount(String primaryAccountHolder, ArrayList<User> authorizedUsers, BigDecimal accountBalance,int uniqueID) {
		super(primaryAccountHolder, authorizedUsers, accountBalance, uniqueID);

	}

	@Override
	public abstract BigDecimal depositMoney(BigDecimal amount);
	@Override
	public abstract BigDecimal withdrawMoney(BigDecimal amount);



}
