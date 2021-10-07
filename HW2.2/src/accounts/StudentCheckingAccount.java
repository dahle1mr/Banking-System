package accounts;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import people.Transaction;
import people.User;
/**
 * @author Michael Dahle - Code Solution for Homework Assignment 2
 */

public class StudentCheckingAccount extends CheckingAccount {

	/*
	 * The maximum amount of money a user can withdraw while over drafted.
	 */
	private final static BigDecimal OVERDRAFT_MAXIMUM = new BigDecimal("-500");
	/*
	 * The overdraft fee to be charged if the account balance goes below zero.
	 */
	private final static BigDecimal OVERDRAFT_FEE = new BigDecimal("35");

	private boolean optedInto;

	public StudentCheckingAccount(String primaryAccountHolder, ArrayList<User> authorizedUsers,
			BigDecimal accountBalance, int uniqueID) {
		super(primaryAccountHolder, authorizedUsers, accountBalance, uniqueID);
		
	}

	@Override
	public BigDecimal depositMoney(BigDecimal amount) {
		BigDecimal validAmount = super.getValidAmount(amount);
		super.setAccountBalance(super.getAccountBalance().add(validAmount));

		super.getTransactionList().add(new Transaction(LocalDate.now().toString(), LocalDateTime.now().toString(), validAmount, Integer.toString(super.getUniqueID()), "deposit"));
		return super.getAccountBalance();
	}

	@Override
	public BigDecimal withdrawMoney(BigDecimal amount) {
		BigDecimal validAmount = super.getValidAmount(amount);
		if(optedInto = true) {
			if (super.getAccountBalance().subtract(validAmount).compareTo(OVERDRAFT_MAXIMUM) < 0) {
				throw new IllegalArgumentException(
						"Account cannot be overdrawn more than $" + OVERDRAFT_MAXIMUM.toPlainString());
			}
			if (super.getAccountBalance().compareTo(validAmount) < 0) {
				super.setStatus(AccountStatus.OVERDRAWN);
				super.setAccountBalance(super.getAccountBalance().subtract(OVERDRAFT_FEE));
			} 
			else {
				if(super.getAccountBalance().subtract(validAmount).compareTo(BigDecimal.ZERO) < 0){
					throw new IllegalArgumentException("Account cannot be overdrawn.");	
				}
			}
		}
			super.setAccountBalance(super.getAccountBalance().subtract(validAmount));
			super.getTransactionList().add(new Transaction(LocalDate.now().toString(), LocalDateTime.now().toString(), validAmount, Integer.toString(super.getUniqueID()), "withdraw"));
			return super.getAccountBalance();
		
	}

}