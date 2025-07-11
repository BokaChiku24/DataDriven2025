package com.kunal.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.kunal.utilities.ExtentManager;
import com.kunal.utilities.TestUtils;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestBase {

	/*
	 * Initializing 1. WebDriver - Done 2. Properties - Done 3. Logs - Not Done
	 * log4j, Application.log, Selenium.log 4. ExtentReports, ReportNG 5. DB 6.
	 * Excel - Not Done 7. Mail 8. Jenkins
	 */

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties or = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static WebDriverWait wait;
	public ExtentReports report = ExtentManager.getInstance();
	public static ExtentTest test;
	public static String browser;

	@BeforeTest
	public void setup() {

		if (driver == null) {
			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "/src/test/resources/properties/Config.properties");
				config.load(fis);
				log.debug("Config file loaded !");
				fis = new FileInputStream(
						System.getProperty("user.dir") + "/src/test/resources/properties/ObjectRepository.properties");
				or.load(fis);
				log.debug("Object repositories file loaded !");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (System.getenv("browser") != null && !System.getenv("browser").isEmpty()) {
			browser = System.getenv("browser");
		} else {

			browser = config.getProperty("browser");
		}
		config.setProperty("browser", browser);

		if (config.getProperty("browser").equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"/Users/kunalchavan/git/Selenium3_4/SeleniumDrivers/chromedriver");
			driver = new ChromeDriver();
			log.debug("Chrome launched !");
		} else if (config.getProperty("browser").equals("Firefox")) {
			System.setProperty("webdriver.gecko.driver",
					"//Users/kunalchavan/git/Selenium3_4/SeleniumDrivers/geckodriver");
			driver = new FirefoxDriver();
			log.debug("Firefox launched !");
		} else if (config.getProperty("browser").equals("Edge")) {
			System.setProperty("webdriver.edge.driver",
					"/Users/kunalchavan/git/Selenium3_4/SeleniumDrivers/msedgedriver");
			driver = new EdgeDriver();
			log.debug("Edge launched !");
		} else {
			System.out.println("Browser name not match in configuation properties file.");
			log.debug("Browser not launched !");
		}
		driver.get(config.getProperty("testsiteurl"));
		log.debug("Navigated to: " + config.getProperty("testsiteurl"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicitlyWait")),
				TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 20);

	}

	public boolean isElementPresent(String locator) {
		try {
			if (locator.endsWith("_CSS")) {
				driver.findElement(By.cssSelector(or.getProperty(locator))).isDisplayed();
				test.log(LogStatus.INFO, "Element visiable:  " + locator);
				return true;
			} else if (locator.endsWith("_XPATH")) {
				driver.findElement(By.xpath(or.getProperty(locator))).isDisplayed();
				test.log(LogStatus.INFO, "Clicking on:  " + locator);
				return true;
			} else if (locator.endsWith("_ID")) {
				driver.findElement(By.id(or.getProperty(locator))).isDisplayed();
				test.log(LogStatus.INFO, "Clicking on:  " + locator);
				return true;
			} else {
				return false;
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void alertAccept(WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	public void click(String locator) {
		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(or.getProperty(locator))).click();
			test.log(LogStatus.INFO, "Clicking on:  " + locator);
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(or.getProperty(locator))).click();
			test.log(LogStatus.INFO, "Clicking on:  " + locator);
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(or.getProperty(locator))).click();
			test.log(LogStatus.INFO, "Clicking on:  " + locator);
		}
	}

	public void clickXpath(String locator) {
		driver.findElement(By.xpath(or.getProperty(locator))).click();
		test.log(LogStatus.INFO, "Clicking on:  " + locator);

	}

	public void type(String locator, String value) {
		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(or.getProperty(locator))).sendKeys(value);
			test.log(LogStatus.INFO, "Keys send:  " + value);
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(or.getProperty(locator))).sendKeys(value);
			test.log(LogStatus.INFO, "Keys send:  " + value);
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(or.getProperty(locator))).sendKeys(value);
			test.log(LogStatus.INFO, "Keys send:  " + value);
		}
	}

	public boolean alertText(WebDriver driver, String text) {
		return driver.switchTo().alert().getText().contains(text);
	}

	public static WebElement dropdown;

	public void select(String locator, String value) {
		if (locator.endsWith("_CSS")) {
			dropdown = driver.findElement(By.cssSelector(or.getProperty(locator)));
		} else if (locator.endsWith("_XPATH")) {
			dropdown = driver.findElement(By.xpath(or.getProperty(locator)));
		} else if (locator.endsWith("_ID")) {
			dropdown = driver.findElement(By.id(or.getProperty(locator)));
		}
		Select select = new Select(dropdown);
		select.selectByVisibleText(value);
		test.log(LogStatus.INFO, "Select from dopdown:  " + locator + " Value as: " + value);

	}

	public static void verifyEquals(String expected, String actual) {
		try {
			Assert.assertEquals(actual, expected);
		} catch (Throwable t) {
			TestUtils.captureScreenshot();
			Reporter.log("<br>" + "Verification failure: " + t.getMessage() + "<br>");
			Reporter.log("<a target=\"_blank\" href=" + TestUtils.screenshotName + "><img src="
					+ TestUtils.screenshotName + " height=200 width=200></img></a>");
			Reporter.log("<br>");
			Reporter.log("<br>");
			test.log(LogStatus.FAIL, "Verificication failed with exception: " + t.getMessage());
			test.log(LogStatus.FAIL, test.addScreenCapture(TestUtils.screenshotName));

		}
	}

	@AfterTest
	public void tearDown() {
		if (driver != null)
			driver.quit();
		log.debug("Execution completed and driver instance is closed now !");
	}

}
