package tests;
import static org.junit.Assert.assertEquals;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;

import accounts.StudentSavingsAccount;

public class StudentSavingsAccountTest {
	
	private StudentSavingsAccount ssAccount;
	
	@Before
	public void setup() {
		ssAccount= new StudentSavingsAccount(null, null, null, 0);
		ssAccount.setAccountBalance(new BigDecimal("100.00"));
	}
	
	@Test
	public void withdraw_amount90_returns10() {
		assertEquals(new BigDecimal("10.00"), ssAccount.withdrawMoney(new BigDecimal("90.00")));
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void withdraw_amount10000_throwsIllegalArgumentException() {
		ssAccount.withdrawMoney(new BigDecimal("10000.00"));
	}
	
	
	@Test
	public void deposit_amount50_returns150() {
		assertEquals(new BigDecimal("150.00"), ssAccount.depositMoney(new BigDecimal("50.00")));
	}
}
	