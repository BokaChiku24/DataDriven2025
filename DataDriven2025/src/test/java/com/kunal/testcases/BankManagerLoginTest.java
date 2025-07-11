package com.kunal.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.kunal.base.TestBase;

public class BankManagerLoginTest extends TestBase {

	@Test
	public void loginAsBankmanager() {
		verifyEquals("abc","xyz");
		log.debug("Inside the bank manager login test");
		click("bmlBtn_XPATH");
		Assert.assertTrue(isElementPresent("addCustBtn_XPATH"));
		log.debug("Login successfully executed");
		Assert.fail("Failing testcase intentinally");

	}

}
