package accounts;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import people.Transaction;
import people.User;

/**
 * @author Michael Dahle - Code Solution for Homework Assignment 2
 */


public class BusinessCheckingAccount extends CheckingAccount {


	private int overdrawnTransactionsPerDay;
	private int monthlyTransactionCount;
	private BigDecimal maximumDepositWithNoFee = new BigDecimal("5000");
	private BigDecimal currentMonthlyDeposit = BigDecimal.ZERO;
	/*
	 * The maximum amount of money a user can withdraw while over drafted.
	 */
	private final static BigDecimal OVERDRAFT_MAXIMUM = new BigDecimal("-7500");
	/*
	 * The overdraft fee to be charged if the account balance goes below zero.
	 */
	private final static BigDecimal OVERDRAFT_FEE = new BigDecimal("35");

	public BusinessCheckingAccount(String primaryAccountHolder, ArrayList<User> authorizedUsers,
			BigDecimal accountBalance, int uniqueID) {
		super(primaryAccountHolder, authorizedUsers, accountBalance, uniqueID);
		overdrawnTransactionsPerDay = 0;
		monthlyTransactionCount = 0;

	}

	@Override
	public BigDecimal depositMoney(BigDecimal amount) {
		BigDecimal validAmount = super.getValidAmount(amount);
		this.currentMonthlyDeposit.add(validAmount);
		BigDecimal transactionFee = BigDecimal.ZERO;
		if (currentMonthlyDeposit.compareTo(maximumDepositWithNoFee) > 0) {
			transactionFee = validAmount.divide(new BigDecimal("100")).multiply(new BigDecimal("0.30"));
		}
		super.setAccountBalance(super.getAccountBalance().add(transactionFee));
		super.setAccountBalance(super.getAccountBalance().add(validAmount));
		BigDecimal fees = getTransactionFees();
		super.setAccountBalance(super.getAccountBalance().add(fees));
		super.getTransactionList().add(new Transaction(LocalDate.now().toString(), LocalDateTime.now().toString(), validAmount, Integer.toString(super.getUniqueID()), "deposit"));
		return super.getAccountBalance();
	}
	@Override
	public BigDecimal withdrawMoney(BigDecimal amount) {
		BigDecimal validAmount = super.getValidAmount(amount);
		if (super.getAccountBalance().subtract(validAmount).compareTo(OVERDRAFT_MAXIMUM) < 0) {
			throw new IllegalArgumentException(
					"Account cannot be overdrawn more than $" + OVERDRAFT_MAXIMUM.toPlainString());
		}
		if (super.getAccountBalance().compareTo(validAmount) < 0) {
			overdrawnTransactionsPerDay++;
			if (overdrawnTransactionsPerDay > 10) {
				throw new IllegalStateException("Cannot exceed 10 overdrafted items per day.");
			}
			super.setStatus(AccountStatus.OVERDRAWN);
			super.setAccountBalance(super.getAccountBalance().subtract(OVERDRAFT_FEE));
		}

		else {
			if(super.getAccountBalance().subtract(validAmount).compareTo(BigDecimal.ZERO) < 0){
				throw new IllegalArgumentException("Account cannot be overdrawn.");	
			}
		}

		super.setAccountBalance(super.getAccountBalance().subtract(validAmount));
		super.getTransactionList().add(new Transaction(LocalDate.now().toString(), LocalDateTime.now().toString(), validAmount, Integer.toString(super.getUniqueID()), "withdraw"));
		return super.getAccountBalance();
	}

	public BigDecimal getTransactionFees() {
		monthlyTransactionCount++;
		BigDecimal fee = BigDecimal.ZERO;
		if (monthlyTransactionCount > 100) {
			fee.add(new BigDecimal("0.50"));
		}
		return fee;
	}

	public void reset() {
		overdrawnTransactionsPerDay = 0;
	}
}

