package com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens;

import java.util.concurrent.TimeUnit;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.ios.IOSTouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cyberiansoft.test.ios10_client.utils.Helpers;

public class RegularMyWorkOrdersScreen extends iOSRegularBaseScreen {
	
	private By discardbtnxpath = By.name("Discard");
	
	private By autosavedworkorder = By.xpath("//XCUIElementTypeTable[@name='MyWorkOrdersTable']/XCUIElementTypeCell/XCUIElementTypeOther[@name='EntityInfoButtonUnchecked, AutoSaved']");
	
	/*private By btnwholesale = By.name("btnWholesale");
	private By btnretail = By.name("btnRetail");
	
	@iOSFindBy(accessibility = "Search")
    private IOSElement searchbtn;
	
	@iOSFindBy(accessibility = "Add")
    private IOSElement addinspbtn;
	
	@iOSFindBy(accessibility = "Discard")
    private IOSElement discardbtn;
	
	@iOSFindBy(accessibility= "Copy\nVehicle")
    private IOSElement copyvehiclemenu;
	
	@iOSFindBy(accessibility = "Copy\nServices")
    private IOSElement copyservicesmenu;
	
	@iOSFindBy(accessibility = "Change\nCustomer")
    private IOSElement changecustomermenu;
	
	@iOSFindBy(accessibility = "Details")
    private IOSElement detailsmenu;
	
	@iOSFindBy(accessibility = "New\nInspection")
    private IOSElement newinspectionmenu;
	
	@iOSFindBy(accessibility = "Create\nInvoices")
    private IOSElement createinvoicesmenu;
	
	@iOSFindBy(accessibility = "Continue")
    private IOSElement continuemenu;
	
	@iOSFindBy(accessibility = "Discard")
    private IOSElement discardmenu;
	
	@iOSFindBy(accessibility = "Notes")
    private IOSElement notesmenu;
	
	@iOSFindBy(accessibility  = "Tech Revenue")
    private IOSElement techrevenuebtn;
	
	@iOSFindBy(accessibility  = "Technicians")
    private IOSElement techniciansmenubtn;
	
	@iOSFindBy(xpath = "//XCUIElementTypeTable[1]/XCUIElementTypeCell[1]")
    private IOSElement firswointable;
	
	@iOSFindBy(accessibility = "Share")
    private IOSElement sharebtn;
	
	//@iOSFindBy(xpath = "//UIAButton[@visible=\"true\" and (contains(@name,\"filter\"))] ")
    //private IOSElement filterbtn;
	
	@iOSFindBy(accessibility = "filter")
    private IOSElement filterbtn;
	
	//@iOSFindBy(xpath = "//UIAButton[@visible=\"true\" and (contains(@name,\"Done\"))] ")
    //private IOSElement donebtn;
	
	@iOSFindBy(accessibility = "Done")
    private IOSElement donebtn;
	
	@iOSFindBy(accessibility = "invoice new")
    private IOSElement newinvoice;
	
	@iOSFindBy(accessibility = "Delete")
    private IOSElement deletebtn;
	
	@iOSFindBy(accessibility = "Approve")
    private IOSElement approvebtn;
	
	@iOSFindBy(accessibility = "Edit")
    private IOSElement editbtn;
	
	@iOSFindBy(accessibility = "Enter password here")
    private IOSElement securefld;
	
	@iOSFindBy(accessibility = "Service Request")
    private IOSElement servicerequestbtn;*/

	public RegularMyWorkOrdersScreen(AppiumDriver driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		appiumdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		FluentWait<WebDriver> wait = new WebDriverWait(appiumdriver, 15);
		wait.until(ExpectedConditions.elementToBeClickable(By.name("MyWorkOrdersTable")));
	}

	public void clickAddOrderButton() {
		/*Helpers.waitABit(5000);
		System.out.println("++++" + appiumdriver.findElementsByClassName("XCUIElementTypeNavigationBar").size());
		//System.out.println("++++" + appiumdriver.findElementByClassName("XCUIElementTypeNavigationBar").findElements(MobileBy.AccessibilityId("Add")).size());
		//System.out.println("++++" + appiumdriver.findElementsByClassName("XCUIElementTypeNavigationBar").size());
		System.out.println("++++" + appiumdriver.findElementsByXPath("//XCUIElementTypeNavigationBar/XCUIElementTypeButton").size());
		appiumdriver.findElementByClassName("XCUIElementTypeNavigationBar").findElement(MobileBy.AccessibilityId("Add")).click();*/
		appiumdriver.findElementByAccessibilityId("Add").click();
		if (elementExists("Discard")) {
			appiumdriver.findElementByAccessibilityId("Discard").click();
		}		
	}
	
	public IOSElement getAddOrderButton() {	
		WebDriverWait wait = new WebDriverWait(appiumdriver, 20);
		return (IOSElement) wait.until(ExpectedConditions.elementToBeClickable(appiumdriver.findElementByAccessibilityId("Add")));
	}

	public void selectFirstOrder() {
		WebElement table = appiumdriver.findElementByAccessibilityId("MyWorkOrdersTable");
		table.findElement(By.xpath("//XCUIElementTypeTable[1]/XCUIElementTypeCell[1]")).click();
		//firswointable.click();
	}

	public void selectCopyServices() {
		appiumdriver.findElementByAccessibilityId("Copy\nServices").click();
	}
	
	public void selectCopyVehicle() {
		appiumdriver.findElementByAccessibilityId("Copy\nVehicle").click();
	}
	
	public void clickChangeCustomerPopupMenu() {
		appiumdriver.findElementByAccessibilityId("Change\nCustomer").click();
	}
	
	public void clickDetailspopupMenu() {
		appiumdriver.findElementByAccessibilityId("Details").click();
	}
	
	public void selectCustomer(String customer) {
		/*appiumdriver.findElementByAccessibilityId("Search").click();
		Helpers.waitABit(1000);
		///XCUIElementTypeSearchField[@name="Search"]
		appiumdriver.getKeyboard().sendKeys(customer);*/
		appiumdriver.findElementByAccessibilityId(customer).click();
	}
	
	public void changeCustomerForWorkOrder(String wonumber, String customer) {
		selectWorkOrder(wonumber);
		clickChangeCustomerPopupMenu();
		Helpers.waitABit(1000);
		selectCustomer(customer);
	}
	
	public void customersPopupSwitchToWholesailMode() throws InterruptedException {
		if (elementExists(MobileBy.AccessibilityId("btnRetail"))) {
			appiumdriver.findElementByAccessibilityId("btnRetail").click();
		}
	}
	
	public void customersPopupSwitchToRetailMode() throws InterruptedException {
		if (elementExists(MobileBy.AccessibilityId("btnWholesale"))) {
			appiumdriver.findElementByAccessibilityId("btnWholesale").click();
		}
	}
	
	public void openWorkOrderDetails(String wonumber) {
		selectWorkOrder(wonumber);
		clickDetailspopupMenu();
		
	}
	
	public void deleteWorkOrderViaAction(String wo) {
		clickActionButton();
		selectWorkOrderForAction(wo);
		clickActionButton();
		clickDeleteButton();
		Helpers.acceptAlert();
		clickDoneButton();
	}
	
	public void clickSearchutton() {
		appiumdriver.findElementByAccessibilityId("Search").click();
	}
	
	public void deleteWorkOrderViaActionAndSearch(String wo) throws InterruptedException {
	
		clickActionButton();
		selectWorkOrderForAction(wo);
		clickActionButton();
		appiumdriver.findElementByAccessibilityId("Delete").click();
		Helpers.acceptAlert();
		clickDoneButton();
	}
	
	public void clickActionButton() {
		appiumdriver.findElementByAccessibilityId("Share").click();		
	}
	
	public void clickFilterButton() {
		if (appiumdriver.findElementsByAccessibilityId("filter").size() < 1)
			appiumdriver.findElementByAccessibilityId("filter pressed").click();
		else
			appiumdriver.findElementByAccessibilityId("filter").click();		
	}
	
	public void clickDoneButton() {
		appiumdriver.findElementByAccessibilityId("Done").click();
		//appiumdriver.tap(1, donebtn, 200);		
	}
	
	public void clickDeleteButton() {
		appiumdriver.findElementByAccessibilityId("Delete").click();
	}
	
	public boolean isAutosavedWorkOrderExists() {	
		return elementExists(autosavedworkorder);
	}
	
	public void selectWorkOrderForAction(String wonumber) {
		appiumdriver.findElementByClassName("XCUIElementTypeTable").
		findElement(MobileBy.xpath("//XCUIElementTypeCell[@name='" + wonumber + "']/XCUIElementTypeOther")).click();
		//appiumdriver.tap(1, appiumdriver.findElementByXPath("//UIATableView[1]/UIATableCell[@name=\"" + wo
			//			+ "\"]/UIAButton[contains(@name, \"EntityInfoButtonUnchecked\")] "), 200);
	}
	
	public void selectWorkOrderForApprove(String wonumber) {
		FluentWait<WebDriver> wait = new WebDriverWait(appiumdriver, 5);
		WebElement wotable = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("MyWorkOrdersTable"))); 
		wotable.findElement(MobileBy.xpath("//XCUIElementTypeCell[@name='" + wonumber + "']/XCUIElementTypeOther")).click();
		
	}
	
	public void selectEmployeeAndTypePassword(String employee, String password) {
		selectEmployee( employee);
		((IOSElement) appiumdriver.findElementByAccessibilityId("Enter password here")).setValue(password);
		Helpers.acceptAlert();
		Helpers.waitABit(1000);
		if (appiumdriver.findElementsByAccessibilityId("Connecting to Back Office").size() > 0) {
			WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(MobileBy.AccessibilityId("Loading team order")));
		}
		Helpers.waitABit(1000);
	}
	
	public void selectEmployee(String employee) {
		appiumdriver.findElementByName(employee).click();
	}
	
	public void approveWorkOrder(String wo, String employee, String pwd) {
		selectWorkOrderForApprove(wo);
		selectEmployeeAndTypePassword(employee, pwd);
		clickApproveButton();
	}
	
	public void clickApproveButton() {
		appiumdriver.findElementByAccessibilityId("Approve").click();
	}
	
	public void selectWorkOrder(String wonumber) {
		new WebDriverWait(appiumdriver, 10)
				  .until(ExpectedConditions.visibilityOf(appiumdriver.findElementByAccessibilityId(wonumber))).click();

	}
	
	public void selectWorkOrderForEidt(String wo) throws InterruptedException {
		selectWorkOrder(wo);
		appiumdriver.findElementByAccessibilityId("Edit").click();
		if (appiumdriver.findElementsByAccessibilityId("Connecting to Back Office").size() > 0) {
			WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(MobileBy.AccessibilityId("Loading team order")));
		}
		Helpers.waitABit(1000);
	}
	
	public void selectWorkOrderForCopyVehicle(String wo) throws InterruptedException {
		selectWorkOrder(wo);
		appiumdriver.findElementByAccessibilityId("Copy\nVehicle").click();
	}
	
	public void selectWorkOrderForAddingNotes(String wo) throws InterruptedException {
		selectWorkOrder(wo);
		appiumdriver.findElementByAccessibilityId("Notes").click();
	}
	
	public void selectWorkOrderForCopyServices(String wo) throws InterruptedException {
		selectWorkOrder(wo);
		appiumdriver.findElementByAccessibilityId("Copy\nServices").click();
	}
	
	public void clickCreateInvoicesMenuItemForWO(String wonum) throws InterruptedException {
		selectWorkOrder(wonum);
		appiumdriver.findElementByAccessibilityId("Create\nInvoices").click();
		Helpers.acceptAlert();
	}
	
	public RegularTechRevenueScreen selectWorkOrderTechRevenueMenuItem(String wo) {		
		selectWorkOrder(wo);
		appiumdriver.findElementByAccessibilityId("Tech Revenue").click();
		return new RegularTechRevenueScreen(appiumdriver);
	}
	
	public RegularSelectedServiceDetailsScreen selectWorkOrderTechniciansMenuItem(String wo) {		
		selectWorkOrder(wo);
		appiumdriver.findElementByAccessibilityId("Technicians").click();
		return new RegularSelectedServiceDetailsScreen(appiumdriver);
	}
	
	public void selectWorkOrderNewInspection(String wonum) throws InterruptedException {
		selectWorkOrder(wonum);
		appiumdriver.findElementByAccessibilityId("New\nInspection").click();
	}
	
	public void selectContinueWorkOrder(String wonum) throws InterruptedException {
		selectWorkOrder(wonum);
		appiumdriver.findElementByAccessibilityId("Continue").click();
	}
	
	public void selectDiscardWorkOrder(String wonum) throws InterruptedException {
		selectWorkOrder(wonum);
		appiumdriver.findElementByAccessibilityId("Discard").click();
	}
	
	public void clickCreateInvoiceIconForWOViaSearch(String wonum) {
		clickCreateInvoiceIconForWO(wonum);
	}

	public void clickCreateInvoiceIconForWO(String wonumber)  {
		new WebDriverWait(appiumdriver, 10)
		  .until(ExpectedConditions.elementToBeClickable(appiumdriver.findElementByAccessibilityId(wonumber)));
		appiumdriver.findElementByClassName("XCUIElementTypeTable").
		findElement(MobileBy.xpath("//XCUIElementTypeCell[@name='" + wonumber + "']/XCUIElementTypeOther")).click();
		//appiumdriver.tap(1, appiumdriver.findElement(MobileBy.xpath("//UIATableView[@name=\"MyWorkOrdersTable\"]/UIATableCell[@name = \"" + wo + "\"]/UIAButton[contains(@name, \"EntityInfoButtonUnchecked\")]")), 200);
		//appiumdriver.findElement(MobileBy.xpath("//UIATableView[@name=\"MyWorkOrdersTable\"]/UIATableCell[@name = \"" + wo + "\"]/UIAButton[contains(@name, \"EntityInfoButtonUnchecked\")]")).click();
		//appiumdriver.findElement(MobileBy.xpath("//UIATableView[@name=\"MyWorkOrdersTable\"]/UIATableCell[@name = \"" + wo + "\"]/UIAButton[@name=\"EntityInfoButtonUnchecked\"]")).click();
		//appiumdriver.findElement(MobileBy.IosUIAutomation(".tableViews()[\"MyWorkOrdersTable\"].cells()[\"" + wo + "\"].buttons()[\"EntityInfoButtonUnchecked\"]")).click();
	}
	
	public void clickCreateInvoiceIconForWOs(String[] wos) throws InterruptedException {
		for (int i = 0; i < wos.length; i++) {
			clickCreateInvoiceIconForWO(wos[i]);

		}
	}
	
	public String getPriceValueForWO(String wonumber) {
		return appiumdriver.findElementByClassName("XCUIElementTypeTable").
		findElement(MobileBy.xpath("//XCUIElementTypeCell[@name='" + wonumber + "']/XCUIElementTypeStaticText[contains(@value, \"$\")]")).getAttribute("label");
	}
	
	public boolean woExists(String wonumber) {
		return appiumdriver.findElements(MobileBy.AccessibilityId(wonumber)).size() > 0;	
	}
	
	public boolean isWorkOrderHasApproveIcon(String wonumber) {
		return appiumdriver.findElements(MobileBy.xpath("//XCUIElementTypeCell[@name='" + wonumber + "']/XCUIElementTypeOther[contains(@name, 'ButtonImageId_78')]")).size() > 0;
	}
	
	public boolean isWorkOrderHasActionIcon(String wonumber) {
		return appiumdriver.findElements(MobileBy.xpath("//XCUIElementTypeCell[@name='" + wonumber + "']/XCUIElementTypeOther[contains(@name, 'ButtonImageId_79')]")).size() > 0;
	}
	
	/*public void clickCancelSearchButton() throws InterruptedException {
		appiumdriver.findElement(MobileBy.IosUIAutomation(".elements()['OrdersSearchBar'].buttons()['Cancel']")).click();
	}
	
	public boolean searchWO(String wo) {
		searchbtn.click();
		appiumdriver.findElement(MobileBy.IosUIAutomation(".elements()['OrdersSearchBar'].searchBars()['Search']")).sendKeys(wo.substring(6, wo.length()));
		appiumdriver.findElement(MobileBy.xpath("//UIAKeyboard[1]/UIAButton[@name=\"Search\"]")).click();
		appiumdriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		return appiumdriver.findElements(MobileBy.IosUIAutomation(".tableViews()['MyWorkOrdersTable'].cells()['" + wo + "']")).size() > 0;		
	}*/

	public void clickInvoiceIcon() {
		appiumdriver.findElementByAccessibilityId("invoice new").click();
	}

	public RegularInvoiceInfoScreen selectInvoiceType(String invoicetype) {
		if (!appiumdriver.findElementByAccessibilityId(invoicetype).isDisplayed()) {
		swipeToElement(appiumdriver.
				findElement(By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeStaticText[@name='" + invoicetype + "']/..")));
		appiumdriver.findElementByAccessibilityId(invoicetype).click();
		}
		appiumdriver.findElementByAccessibilityId(invoicetype).click();
		return new RegularInvoiceInfoScreen(appiumdriver);
	}

	public String selectInvoiceTypeAndAcceptAlert(String invoicetype) throws InterruptedException {
		selectInvoiceType(invoicetype);
		return Helpers.getAlertTextAndAccept();
	}

	public void selectWorkOrderType(String workordertype) {

		if (Helpers.elementExists(discardbtnxpath)) {
			appiumdriver.findElement(discardbtnxpath).click();
		}
		IOSElement wostable = (IOSElement) appiumdriver.findElement(MobileBy.iOSNsPredicateString("name = 'OrderTypeSelector' and type = 'XCUIElementTypeTable'"));

		if (!wostable.findElementByAccessibilityId(workordertype).isDisplayed()) {
			swipeToElement(wostable.findElementByAccessibilityId(workordertype));
			//wostable.click();
		}
		wostable.findElementByAccessibilityId(workordertype).click();
		
		
		
		/*if (! appiumdriver.
				findElement(By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeStaticText[@name='" + workordertype + "']")).isDisplayed()) {
			swipeToElement(appiumdriver.
				findElement(By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeStaticText[@name='" + workordertype + "']/..")));
			IOSTouchAction iostouch = new IOSTouchAction(appiumdriver);
			iostouch.tap(appiumdriver.
				findElement(By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeStaticText[@name='" + workordertype + "']"))).perform();
		}
		appiumdriver.
				findElement(By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeStaticText[@name='" + workordertype + "']")).click();*/
	}

	public  void selectWorkOrderJob(String job) {
		appiumdriver.findElementByName(job).click();
	}
	
	public void setFilterBilling(String billing)  {
		appiumdriver.findElementByName("Billing").click();
		Helpers.waitABit(500);
		appiumdriver.findElementByName(billing).click();
	}
	
	public void setFilterLocation(String _location)  {
		appiumdriver.findElementByAccessibilityId("Location").click();
		Helpers.waitABit(500);
		appiumdriver.findElementByAccessibilityId(_location).click();
	}
	
	public void clickSaveFilter() {
		clickSaveButton();
	}
	
	public boolean isMenuItemForSelectedWOExists(String menuitem) {
		return elementExists(MobileBy.AccessibilityId(menuitem));
	}
	
	public void clickServiceRequestButton() {
		appiumdriver.findElementByAccessibilityId("Service Request").click();
	}
	
	public WebElement getWorkOrderTableParentNode(String wonumber) {
		return appiumdriver.findElement(MobileBy.xpath("//XCUIElementTypeTable[1]/XCUIElementTypeCell/XCUIElementTypeStaticText[@value='" + wonumber + "']/.."));
	}
	
	public RegularVehicleScreen selectJob(String workorderjob) {
		appiumdriver.findElementByName(workorderjob).click();
		return new RegularVehicleScreen(appiumdriver);
	}
	
	public String getFirstWorkOrderNumberValue() {		
		return appiumdriver.findElement(By.xpath("//XCUIElementTypeTable[1]/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[@name='labelOrderNumber']")).getAttribute("label");
	}
	
	public void switchToLocalWorkOrdersView() {
		appiumdriver.findElementByAccessibilityId("Local").click();		
	}
	
	public void switchToOnlineWorkOrdersView() {
		appiumdriver.findElementByAccessibilityId("Online").click();		
	}
}
