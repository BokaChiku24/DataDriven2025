package com.kunal.testcases;

import org.testng.annotations.Test;

import com.kunal.base.BaseTest;

public class BankManagerLoginTest extends BaseTest {

	@Test
	public void loginAsBankManager() {
		
		click("bmlBtn_XPATH");
		isElementPresent("addCustomerBtn_XPATH");
	}

}
