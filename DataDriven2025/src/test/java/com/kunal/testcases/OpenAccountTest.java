package com.kunal.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.kunal.base.TestBase;

public class OpenAccountTest extends TestBase {

	@BeforeClass
	public void goToAccount() {
		 click("bmlBtn_XPATH");
		//wait.until(ExpectedConditions
		//		.elementToBeClickable(driver.findElement(By.cssSelector(or.getProperty("openAccount_CSS")))));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();",
				driver.findElement(By.cssSelector(or.getProperty("openAccount_CSS"))));
	}

	@Test
	public void openAccountTest() {
		select("customer_CSS", "Harry Potter");
		select("currency_CSS", "Dollar");
		click("submitCustomer_CSS");
		Reporter.log("Account open successfully");
		wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alertText(driver, "Account created successfully"));
		alertAccept(driver);
	}
}
