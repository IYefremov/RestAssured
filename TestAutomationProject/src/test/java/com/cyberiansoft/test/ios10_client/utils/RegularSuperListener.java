package com.cyberiansoft.test.ios10_client.utils;

import com.cyberiansoft.test.core.MobilePlatform;
import com.cyberiansoft.test.driverutils.AppiumInicializator;
import com.cyberiansoft.test.driverutils.DriverBuilder;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.RegularMainScreen;
import com.cyberiansoft.test.ios10_client.testcases.BaseTestCase;
import com.cyberiansoft.test.ios_client.utils.TestUser;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.*;

public class RegularSuperListener extends TestListenerAdapter  implements IInvokedMethodListener  {
	private Object currentClass;
	
	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		
		currentClass = testResult.getInstance();
		/*if (method.getTestMethod().getConstructorOrMethod().getMethod().isAnnotationPresent(org.testng.annotations.BeforeClass.class)) {
			if (loggerdir == null) {
				Date now = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
				time = dateFormat.format(now);
				loggerdir = "report/" + time;
				File dir = new File(loggerdir);
				dir.mkdirs();
			}
			extentreport = new ExtentReports("report/" + time + "/UCTestReport.html", false, DisplayOrder.OLDEST_FIRST);
			((AndroidBaseTestCase) currentClass).setExtentReport(extentreport);
		}*/

		//System.out.println("+++++++++++"  +   method.getTestMethod().getConstructorOrMethod().getMethod().getAnnotation(org.testng.annotations.Test.class).testName());
		//if  (method.getTestMethod().getConstructorOrMethod().getMethod().getAnnotation(org.testng.annotations.Test.class) != null) {
		//	testName = method.getTestMethod().getConstructorOrMethod().getMethod().getAnnotation(org.testng.annotations.Test.class).testName();
		//}
		//testDescription = method.getTestMethod().getDescription();
		//ExtentReports extent = ((AndroidBaseTestCase) currentClass).getExtentReport();
		//testlogger = extentreport.startTest(method.getTestMethod().getMethodName(), method.getTestMethod().getDescription());
		//((AndroidBaseTestCase) currentClass).setExtentTest(testlogger);
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {    
        if ((method.getTestMethod().getConstructorOrMethod().getMethod().isAnnotationPresent(org.testng.annotations.BeforeClass.class)) 
        		&& (method.getTestResult().getStatus()==2)) {
        	AppiumDriver<MobileElement> appiumdriver = DriverBuilder.getInstance().getAppiumDriver();
        	
        	if (appiumdriver != null) {
        		appiumdriver.quit();
        	} else {

        	}
        }
		
		
		
		
        /*ExtentReports extent = ((AndroidBaseTestCase) currentClass).getExtentReport();
        extent.endTest(testlogger);
        extent.flush();
        System.out.println("+++++++++++" + method.getTestMethod().getMethodName());
        if (method.getTestResult().getStatus() == 2) {    
        	if (appiumdriver != null)
        		testlogger.log(LogStatus.FAIL, "Something wrong", testlogger.addScreenCapture(((AndroidBaseTestCase) currentClass).createScreenshot(appiumdriver)));
        	else 
        		testlogger.log(LogStatus.FAIL, "Something wrong");
        } else if (method.getTestResult().getStatus() == 3) {
        	testlogger.log(LogStatus.SKIP , method.getTestMethod().getMethodName());
        } else {
        	testlogger.log(LogStatus.PASS , method.getTestMethod().getMethodName());
        }
        if ((method.getTestMethod().getConstructorOrMethod().getMethod().isAnnotationPresent(org.testng.annotations.BeforeClass.class)) 
        		&& (method.getTestResult().getStatus()==2)) {
        	if (appiumdriver != null)
        		appiumdriver.quit();
        }
        
        ExtentReports extent = ((AndroidBaseTestCase) currentClass).getExtentReport();
        extent.endTest(testlogger);
        extent.flush();*/
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		AppiumDriver<MobileElement> appiumdriver = DriverBuilder.getInstance().getAppiumDriver();
	        if (appiumdriver != null) {
	        	try {
	        		//testlogger.log(LogStatus.FAIL, LogAssertions.stepMessage, testlogger.addScreenCapture(((BaseTestCase) currentClass).createScreenshot(appiumdriver, iOSLogger.loggerdir)));
	        	} catch (Exception e) {
	        		AppiumInicializator.getInstance().initAppium(MobilePlatform.IOS_REGULAR);
	        	}
	        	
	        }
	        DriverBuilder.getInstance().getAppiumDriver().closeApp();
	        DriverBuilder.getInstance().getAppiumDriver().launchApp();

        	RegularMainScreen mainscr = new RegularMainScreen(appiumdriver);
    		try {
    			TestUser testuser = ((BaseTestCase) currentClass).getTestUser();
    			mainscr.userLogin(testuser.getTestUserName(), testuser.getTestUserPassword());
    			
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {

	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
	}

	
	@Override
	public void onTestStart(ITestResult result) {

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
	
	private static String getTestMethodName(ITestResult result) {
		return result.getMethod().getConstructorOrMethod() .getName();
	}





/*public class SuperListener extends TestListenerAdapter  implements IInvokedMethodListener  {
	private Object currentClass;
	private ExtentReports extentreport;
	private ExtentTest testlogger;
	private String loggerdir;
	private String time;
	
	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		currentClass = testResult.getInstance();
		if (method.getTestMethod().getConstructorOrMethod().getMethod().isAnnotationPresent(org.testng.annotations.BeforeClass.class)) {
			if (loggerdir == null) {
				Date now = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
				time = dateFormat.format(now);
				loggerdir = "report/" + time;
				File dir = new File(loggerdir);
				dir.mkdirs();
			}
			extentreport = new ExtentReports("report/" + time + "/UCTestReport.html", false, DisplayOrder.OLDEST_FIRST);
			((BaseTestCase) currentClass).setExtentReport(extentreport);
		}

		//System.out.println("+++++++++++"  +   method.getTestMethod().getConstructorOrMethod().getMethod().getAnnotation(org.testng.annotations.Test.class).testName());
		//if  (method.getTestMethod().getConstructorOrMethod().getMethod().getAnnotation(org.testng.annotations.Test.class) != null) {
		//	testName = method.getTestMethod().getConstructorOrMethod().getMethod().getAnnotation(org.testng.annotations.Test.class).testName();
		//}
		//testDescription = method.getTestMethod().getDescription();
		//ExtentReports extent = ((BaseTestCase) currentClass).getExtentReport();
		//testlogger = extentreport.startTest(method.getTestMethod().getMethodName(), method.getTestMethod().getDescription());
		//((BaseTestCase) currentClass).setExtentTest(testlogger);
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {	
		if (!method.getTestMethod().getConstructorOrMethod().getMethod().isAnnotationPresent(org.testng.annotations.BeforeSuite.class)) {
		AppiumDriver appiumdriver = ((BaseTestCase) currentClass).getAppiumDriver();
        if ((method.getTestMethod().getConstructorOrMethod().getMethod().isAnnotationPresent(org.testng.annotations.BeforeClass.class)) 
        		&& (method.getTestResult().getStatus()==2)) {
        	
        	
        	if (appiumdriver != null) {
        		testlogger = extentreport.startTest(method.getTestMethod().getMethodName());
        		testlogger.log(LogStatus.FAIL, "Something wrong", testlogger.addScreenCapture(((BaseTestCase) currentClass).createScreenshot(appiumdriver, loggerdir)));
        		appiumdriver.quit();
        		extentreport = ((BaseTestCase) currentClass).getExtentReport();
        		extentreport.endTest(testlogger);
        		extentreport.flush();
        	} else {
        		testlogger = extentreport.startTest(method.getTestMethod().getMethodName());
        		testlogger.log(LogStatus.FAIL, "Something wrong");
        		extentreport = ((BaseTestCase) currentClass).getExtentReport();
        		extentreport.endTest(testlogger);
        		extentreport.flush();
        	}
        }
		}
        
      
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		AppiumDriver appiumdriver = ((BaseTestCase) currentClass).getAppiumDriver();
	        testlogger= ((BaseTestCase) currentClass).getExtentTest();
	        if (appiumdriver != null) {
	        	testlogger.log(LogStatus.FAIL, "FAIL!!!!!!!!!!!!! ALARM", testlogger.addScreenCapture(((BaseTestCase) currentClass).createScreenshot(appiumdriver, loggerdir)));        
	        }
        	extentreport = ((BaseTestCase) currentClass).getExtentReport();
        	extentreport.endTest(testlogger);
        	extentreport.flush();
        	
        	try {
				((BaseTestCase) currentClass).resrtartApplication();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	MainScreen mainscr = new MainScreen(appiumdriver);
    		try {
    			TestUser testuser = ((BaseTestCase) currentClass).getTestUser();
    			((BaseTestCase) currentClass).homescreen = mainscr.userLogin(testuser.getTestUserName(), testuser.getTestUserPassword());
    			
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		//currentClass = result.getInstance();
        //AppiumDriver<MobileElement> appiumdriver = ((BaseTestCase) currentClass).getAppiumDriver();
        testlogger= ((BaseTestCase) currentClass).getExtentTest();
		System.out.println("test method " + getTestMethodName(result) + " skipped");
		testlogger.log(LogStatus.SKIP , "Test Case Skipped", getTestName(result) + " skipped");
		extentreport = ((BaseTestCase) currentClass).getExtentReport();
		extentreport.endTest(testlogger);
		extentreport.flush();
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		testlogger.log(LogStatus.PASS , "Test Case Finished", getTestName(result) + " passed");
		extentreport = ((BaseTestCase) currentClass).getExtentReport();
		extentreport.endTest(testlogger);
		extentreport.flush();
	}

	
	@Override
	public void onTestStart(ITestResult result) {
		currentClass = result.getInstance();
		testlogger = extentreport.startTest(getTestName(result), getTestDescription(result));
		((BaseTestCase) currentClass).setExtentTest(testlogger);
		System.out.println("test method " + getTestName(result) + " started");
		//test.log(LogStatus.SKIP , result.getMethod().getConstructorOrMethod().getName());
	}
	
	@Override
	public void onStart(ITestContext context) {
		
	}
	
	@Override
	public void onFinish(ITestContext context) {
		if (extentreport != null) {
			extentreport.flush();
			extentreport.close();			
		}
		System.out.println("on finish of test " + context.getName());
	}
	
	private static String getTestName(ITestResult result) {
		return result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(org.testng.annotations.Test.class).testName();
	}
	
	private static String getTestDescription(ITestResult result) {
		return result.getMethod().getDescription();
	}
	
	private static String getTestMethodName(ITestResult result) {
		return result.getMethod().getConstructorOrMethod() .getName();
	}*/

}

