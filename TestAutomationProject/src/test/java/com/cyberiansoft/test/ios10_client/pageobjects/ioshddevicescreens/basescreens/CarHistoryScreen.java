package com.cyberiansoft.test.ios10_client.pageobjects.ioshddevicescreens.basescreens;

import com.cyberiansoft.test.ios10_client.pageobjects.ioshddevicescreens.typesscreens.MyInvoicesScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.ioshddevicescreens.typesscreens.TeamInvoicesScreen;
import com.cyberiansoft.test.ios10_client.utils.Helpers;
import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CarHistoryScreen extends BaseAppScreen {
	
	@iOSXCUITFindBy(accessibility = "Search")
    private IOSElement searchbtn;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeSearchBar[1]")
    private IOSElement searchbar;
	
	@iOSXCUITFindBy(accessibility = "Close")
    private IOSElement closesearchbtn;
	
	@iOSXCUITFindBy(accessibility  = "Switch to online")
    private IOSElement switchtowebbtn;
	
	@iOSXCUITFindBy(accessibility  = "Invoices")
    private IOSElement invoicesmenu;
	
	@iOSXCUITFindBy(accessibility  = "Work Orders")
    private IOSElement myworkordersmenumenu;
	
	public CarHistoryScreen() {
		super();
		PageFactory.initElements(new AppiumFieldDecorator(appiumdriver, Duration.ofSeconds(10)), this);
	}
	
	public void searchCar(String car) {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Search")));
		searchbtn.click();
		appiumdriver.getKeyboard().pressKey(car);
		closesearchbtn.click();
		//wait = new WebDriverWait(appiumdriver, 60);
		//wait.until(ExpectedConditions.elementToBeClickable(closesearchbtn)).click();
	}
	
	public void clickFirstCarHistoryInTable() {		
		appiumdriver.findElementByXPath("//XCUIElementTypeTable[1]/XCUIElementTypeCell[1]").click();
	}
	
	public String getFirstCarHistoryValueInTable() {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(MobileBy.className("XCUIElementTypeTable")));
		final int carinfocount =  appiumdriver.findElementByXPath("//XCUIElementTypeTable[1]/XCUIElementTypeCell[1]").findElements(MobileBy.className("XCUIElementTypeStaticText")).size();
		String VIN = "";
		if (carinfocount == 4)
			VIN = appiumdriver.findElementByXPath("//XCUIElementTypeTable[1]/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[4]").getAttribute("name");
		else
			VIN = appiumdriver.findElementByXPath("//XCUIElementTypeTable[1]/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[3]").getAttribute("name");
		return VIN;
	}
	
	public String getFirstCarHistoryDetailsInTable() {		
		return appiumdriver.findElementByXPath("//XCUIElementTypeTable[1]/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[1]").getAttribute("name");
	}
	
	public void clickFirstCar() {		
		appiumdriver.findElementByXPath("//XCUIElementTypeTable[1]/XCUIElementTypeCell[1]").click();
	}
	
	public void clickSwitchToWeb() {		
		switchtowebbtn.click();
	}
	
	public MyInvoicesScreen clickCarHistoryInvoices() {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(invoicesmenu)).click();
		return new MyInvoicesScreen();
	}

	public TeamInvoicesScreen clickCarHistoryTeamInvoices() {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(invoicesmenu)).click();
		return new TeamInvoicesScreen();
	}
	
	public void clickCarHistoryMyWorkOrders() {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(myworkordersmenumenu)).click();
		Helpers.waitABit(1000);
	}

}
