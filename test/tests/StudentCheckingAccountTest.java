package tests;
import static org.junit.Assert.assertEquals;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;

import accounts.StudentCheckingAccount;

public class StudentCheckingAccountTest {
	
	private StudentCheckingAccount scAccount;
	
	@Before
	public void setup() {
		scAccount= new StudentCheckingAccount(null, null, null, 0);
		scAccount.setAccountBalance(new BigDecimal("100.00"));
	}
	
	@Test
	public void withdraw_amount90_returns10() {
		assertEquals(new BigDecimal("10.00"), scAccount.withdrawMoney(new BigDecimal("90.00")));
	}
	

	@Test
	public void withdraw_amount150_returns85() {
		assertEquals(new BigDecimal("-85.00"), scAccount.withdrawMoney(new BigDecimal("150.00")));
	}
	
	
	
	@Test(expected = IllegalArgumentException.class)
	public void withdraw_amount10000_throwsIllegalArgumentException() {
		scAccount.withdrawMoney(new BigDecimal("10000.00"));
	}
	
	
	@Test
	public void deposit_amount50_returns150() {
		assertEquals(new BigDecimal("150.00"), scAccount.depositMoney(new BigDecimal("50.00")));
	}
	
}