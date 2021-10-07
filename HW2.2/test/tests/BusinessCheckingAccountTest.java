package tests;
import static org.junit.Assert.assertEquals;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;

import accounts.BusinessCheckingAccount;

public class BusinessCheckingAccountTest {
	
	private BusinessCheckingAccount bcAccount;
	
	@Before
	public void setup() {
		bcAccount= new BusinessCheckingAccount(null, null, null, 0);
		bcAccount.setAccountBalance(new BigDecimal("100.00"));
	}
	
	@Test
	public void withdraw_amount90_returns10() {
		assertEquals(new BigDecimal("10.00"), bcAccount.withdrawMoney(new BigDecimal("90.00")));
	}
	

	@Test
	public void withdraw_amount150_returns85() {
		assertEquals(new BigDecimal("-85.00"), bcAccount.withdrawMoney(new BigDecimal("150.00")));
	}
	
	
	
	@Test(expected = IllegalArgumentException.class)
	public void withdraw_amount10000_throwsIllegalArgumentException() {
		bcAccount.withdrawMoney(new BigDecimal("10000.00"));
	}
	
	
	@Test
	public void deposit_amount50_returns150() {
		assertEquals(new BigDecimal("150.00"), bcAccount.depositMoney(new BigDecimal("50.00")));
	}
	
}
