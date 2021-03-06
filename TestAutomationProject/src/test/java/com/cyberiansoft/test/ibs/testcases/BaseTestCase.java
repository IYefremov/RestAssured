package com.cyberiansoft.test.ibs.testcases;

import com.cyberiansoft.test.baseutils.BaseUtils;
import com.cyberiansoft.test.bo.config.BOConfigInfo;
import com.cyberiansoft.test.bo.pageobjects.webpages.BackOfficeHeaderPanel;
import com.cyberiansoft.test.core.BrowserType;
import com.cyberiansoft.test.core.WebDriverConfigInfo;
import com.cyberiansoft.test.driverutils.DriverBuilder;
import com.cyberiansoft.test.ibs.config.IBSConfigInfo;
import com.cyberiansoft.test.ibs.pageobjects.webpages.IBSDashboardPage;
import com.cyberiansoft.test.ibs.pageobjects.webpages.IBSLoginWebPage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;


public class BaseTestCase {

	protected WebDriver webdriver;
	public BrowserType browsertype;
	protected File app;
	
	@BeforeSuite
	public void cleanScreenShotsFolder() throws IOException{
        File reportFolder = new File("/allure-results");
        if (reportFolder.exists()) {
            FileUtils.deleteDirectory(reportFolder);
        }
        File videoFolder = new File("/video");
        if (videoFolder.exists()) {
            FileUtils.deleteDirectory(videoFolder);
        }
	}

    public WebDriver getWebDriver() {
		return webdriver;
	}

    @BeforeMethod
    public void ibsLogin(Method method) throws InterruptedException {
        browsertype = BaseUtils.getBrowserType(WebDriverConfigInfo.getInstance().getDefaultBrowser());
        DriverBuilder.getInstance()
                .setBrowserType(browsertype)
                .setRemoteWebDriverURL(WebDriverConfigInfo.getInstance().getAzureURL())
                .setDriver();
        webdriver = DriverBuilder.getInstance().getDriver();

        System.out.printf("\n* Starting test : %s Method : %s\n", getClass(), method.getName());
        goToWebPage(IBSConfigInfo.getInstance().getIbsUrl());
        IBSLoginWebPage loginPage = PageFactory.initElements(webdriver, IBSLoginWebPage.class);
        loginPage.UserLogin(IBSConfigInfo.getInstance().getUserName(), IBSConfigInfo.getInstance().getUserPassword());
        Thread.sleep(2000);
    }

    @AfterMethod
    public void ibsLogout(ITestResult result) {
        if (result.isSuccess()) {
            BackOfficeHeaderPanel backOfficeHeader = PageFactory.initElements(webdriver, BackOfficeHeaderPanel.class);
            IBSDashboardPage ibsDashboardPage = PageFactory.initElements(webdriver, IBSDashboardPage.class);
            if (!webdriver.getCurrentUrl().contains("reconpro")) {
                goToWebPage(BOConfigInfo.getInstance().getBackOfficeURL());
                try {
                    backOfficeHeader.clickLogout();
                } catch (WebDriverException ignored) {}
            }
            try {
                goToWebPage(IBSConfigInfo.getInstance().getIbsUrl());
                ibsDashboardPage.clickLogoutButton();
                webdriver.manage().deleteAllCookies();
            } catch (WebDriverException ignored) {}
        }
        DriverBuilder.getInstance().quitDriver();
    }

    @AfterSuite
    public void tearDown() {
        DriverBuilder.getInstance().quitDriver();
    }

	public void goToWebPage(String url) {
		webdriver.manage().window().maximize();
		webdriver.get(url);
	}

    public void setDriver() {
        webdriver = DriverBuilder.getInstance().getDriver();
    }
}