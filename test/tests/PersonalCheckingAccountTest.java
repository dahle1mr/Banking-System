package tests;
import static org.junit.Assert.assertEquals;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;

import accounts.PersonalCheckingAccount;

public class PersonalCheckingAccountTest {
	
	private PersonalCheckingAccount pcAccount;
	
	@Before
	public void setup() {
		pcAccount= new PersonalCheckingAccount(null, null, null, 0);
		pcAccount.setAccountBalance(new BigDecimal("100.00"));
	}
	
	@Test
	public void withdraw_amount90_returns10() {
		assertEquals(new BigDecimal("10.00"), pcAccount.withdrawMoney(new BigDecimal("90.00")));
	}
	

	@Test
	public void withdraw_amount150_returns85() {
		assertEquals(new BigDecimal("-85.00"), pcAccount.withdrawMoney(new BigDecimal("150.00")));
	}
	
	
	
	@Test(expected = IllegalArgumentException.class)
	public void withdraw_amount10000_throwsIllegalArgumentException() {
		pcAccount.withdrawMoney(new BigDecimal("10000.00"));
	}
	
	
	@Test
	public void deposit_amount50_returns150() {
		assertEquals(new BigDecimal("150.5000"), pcAccount.depositMoney(new BigDecimal("50.00")));
	}
	
}
