package com.cyberiansoft.test.bo.testcases;

import com.cyberiansoft.test.baseutils.BaseUtils;
import com.cyberiansoft.test.bo.config.BOConfigInfo;
import com.cyberiansoft.test.core.BrowserType;
import com.cyberiansoft.test.core.MobilePlatform;
import com.cyberiansoft.test.driverutils.DriverBuilder;
import com.cyberiansoft.test.ios_client.utils.Helpers;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.apache.commons.io.FileUtils;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;

public class BaseTestCase {

	private ScreenRecorder screenRecorder;
	protected AppiumDriver<MobileElement> appiumdriver;
	protected WebDriver webdriver;
	protected DesiredCapabilities appiumcap;
	protected DesiredCapabilities webcap;
	protected static BrowserType browsertype;
	protected static MobilePlatform mobilePlatform = MobilePlatform.IOS_REGULAR;
	protected File app;
	String bundleid = "";
	protected static AppiumDriverLocalService service;
	
	@BeforeSuite
	public void cleanScreenShotsFolder() throws IOException{
		File reportFolder = new File("/report");
		if (!reportFolder.exists())
			reportFolder.mkdir();
		else
			FileUtils.cleanDirectory(new File("/report"));
	}
	
//	@BeforeClass
//	@Parameters({ "selenium.browser", "ios.bundleid" })
//	public void setUp(@Optional("chrome") String browser, String bundleid) throws Exception {
//		/*
//		 * GraphicsConfiguration gc = GraphicsEnvironment
//		 * .getLocalGraphicsEnvironment().getDefaultScreenDevice()
//		 * .getDefaultConfiguration();
//		 *
//		 *
//		 * screenRecorder = new ScreenRecorder(gc, new Format(MediaTypeKey,
//		 * MediaType.FILE, MimeTypeKey, MIME_AVI), new Format( MediaTypeKey,
//		 * MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
//		 * CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey,
//		 * (int) 24, FrameRateKey, Rational.valueOf(15), QualityKey, 1.0f,
//		 * KeyFrameIntervalKey, (int) (15 * 60)), new Format(MediaTypeKey,
//		 * MediaType.VIDEO, EncodingKey, "black", FrameRateKey,
//		 * Rational.valueOf(30)), null); screenRecorder.start();
//		 * System.out.println("++++++++++" + screenRecorder.getState().name());
//		 * System.
//		 *
//		 *
//		 *
//		 * out.println("++++++++++" +
//		 * screenRecorder.getCreatedMovieFiles().get(0).getPath());
//		 */
//
////		service = new AppiumServiceBuilder().withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
////				 .usingAnyFreePort().withArgument(SESSION_OVERRIDE)
////				 .withArgument(LOG_LEVEL, "error")
////				 .build();
////	        service.start();
////
////	    if (service == null || !service.isRunning()) {
////	    	throw new AppiumServerHasNotBeenStartedLocallyException("An appium server node is not started!");
////	    }
//	    this.bundleid =  bundleid;
//		browsertype = BaseUtils.getBrowserType(browser);
//
//		DriverBuilder.getInstance().setDriver(browsertype);
//		webdriver = DriverBuilder.getInstance().getDriver();
//		webdriver.navigate().refresh();
//	}

	@BeforeClass
	public void setUp() {
		browsertype = BaseUtils.getBrowserType(BOConfigInfo.getInstance().getDefaultBrowser());
		DriverBuilder.getInstance().setDriver(browsertype);
		webdriver = DriverBuilder.getInstance().getDriver();
		webdriver.navigate().refresh();
	}

	public static WebElement wait(By locator) {
		return Helpers.wait(locator);
	}

	@AfterMethod
	public void cookieCleaner(){
		DriverBuilder.getInstance().getDriver().get("https://reconpro.cyberianconcepts.com/");
		DriverBuilder.getInstance().getDriver().manage().deleteAllCookies();
	}
	
	@AfterClass
	public void tearDown() {
		if (DriverBuilder.getInstance().getDriver() != null)
			DriverBuilder.getInstance().getDriver().quit();
		if (DriverBuilder.getInstance().getAppiumDriver() != null)
			DriverBuilder.getInstance().getAppiumDriver().quit();
	}

}
