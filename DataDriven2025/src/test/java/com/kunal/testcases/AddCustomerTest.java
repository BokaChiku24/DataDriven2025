package com.kunal.testcases;

import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.kunal.base.BaseTest;
import com.kunal.utilities.DataUtil;

public class AddCustomerTest extends BaseTest {

	@Test(dataProviderClass = DataUtil.class, dataProvider = "dp1")
	public void addCustomer(String firstName, String lastName, String postCode) {
		
		click("addCustomerBtn_XPATH");
		type("firstname_XPATH",firstName);
		type("lastname_XPATH",lastName);
		type("postcode_XPATH",postCode);
		click("addcust_XPATH");
		
		Alert alert = driver.switchTo().alert();
		Assert.assertTrue(alert.getText().contains("Customer added successfully"));
		
		alert.accept();
	}

	/*
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
	*/
}
