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


public class BusinessSavingsAccount extends SavingsAccount {

	private int monthlyTransactionCount;

	public BusinessSavingsAccount(String primaryAccountHolder, ArrayList<User> authorizedUsers,
			BigDecimal accountBalance, int uniqueID) {
		super(primaryAccountHolder, authorizedUsers, accountBalance, uniqueID);
		monthlyTransactionCount = 0;

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
		if(super.getAccountBalance().subtract(validAmount).compareTo(BigDecimal.ZERO) < 0){
			throw new IllegalArgumentException("Account cannot be overdrawn.");	
		}

		super.setAccountBalance(super.getAccountBalance().subtract(getValidAmount(amount)));
		super.getTransactionList().add(new Transaction(LocalDate.now().toString(), LocalDateTime.now().toString(), validAmount, Integer.toString(super.getUniqueID()), "withdraw"));
		return super.getAccountBalance();
	}

	public BigDecimal getMonthlyFee() {
		BigDecimal fee = new BigDecimal("5.00");
		if (super.getAccountBalance().compareTo(BigDecimal.valueOf(3500)) >= 0){
			fee = BigDecimal.ZERO;
		}
		return fee;
	}

	public BigDecimal getTransactionFees() {
		monthlyTransactionCount++;
		BigDecimal fee = BigDecimal.ZERO;
		if (monthlyTransactionCount > 10) {
			fee.add(new BigDecimal("0.25"));
		}
		return fee;
	}


}
