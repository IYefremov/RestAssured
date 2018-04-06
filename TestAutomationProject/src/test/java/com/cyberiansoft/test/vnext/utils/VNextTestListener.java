package com.cyberiansoft.test.vnext.utils;

import com.cyberiansoft.test.baseutils.AppiumUtils;
import com.cyberiansoft.test.driverutils.DriverBuilder;
import com.cyberiansoft.test.vnext.screens.VNextHomeScreen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.WebDriver;
import org.testng.*;


public class VNextTestListener extends TestListenerAdapter implements IInvokedMethodListener  {
	
	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		String filename = "";
		AppiumDriver<MobileElement> appiumdriver = DriverBuilder.getInstance().getAppiumDriver();
		WebDriver webdriver = DriverBuilder.getInstance().getDriver();
	    if (appiumdriver != null) {
	    	//filename = AppiumUtils.createScreenshot("reportvnext/" + ExtentReportFactory.reporttime + "/", "failed" + getTestMethodName(result));
	    }
	    
	    if (webdriver != null) {
	    	webdriver.quit();
	    }
	    
	    /*if (appiumdriver.findElements(By.xpath("//div[@data-page='null']")).size() < 1) {
	    	AppiumUtils.setNetworkOn();
	    	((VNextBaseTestCase) currentClass).resetApp();
	    	((VNextBaseTestCase) currentClass).setUp();
	    	//((VNextBaseTestCase) currentClass).setNetworkOn();
	    	try {
	    		((VNextBaseTestCase) currentClass).registerDevice();
	    	} catch (Exception e) {
	    		// TODO Auto-generated catch block
	    		e.printStackTrace();
	    	}
	    	try {
	    		//((VNextBaseTestCase) currentClass).registerDevice();
	    	} catch (Exception e) {
	    		// TODO Auto-generated catch block
	    		e.printStackTrace();
	    	}
	    }*/
	    AppiumUtils.setNetworkOn();
	    VNextAppUtils.restartApp();
	    VNextHomeScreen homescreen = new VNextHomeScreen(appiumdriver);
	}
	
	@Override
	public void onTestSkipped (ITestResult result) {
		AppiumDriver<MobileElement> driver = DriverBuilder.getInstance().getAppiumDriver();
	    if (driver != null) {
	    	//AppiumUtils.createScreenshot("reportvnext/" + ExtentReportFactory.reporttime + "/", "skipped" + getTestMethodName(result));
	    }
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {

	}

	@Override
	public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {
		// TODO Auto-generated method stub
		
	}
	
	private static String getTestMethodName(ITestResult result) {
		return result.getMethod().getConstructorOrMethod().getName();
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		//ExtentReportFactory.getTest(getTestMethodName(result), getTestName(result));
	}
	
	@Override
	public void onStart(ITestContext context) {
		
	}
	
	@Override
	public void onFinish(ITestContext context) {
	}
	
	private static String getTestName(ITestResult result) {
		return result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(org.testng.annotations.Test.class).testName();
	}
	
	private static String getTestDescription(ITestResult result) {
		return result.getMethod().getDescription();
	}

}
