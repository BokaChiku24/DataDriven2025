package com.kunal.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;

import com.kunal.base.TestBase;
import com.kunal.utilities.TestUtils;
import com.relevantcodes.extentreports.LogStatus;

public class CustomListeners extends TestBase implements ITestListener {

	public void onTestStart(ITestResult result) {
		// not implemented
		Reporter.log("Test case start: " + result.getMethod().getMethodName());
		test = report.startTest(result.getName().toUpperCase());
		
		/*
		// Run modes - Y/N
		if(!TestUtils.isTestRunnable(result.getName(),excel)) {
			throw new SkipException("Skipping the test" + result.getName().toUpperCase() + " as run mode is set to No");
		}
		*/

	}

	public void onTestSuccess(ITestResult result) {
		// not implemented
		Reporter.log("Test case finished: " + result.getMethod().getMethodName());
		test.log(LogStatus.PASS, result.getName().toUpperCase() + " : Pass");
		report.endTest(test);
		report.flush();

	}

	public void onTestFailure(ITestResult result) {
		// not implemented
		System.setProperty("org.uncommons.reportng.escape-output", "false"); // To generate hyperlink under ReportNG
		Reporter.log("Captuing screenshot..");
		TestUtils.captureScreenshot();
		Reporter.log("Screenshot captured..");
		Reporter.log("<a target=\"_blank\" href=" + TestUtils.screenshotName + ">Screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\" href=" + TestUtils.screenshotName + "><img src =" + TestUtils.screenshotName
				+ " height=200 width=200></img></a>");
		test.log(LogStatus.FAIL,
				result.getName().toUpperCase() + " : Fail" + " : Exception is - " + result.getThrowable());
		test.log(LogStatus.FAIL, "Screenshot is: " + test.addScreenCapture(TestUtils.screenshotName));
	}

	public void onTestSkipped(ITestResult result) {
		// not implemented
		test.log(LogStatus.SKIP, result.getName().toUpperCase() + " Skipped the test case");
		report.endTest(test);
		report.flush();
	}

	public void onStart(ITestContext context) {
		// not implemented
	}

	public void onFinish(ITestContext context) {
		// not implemented
	}

}
