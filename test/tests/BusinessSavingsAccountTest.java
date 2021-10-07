package tests;
import static org.junit.Assert.assertEquals;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;

import accounts.BusinessSavingsAccount;

public class BusinessSavingsAccountTest {
	
	private BusinessSavingsAccount bsAccount;
	
	@Before
	public void setup() {
		bsAccount= new BusinessSavingsAccount(null, null, null, 0);
		bsAccount.setAccountBalance(new BigDecimal("100.00"));
	}
	
	@Test
	public void withdraw_amount90_returns10() {
		assertEquals(new BigDecimal("10.00"), bsAccount.withdrawMoney(new BigDecimal("90.00")));
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void withdraw_amount10000_throwsIllegalArgumentException() {
		bsAccount.withdrawMoney(new BigDecimal("10000.00"));
	}
	
	
	@Test
	public void deposit_amount50_returns150() {
		assertEquals(new BigDecimal("150.00"), bsAccount.depositMoney(new BigDecimal("50.00")));
	}
}
	

