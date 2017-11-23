package com.cyberiansoft.test.inhouse.utils;

import com.cyberiansoft.test.inhouse.testcases.BaseTestCase;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


public class TestListener extends TestListenerAdapter  implements IInvokedMethodListener  {
	private Object currentClass;
	
	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		
		currentClass = testResult.getInstance();
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
	        WebDriver driver = ((BaseTestCase) currentClass).getWebDriver();
	        if (driver != null) {
	        	createScreenshot(driver, "report/", getTestMethodName(result));
	        }
	}
	
	@Override
	public void onTestSkipped (ITestResult result) {
	        WebDriver driver = ((BaseTestCase) currentClass).getWebDriver();
	        if (driver != null) {
	        }
	}
	
	public String createScreenshot(WebDriver driver, String loggerdir, String testcasename) {
		WebDriver driver1 = new Augmenter().augment(driver);
		UUID uuid = UUID.randomUUID();
		File file = ((TakesScreenshot) driver1).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(file , new File(loggerdir + "\\" + testcasename + uuid + ".jpeg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "myscreen" + uuid + ".jpeg";
	}

	@Override
	public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {
	}
	
	private static String getTestMethodName(ITestResult result) {
		return result.getMethod().getConstructorOrMethod().getName();
	}
	
	

}
