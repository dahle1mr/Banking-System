package accounts;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import people.Transaction;
import people.User;

/**
 * @author Michael Dahle - Code Solution for Homework Assignment 2
 */

public class PersonalCheckingAccount extends CheckingAccount {

	private BigDecimal maxDepositForCashBack = new BigDecimal("3000");
	private BigDecimal minMonthlyDirectDeposit = new BigDecimal("500");
	private BigDecimal minMonthlyBalance = new BigDecimal("1500");
	private BigDecimal amountDepositedPerMonth = BigDecimal.ZERO;

	/*
	 * The maximum amount of money a user can withdraw while over drafted.
	 */
	private final static BigDecimal OVERDRAFT_MAXIMUM = new BigDecimal("-1500");
	/*
	 * The overdraft fee to be charged if the account balance goes below zero.
	 */
	private final static BigDecimal OVERDRAFT_FEE = new BigDecimal("35");

	private boolean optedInto;

	public PersonalCheckingAccount(String primaryAccountHolder, ArrayList<User> authorizedUsers,
			BigDecimal accountBalance, int uniqueID) {
		super(primaryAccountHolder, authorizedUsers, accountBalance, uniqueID);

	}

	@Override
	public BigDecimal depositMoney(BigDecimal amount) {
		BigDecimal validAmount = super.getValidAmount(amount);
		// get 1% money back of the valid amount, unless it goes pass 3000$
		super.setAccountBalance(super.getAccountBalance().add(validAmount));
		this.addReward(validAmount);
		super.getTransactionList().add(new Transaction(LocalDate.now().toString(), LocalDateTime.now().toString(), validAmount, Integer.toString(super.getUniqueID()), "deposit"));
		return super.getAccountBalance();
	}
	
	private void addReward(BigDecimal amount) {
		BigDecimal reward = BigDecimal.ZERO;
		if (amountDepositedPerMonth.compareTo(maxDepositForCashBack) < 0) {
			if (amountDepositedPerMonth.add(amount).compareTo(maxDepositForCashBack) <= 0) {
				reward = amount.multiply(new BigDecimal("0.01"));
			} else {
				BigDecimal percent = amountDepositedPerMonth.add(amount).subtract(maxDepositForCashBack);
				reward = percent.multiply(new BigDecimal("0.01"));
			}
			amountDepositedPerMonth = amountDepositedPerMonth.add(amount);
			super.setAccountBalance(super.getAccountBalance().add(reward));
		}
	}

	@Override
	public BigDecimal withdrawMoney(BigDecimal amount) {
		BigDecimal validAmount = super.getValidAmount(amount);
		if (optedInto = true) {
			if (super.getAccountBalance().subtract(validAmount).compareTo(OVERDRAFT_MAXIMUM) < 0) {
				throw new IllegalArgumentException(
						"Account cannot be overdrawn more than $" + OVERDRAFT_MAXIMUM.toPlainString());
			}
			if (super.getAccountBalance().compareTo(validAmount) < 0) {
				super.setStatus(AccountStatus.OVERDRAWN);
				super.setAccountBalance(super.getAccountBalance().subtract(OVERDRAFT_FEE));
			}
		} else {
			if(super.getAccountBalance().subtract(validAmount).compareTo(BigDecimal.ZERO) < 0) {
				throw new IllegalArgumentException("Account cannot be overdrawn.");
			}
		}
		super.setAccountBalance(super.getAccountBalance().subtract(validAmount));
		super.getTransactionList().add(new Transaction(LocalDate.now().toString(), LocalDateTime.now().toString(), validAmount, Integer.toString(super.getUniqueID()), "withdraw"));
		return super.getAccountBalance();
	}

	public BigDecimal getMonthlyFee(BigDecimal amount) {
		BigDecimal fee = new BigDecimal("10.00");
		// Make a direct deposit of 500 or more a month, waive fee
		if (amount.compareTo(minMonthlyDirectDeposit) > 0) {
			fee = BigDecimal.ZERO;
		}
		// if their balance each day is above 1500, waive fee
		if (super.getAccountBalance().compareTo(minMonthlyBalance) > 0) {
			fee = BigDecimal.ZERO;
		}
		return fee;
	}
}
