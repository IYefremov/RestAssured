  package com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens;

  import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.baseappscreens.RegularCarHistoryScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.baseappscreens.RegularCustomersScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.baseappscreens.RegularSettingsScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.typesscreens.*;
import com.cyberiansoft.test.ios10_client.utils.Helpers;
import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegularHomeScreen extends iOSRegularBaseScreen {
	
	/*@iOSFindBy(accessibility = "Customers")
    private IOSElement customersbtn;
	
	@iOSFindBy(accessibility = "My Inspections")
    private IOSElement myinspectionsbtn;
	
	@iOSFindBy(accessibility  = "Team Inspections")
    private IOSElement teaminspectionsbtn;
	
	@iOSFindBy(accessibility = "My Work Orders")
    private IOSElement myworkordersbtn;
	
	@iOSFindBy(accessibility = "Car History")
    private IOSElement carhistorybtn;
	
	@iOSFindBy(accessibility = "My Invoices")
    private IOSElement myinvoicesbtn;
	
	@iOSFindBy(accessibility = "Team Invoices")
    private IOSElement teaminvoicesbtn;
	
	@iOSFindBy(accessibility = "Service Requests")
    private IOSElement servicerequestsbtn;
	
	@iOSFindBy(accessibility = "Status")
    private IOSElement statustsbtn;
	
	@iOSFindBy(accessibility = "Team Work Orders")
    private IOSElement temworkorderstsbtn;
	
	@iOSFindBy(accessibility = "Settings")
    private IOSElement settingstsbtn;*/
	
	@iOSFindBy(accessibility = "logout")
    private IOSElement logoutbtn;

	@iOSFindBy(accessibility = "viewPrompt")
	private IOSElement activecustomer;
	
	public RegularHomeScreen() {
		super();
		PageFactory.initElements(new AppiumFieldDecorator(appiumdriver), this);
		WebDriverWait wait = new WebDriverWait(appiumdriver, 15);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(MobileBy.AccessibilityId("Home")));
	}

	public RegularCustomersScreen clickCustomersButton() {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(appiumdriver.findElementByAccessibilityId("Customers")));
		appiumdriver.findElementByAccessibilityId("Customers").click();
		return new RegularCustomersScreen();
	}

	public RegularMyInspectionsScreen clickMyInspectionsButton() {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(appiumdriver.findElementByAccessibilityId("Inspections")));
		appiumdriver.findElementByAccessibilityId("Inspections").click();
		RegularMyInspectionsScreen myinspectionsscreen = new RegularMyInspectionsScreen();
		myinspectionsscreen.switchToMyView();
		return myinspectionsscreen;
	}
	
	public RegularTeamInspectionsScreen clickTeamInspectionsButton() {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(appiumdriver.findElementByAccessibilityId("Inspections")));
		appiumdriver.findElementByAccessibilityId("Inspections").click();
		RegularMyInspectionsScreen myinspectionsscreen = new RegularMyInspectionsScreen();
		myinspectionsscreen.switchToTeamView();
		return new RegularTeamInspectionsScreen();
	}

	public RegularMyWorkOrdersScreen clickMyWorkOrdersButton() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(appiumdriver.findElementByAccessibilityId("Work Orders"))).click();
		RegularMyWorkOrdersScreen myworkordersscreen = new RegularMyWorkOrdersScreen();
		myworkordersscreen.switchToMyView();
		return myworkordersscreen;
	}
	
	public RegularCarHistoryScreen clickCarHistoryButton() {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(appiumdriver.findElementByAccessibilityId("Car History")));
		appiumdriver.findElementByAccessibilityId("Car History").click();
		return new RegularCarHistoryScreen();
	}

	public RegularMyInvoicesScreen clickMyInvoices() {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(appiumdriver.findElementByAccessibilityId("Invoices")));
		appiumdriver.findElementByAccessibilityId("Invoices").click();
		RegularMyInvoicesScreen invoicesscreen = new RegularMyInvoicesScreen();
		invoicesscreen.switchToMyView();
		return invoicesscreen;
	}
	
	public RegularTeamInvoicesScreen clickTeamInvoices() {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(appiumdriver.findElementByAccessibilityId("Invoices")));
		appiumdriver.findElementByAccessibilityId("Invoices").click();
		RegularMyInvoicesScreen invoicesscreen = new RegularMyInvoicesScreen();
		invoicesscreen.switchToTeamView();
		return new RegularTeamInvoicesScreen();
	}

	public RegularServiceRequestsScreen clickServiceRequestsButton() {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(appiumdriver.findElementByAccessibilityId("Service Requests")));
		appiumdriver.findElementByAccessibilityId("Service Requests").click();
		return new RegularServiceRequestsScreen();
	}
	
	public void clickStatusButton() {
		//WebDriverWait wait = new WebDriverWait(appiumdriver, 60);
		//wait.until(ExpectedConditions.elementToBeClickable(statustsbtn));
		appiumdriver.findElementByAccessibilityId("Status").click();
	}
	
	public RegularTeamWorkOrdersScreen clickTeamWorkordersButton() {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(appiumdriver.findElementByAccessibilityId("Work Orders"))).click();
		RegularMyWorkOrdersScreen myworkordersscreen = new RegularMyWorkOrdersScreen();
		myworkordersscreen.switchToTeamView();
		return new RegularTeamWorkOrdersScreen();
	}

	public RegularSettingsScreen clickSettingsButton() {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(appiumdriver.findElementByAccessibilityId("Service Requests")));
		swipeScreenUp();
		appiumdriver.findElementByAccessibilityId("Settings").click();
		return new RegularSettingsScreen();
	}	
	
	public void updateDatabase() {
		Helpers.setTimeOut(180);
		IOSElement toolbar = (IOSElement) appiumdriver.findElementByClassName("XCUIElementTypeToolbar");
		toolbar.findElementsByClassName("XCUIElementTypeButton").get(0).click();
		Helpers.acceptAlert();
		Helpers.setDefaultTimeOut();
	}
	
	public void updateVIN() {
		Helpers.setTimeOut(60);
		IOSElement toolbar = (IOSElement) appiumdriver.findElementByClassName("XCUIElementTypeToolbar");
		toolbar.findElementsByClassName("XCUIElementTypeButton").get(1).click();
		Helpers.acceptAlert();
		Helpers.setDefaultTimeOut();
	}

	public RegularMainScreen clickLogoutButton() {
		logoutbtn.click();
		return new RegularMainScreen();
	}

	public String getActiveCustomerValue() {
		return activecustomer.getAttribute("value");
	}

}
