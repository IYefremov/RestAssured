package com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.typesscreens;

import com.cyberiansoft.test.baseutils.BaseUtils;
import com.cyberiansoft.test.ios10_client.appcontexts.TypeScreenContext;
import com.cyberiansoft.test.ios10_client.utils.AppiumWait;
import com.cyberiansoft.test.ios10_client.utils.Helpers;
import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class RegularTeamWorkOrdersScreen extends RegularBaseTypeScreenWithTabs {

	private final TypeScreenContext TEAMWOCONTEXT = TypeScreenContext.TEAMWORKORDER;
	
	private By discardbtnxpath = By.name("Discard");
	
	@iOSXCUITFindBy(accessibility = "Monitor")
    private IOSElement womonitor;
	
	@iOSXCUITFindBy(accessibility = "Search")
    private IOSElement searchbtn;
	
	@iOSXCUITFindBy(accessibility = "Edit")
    private IOSElement editmanu;
	
	@iOSXCUITFindBy(accessibility = "invoice new")
    private IOSElement invoicenewbtn;
	
	@iOSXCUITFindBy(accessibility = "Location")
    private IOSElement locationfld;

	@iOSXCUITFindBy(accessibility = "Save")
    private IOSElement searccsavebtn;

	@iOSXCUITFindBy(accessibility = "Approve")
    private IOSElement approvebtn;

	@iOSXCUITFindBy(accessibility = "TeamOrdersTable")
	private IOSElement teamOrdersTable;
	
	public RegularTeamWorkOrdersScreen() {
		super();
		PageFactory.initElements(new AppiumFieldDecorator(appiumdriver), this);
	}

	public void waitTeamWorkOrdersScreenLoaded() {
		FluentWait<WebDriver> wait = new WebDriverWait(appiumdriver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("TeamOrdersTable")));

	}

	public void clickCreateInvoiceForWO(String workOrderID) {
		waitTeamWorkOrdersScreenLoaded();
		teamOrdersTable.findElementByClassName("XCUIElementTypeTable").findElement(MobileBy.AccessibilityId(workOrderID))
				.findElement(MobileBy.className("XCUIElementTypeOther")).click();
	}
	
	public void clickOnWO(String workOrderID) {
		waitTeamWorkOrdersScreenLoaded();
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId(workOrderID))).click();
	}
	
	public void selectWOMonitor() {
		womonitor.click();
	}
	
	public void selectEditWO() {
		editmanu.click();
	}
	
	public void verifyCreateInvoiceIsActivated(String workOrderID) {
		Assert.assertTrue(teamOrdersTable.findElement(MobileBy.AccessibilityId(workOrderID))
				.findElements(MobileBy.iOSNsPredicateString("type = 'XCUIElementTypeOther' and name CONTAINS 'EntityInfoButtonChecked'")).size() > 0);
		Assert.assertTrue(appiumdriver.findElementsByAccessibilityId("invoice new").size() > 0);
	}

	public void clickCreateInvoiceIconForWO(String workOrderNumber)  {
		teamOrdersTable.findElement(MobileBy.AccessibilityId(workOrderNumber))
				.findElement(MobileBy.AccessibilityId("EntityInfoButtonUnchecked, ButtonImageId_79")).click();
	}
	
	public void clickiCreateInvoiceButton()  {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.visibilityOf(invoicenewbtn));
		invoicenewbtn.click();
	}	
	
	public void clickSearchButton() {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(searchbtn)).click();
	}
	
	public void clickSearchSaveButton() {
		searccsavebtn.click();
	}
	
	public void selectSearchLocation(String _location) {
		locationfld.click();
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Locations")));
		wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId(_location)));
		appiumdriver.findElementByClassName("XCUIElementTypeTable").findElement(MobileBy.AccessibilityId(_location)).click();
	}
	
	public void setSearchType(String wotype)  {
		appiumdriver.findElementByXPath("//XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeStaticText[@name='Type']").click();
		selectWorkOrderType(wotype);
	}
	
	public void selectWorkOrderForApprove(String workOrderID) {
		teamOrdersTable.findElementByAccessibilityId(workOrderID).findElement(MobileBy.iOSNsPredicateString("type = 'XCUIElementTypeOther' and name CONTAINS 'EntityInfoButton'")).click();
	}
	
	public void approveWorkOrder(String workOrderID, String employee, String pwd) {
		selectWorkOrderForApprove(workOrderID);
		selectEmployeeAndTypePassword(employee, pwd);
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(MobileBy.iOSNsPredicateString("type = 'XCUIElementTypeNavigationBar' and name = 'Summary'"))).click();
		approvebtn.click();
	}
	
	public void selectWorkOrderType(String workordertype) {

		if (Helpers.elementExists(discardbtnxpath)) {
			appiumdriver.findElement(discardbtnxpath).click();
		}
		if (! appiumdriver.
				findElement(By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeStaticText[@name='" + workordertype + "']")).isDisplayed()) {
			swipeToElement(appiumdriver.
				findElement(By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeStaticText[@name='" + workordertype + "']/..")));
			appiumdriver.
				findElement(By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeStaticText[@name='" + workordertype + "']")).click();
		}
	}
	
	public void selectEmployeeAndTypePassword(String employee, String password) {
		selectEmployee( employee);
		((IOSElement) appiumdriver.findElementByAccessibilityId("Enter password here")).setValue(password);
		Helpers.acceptAlert();

		if (appiumdriver.findElementsByAccessibilityId("Connecting to Back Office").size() > 0) {
			WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(MobileBy.AccessibilityId("Loading team order")));
		}
	}
	
	public void selectEmployee(String employee) {
		appiumdriver.findElementByName(employee).click();
	}
	
	public void setFilterLocation(String _location)  {
		appiumdriver.findElementByAccessibilityId("Location").click();
		appiumdriver.findElementByAccessibilityId(_location).click();
	}

	public void setBilling(String billingValue)  {
		appiumdriver.findElementByAccessibilityId("Billing").click();
		BaseUtils.waitABit(500);
		appiumdriver.findElementByAccessibilityId(billingValue).click();
	}

	public void setFilterCustomer(String companyName)  {
		appiumdriver.findElementByAccessibilityId("Customer").click();
		BaseUtils.waitABit(500);
		WebDriverWait wait = new WebDriverWait(appiumdriver, 15);
		wait.until(ExpectedConditions.visibilityOf(appiumdriver.findElementByAccessibilityId(companyName)));
		appiumdriver.findElementByAccessibilityId(companyName).click();
	}
	
	public void clickSaveFilter() {
		appiumdriver.findElementByAccessibilityId("Save").click();
	}
	
	public boolean isWorkOrderExists(String workOrderID) {
		waitTeamWorkOrdersScreenLoaded();
		FluentWait<WebDriver> wait = new WebDriverWait(appiumdriver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("TeamOrdersTable")));
		return teamOrdersTable.findElements(MobileBy.AccessibilityId(workOrderID)).size() > 0;
	}
	
	public boolean isWorkOrderHasApproveIcon(String workOrderID) {
		waitTeamWorkOrdersScreenLoaded();
		return teamOrdersTable.findElement(MobileBy.AccessibilityId(workOrderID)).findElements(MobileBy.iOSNsPredicateString("name CONTAINS 'ButtonImageId_78'")).size() > 0;
	}
	
	public boolean isWorkOrderHasActionIcon(String workOrderID) {
		waitTeamWorkOrdersScreenLoaded();
		return teamOrdersTable.findElement(MobileBy.AccessibilityId(workOrderID)).findElements(MobileBy.iOSNsPredicateString("name CONTAINS 'ButtonImageId_79'")).size() > 0;
	}
	
	public String getFirstWorkOrderNumberValue() {		
		return teamOrdersTable.findElement(By.xpath("//XCUIElementTypeCell[1]/XCUIElementTypeStaticText[@name='labelOrderNumber']")).getAttribute("label");
	}
	
	public void selectWorkOrderForEidt(String workOrderID) {
		selectWorkOrder(workOrderID);
		appiumdriver.findElementByAccessibilityId("Edit").click();
	}
	
	public void selectWorkOrder(String workOrderID) {
		waitTeamWorkOrdersScreenLoaded();
		AppiumWait.getGeneralFluentWait(60, 500).until(driver -> {
			teamOrdersTable.findElementByAccessibilityId(workOrderID).click();
			return true;
		});
		//teamOrdersTable.findElementByAccessibilityId(workOrderID).click();

	}

	public void clickBackButton()  {
		FluentWait<WebDriver> wait = new WebDriverWait(appiumdriver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(MobileBy.name("Back"))).click();
	}
}
