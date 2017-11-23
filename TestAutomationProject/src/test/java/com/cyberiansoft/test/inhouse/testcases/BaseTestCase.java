package com.cyberiansoft.test.inhouse.testcases;

import com.cyberiansoft.test.inhouse.utils.WebDriverInstansiator;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;


public class BaseTestCase {

	protected WebDriver webdriver;
	public String browsertype;
	protected File app;
	
	@BeforeSuite
	public void cleanScreenShotsFolder() throws IOException{
		FileUtils.cleanDirectory(new File(".//report")); 
	}
	
	@BeforeClass
	@Parameters({ "selenium.browser" })
	public void setUp(String browser) throws Exception {
		browsertype = browser;
		WebDriverInstansiator.setDriver(browser);
		webdriver = WebDriverInstansiator.getDriver();
		webdriver.navigate().refresh();
	}

	public WebDriver getWebDriver() {
		return webdriver;
	}

	@AfterMethod
	public void cookieCleaner(){
		webdriver.get("https://sc.cyberianconcepts.com");
		webdriver.manage().deleteAllCookies();
	}
	
	@AfterClass
	public void tearDown() throws Exception {

		if (webdriver != null)
			webdriver.quit();
	}

	public void webdriverGotoWebPage(String url) {
		webdriver.manage().window().maximize();
		webdriver.get(url);
	}
}