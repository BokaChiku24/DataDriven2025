package com.kunal.utilities;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.kunal.base.TestBase;

public class TestUtils extends TestBase {

	public static String screenshotPath;
	public static String screenshotName;

	public static void captureScreenshot() {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Date d = new Date();

		screenshotName = d.toString().replace(":", "_").replace(" ", "_") + "error.jpg";
		try {
			FileUtils.copyFile(src,
					new File(System.getProperty("user.dir") + "/target/surefire-reports/html/" + screenshotName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	@DataProvider(name="dp")
	public Object[][] getData(Method m){
		String sheetName = m.getName();
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);

		Object[][] data = new Object[rows - 1][1];
		Hashtable<String, String> table = null;
		for(int rowNum = 2; rowNum <= rows; rowNum++) {
			table = new Hastable<String, String>();
			for(int colNum = 0; colNum<cols; colNum++) {
				table.put(excel.getCellData(sheetName, colNum, 1),excel.getCellData(sheetName, colNum, rowNum));
				data[rows-2][cols] = table;
				data[rows-2][0] = excel.getCellData(sheetName, colNum, rowNum)
			}
		}
		return data;
	}
	*/
	
	/*
	public static boolean isTestRunnable(String testName, ExcelReader excel) {
	String sheetName = "test_suite";
		int row = excel.getRowCount(sheetName);
		
		for(int rowNum = 2; rowNum<= row ; rowNum++){
			String testCase = excel.getCellData(sheetName, "TCID", rowNum);
			if(testCase.equalsIgnoreCase(testName)){
			String runMode = excel.getCellData(sheetName, "Runmode", rowNum);
			if(runMode.equalsIgnoreCase("Y"))
			return true;
			else
			return false;
			}
		}
	}
	
	*/

}
