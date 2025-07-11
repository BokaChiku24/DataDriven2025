package com.kunal.testcases;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.kunal.base.TestBase;

public class AddCustomerTest extends TestBase {

	@BeforeClass
	public void goToCustomer() {
		click("bmlBtn_XPATH");
		click("addCustBtn_XPATH");
	}

	@Test(dataProvider = "getCustomerData")
	public void addCustomer(String firstName, String lastName, String postCode, String alertText) {
		
		type("firstName_CSS",firstName);
		type("lastName_CSS",lastName);
		type("postCode_CSS",postCode);
		click("cutomerSubmit_CSS");
		Reporter.log("Customer added successfully");
		wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alertText(driver, alertText));
		alertAccept(driver);
	}

	@DataProvider
	public Object[][] getCustomerData() {
		Object[][] obj = new Object[2][4];
		obj[0][0] = "Kunal";
		obj[0][1] = "Chavan";
		obj[0][2] = "51109";
		obj[0][3] = "Customer added successfully";

		obj[1][0] = "Raghav";
		obj[1][1] = "Chavan";
		obj[1][2] = "47899";
		obj[1][3] = "Customer added successfully";

		return obj;
	}

}
