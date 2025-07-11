package com.kunal.testcases;

import org.testng.annotations.Test;

import com.kunal.base.BaseTest;
import com.kunal.utilities.DataUtil;

public class LoginTest extends BaseTest {

	@Test(dataProviderClass = DataUtil.class, dataProvider = "dp1")
	public void doLogin(String username, String password) throws InterruptedException {

		type("username_ID", username);
		Thread.sleep(2000);
	}

}
