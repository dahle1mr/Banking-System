package people;
import java.math.BigDecimal;
import java.util.Date;
/**
 * @author Michael Dahle - Code Solution for Homework Assignment 2
 */

public class Transaction {

	private String transactionDate;
	private String transactionTime;
	private BigDecimal amount;
	private String accountID;
	private String transactionType;


	public Transaction(String transactionDate, String transactionTime, BigDecimal amount, String accountID, String transactionType) {
		this.transactionDate = transactionDate;
		this.transactionTime = transactionTime;
		this.amount = amount;
		this.accountID = accountID;
		this.transactionType = transactionType;
	}


	public String getTransactionDate() {
		return transactionDate;
	}


	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}


	public String getTransactionTime() {
		return transactionTime;
	}


	public void setTransactionTime(String transactionTime) {
		this.transactionTime = transactionTime;
	}


	public BigDecimal getAmount() {
		return amount;
	}


	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}


	public String getAccountID() {
		return accountID;
	}


	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}


	public String getTransactionType() {
		return transactionType;
	}


	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String toString() {
		return transactionDate + "," +  transactionTime + "," +  amount + "," +  accountID + "," + transactionType;
	}

}
