package com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.baseappscreens;

import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.RegularCarHistoryWOsAndInvoicesScreen;
import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegularCarHistoryScreen extends RegularBaseAppScreen {
	
	@iOSXCUITFindBy(accessibility = "Search")
    private IOSElement searchbtn;
	
	@iOSXCUITFindBy(xpath = "//UIAPopover[1]/UIASearchBar[1]")
    private IOSElement searchbar;
	
	@iOSXCUITFindBy(accessibility = "Close")
    private IOSElement closesearchbtn;

	@iOSXCUITFindBy(accessibility = "Switch to online")
    private IOSElement switchtowebbtn;
	
	public RegularCarHistoryScreen() {
		super();
		PageFactory.initElements(new AppiumFieldDecorator(appiumdriver), this);
	}
	
	public void searchCar(String vin) {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(searchbtn)).click();
		IOSElement searchfld = (IOSElement) appiumdriver.findElement(MobileBy.iOSNsPredicateString("name = 'Search' and type = 'XCUIElementTypeSearchField'"));
		searchfld.click();
		searchfld.sendKeys(vin + "\n");	
	}
	
	public RegularCarHistoryWOsAndInvoicesScreen clickFirstCarHistoryInTable() {
		appiumdriver.findElement(MobileBy.xpath("//XCUIElementTypeTable[1]/XCUIElementTypeCell[1]")).click();
		return new RegularCarHistoryWOsAndInvoicesScreen();
	}
	
	public RegularCarHistoryWOsAndInvoicesScreen clickCarHistoryRowByVehicleInfo(String vehicleinfo) {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId(vehicleinfo)));
		swipeToElement(appiumdriver.findElement(MobileBy.xpath("//XCUIElementTypeTable[1]/XCUIElementTypeCell/XCUIElementTypeStaticText[@name='" + vehicleinfo + "']")));
		appiumdriver.findElement(MobileBy.xpath("//XCUIElementTypeTable[1]/XCUIElementTypeCell/XCUIElementTypeStaticText[@name='" + vehicleinfo + "']")).click();
		return new RegularCarHistoryWOsAndInvoicesScreen();
	}
	
	public String getFirstCarHistoryValueInTable() {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("//XCUIElementTypeTable[1]/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[1]")));
		return appiumdriver.findElementByXPath("//XCUIElementTypeTable[1]/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[1]").getAttribute("value");
	}
	
	public String getFirstCarHistoryDetailsInTable() {		
		return appiumdriver.findElementByXPath("//XCUIElementTypeTable[1]/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[2]").getAttribute("value");
	}
	
	public void clickSwitchToWeb() {		
		switchtowebbtn.click();
	}
	


	public void clickBackButton() {
		FluentWait<WebDriver> wait = new WebDriverWait(appiumdriver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(MobileBy.name("Back"))).click();

	}

}
