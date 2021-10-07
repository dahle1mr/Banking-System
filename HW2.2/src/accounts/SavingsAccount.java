package accounts;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;

import people.User;
/**
 * @author Michael Dahle - Code Solution for Homework Assignment 2
 * 
 * Creates a savings account that can have money deposited into it and money withdrawn from it. 
 * 
 */

public abstract class SavingsAccount extends Account {

	public SavingsAccount(String primaryAccountHolder, ArrayList<User> authorizedUsers, BigDecimal accountBalance,int uniqueID) {
		super(primaryAccountHolder, authorizedUsers, accountBalance, uniqueID);

	}

	@Override
	public abstract BigDecimal depositMoney(BigDecimal amount);
	@Override
	public abstract BigDecimal withdrawMoney(BigDecimal amount);
	
}

