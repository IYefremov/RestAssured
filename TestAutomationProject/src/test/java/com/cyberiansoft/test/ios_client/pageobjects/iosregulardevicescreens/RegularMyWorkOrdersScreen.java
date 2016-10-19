package com.cyberiansoft.test.ios_client.pageobjects.iosregulardevicescreens;

import java.util.concurrent.TimeUnit;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cyberiansoft.test.ios_client.utils.Helpers;

public class RegularMyWorkOrdersScreen extends iOSRegularBaseScreen {
	
	private By discardbtnxpath = By.name("Discard");
	
	private By autosavedworkorder = By.xpath("//UIATableView[1]/UIATableCell[1]/UIAButton[@label=\"autoSaved\"]");
	
	private By btnwholesale = By.name("btnWholesale");
	private By btnretail = By.name("btnRetail");
	
	@iOSFindBy(uiAutomator = ".navigationBar().buttons()[\"Search\"]")
    private IOSElement searchbtn;
	
	@iOSFindBy(uiAutomator = ".navigationBar().buttons()[\"Add\"]")
    private IOSElement addinspbtn;
	
	@iOSFindBy(accessibility  = "Discard")
    private IOSElement discardbtn;
	
	@iOSFindBy(xpath = "//UIAScrollView[2]/UIAButton[@name=\"Copy\nVehicle\"]")
    private IOSElement copyvehiclemenu;
	
	@iOSFindBy(xpath = "//UIAScrollView[2]/UIAButton[@name=\"Copy\nServices\"]")
    private IOSElement copyservicesmenu;
	
	@iOSFindBy(xpath = "//UIAScrollView[2]/UIAButton[@name=\"Change\nCustomer\"]")
    private IOSElement changecustomermenu;
	
	@iOSFindBy(accessibility  = "Details")
    private IOSElement detailsmenu;
	
	@iOSFindBy(xpath = "//UIAScrollView[2]/UIAButton[@name=\"New\nInspection\"]")
    private IOSElement newinspectionmenu;
	
	@iOSFindBy(xpath = "//UIAScrollView[2]/UIAButton[@name=\"Create\nInvoices\"]")
    private IOSElement createinvoicesmenu;
	
	@iOSFindBy(accessibility  = "Continue")
    private IOSElement continuemenu;
	
	@iOSFindBy(accessibility  = "Discard")
    private IOSElement discardmenu;
	
	@iOSFindBy(accessibility  = "Notes")
    private IOSElement notesmenu;
	
	@iOSFindBy(xpath = "//UIATableView[1]/UIATableCell[1]")
    private IOSElement firswointable;
	
	@iOSFindBy(uiAutomator = ".toolbars()[0].buttons()['Share']")
    private IOSElement sharebtn;
	
	//@iOSFindBy(xpath = "//UIAButton[@visible=\"true\" and (contains(@name,\"filter\"))] ")
    //private IOSElement filterbtn;
	
	@iOSFindBy(uiAutomator = ".toolbars()[0].buttons().firstWithPredicate(\"name LIKE 'filter*' \")")
    private IOSElement filterbtn;
	
	//@iOSFindBy(xpath = "//UIAButton[@visible=\"true\" and (contains(@name,\"Done\"))] ")
    //private IOSElement donebtn;
	
	@iOSFindBy(xpath = "//UIAButton[@name='Done']")
    private IOSElement donebtn;
	
	@iOSFindBy(uiAutomator = ".toolbars()[0].buttons()['invoice new']")
    private IOSElement newinvoice;
	
	@iOSFindBy(accessibility  = "Delete")
    private IOSElement deletebtn;
	
	@iOSFindBy(accessibility  = "Approve")
    private IOSElement approvebtn;
	
	@iOSFindBy(accessibility  = "Edit")
    private IOSElement editbtn;
	
	@iOSFindBy(xpath = "//UIASecureTextField[@value=\"Enter password here\"]")
    private IOSElement securefld;
	
	@iOSFindBy(accessibility  = "Service Request")
    private IOSElement servicerequestbtn;

	public RegularMyWorkOrdersScreen(AppiumDriver driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver, 10, TimeUnit.SECONDS), this);
		appiumdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public void clickAddOrderButton() throws InterruptedException {	
		WebDriverWait wait = new WebDriverWait(appiumdriver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(addinspbtn));
		addinspbtn.click();
		if (isAlertExists()) {
			declineAlertByCoords();
			Thread.sleep(1000);
		}		
	}
	
	public IOSElement getAddOrderButton() {	
		WebDriverWait wait = new WebDriverWait(appiumdriver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(addinspbtn));
		return addinspbtn;
	}

	public void selectFirstOrder() {
		firswointable.click();
	}

	public void selectCopyServices() {
		copyservicesmenu.click();
	}
	
	public void selectCopyVehicle() {
		copyvehiclemenu.click();
	}
	
	public void clickChangeCustomerPopupMenu() {
		changecustomermenu.click();
	}
	
	public void clickDetailspopupMenu() {
		detailsmenu.click();
	}
	
	public void selectCustomer(String customer) {
		appiumdriver.findElementByName(customer).click();
	}
	
	public void changeCustomerForWorkOrder(String wonumber, String customer) {
		selectWorkOrder(wonumber);
		clickChangeCustomerPopupMenu();
		selectCustomer(customer);
	}
	
	public void customersPopupSwitchToWholesailMode() throws InterruptedException {
		if (elementExists(btnretail)) {
			appiumdriver.findElement(btnretail).click();
		}
	}
	
	public void customersPopupSwitchToRetailMode() throws InterruptedException {
		if (elementExists(btnwholesale)) {
			appiumdriver.findElement(btnwholesale).click();
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
	
	public void deleteWorkOrderViaActionAndSearch(String wo) throws InterruptedException {
		
		searchbtn.click();
		appiumdriver.findElement(MobileBy.IosUIAutomation(".elements()['OrdersSearchBar'].searchBars()['Search']")).sendKeys(wo.substring(6, wo.length()));
		//appiumdriver.findElement(By.name("Search")).click();
		appiumdriver.findElement(MobileBy.xpath("//UIAKeyboard[1]/UIAButton[@name='Search']")).click();
		
		clickActionButton();
		selectWorkOrderForAction(wo);
		clickActionButton();
		appiumdriver.findElementByXPath("//UIAScrollView[2]/UIAButton[@name=\"Delete\"]").click();
		Helpers.acceptAlert();
		clickDoneButton();
	}
	
	public void clickActionButton() {
		sharebtn.click();		
	}
	
	public void clickFilterButton() {
		filterbtn.click();		
	}
	
	public void clickDoneButton() {
		appiumdriver.tap(1, donebtn, 200);		
	}
	
	public void clickDeleteButton() {
		deletebtn.click();
	}
	
	public boolean isAutosavedWorkOrderExists() {	
		return elementExists(autosavedworkorder);
	}
	
	public void selectWorkOrderForAction(String wo) {
		appiumdriver.tap(1, appiumdriver.findElementByXPath("//UIATableView[1]/UIATableCell[@name=\"" + wo
						+ "\"]/UIAButton[contains(@name, \"EntityInfoButtonUnchecked\")] "), 200);
	}
	
	public void selectWorkOrderForApprove(String wo) {
		appiumdriver.tap(1, appiumdriver.findElementByXPath("//UIATableView[1]/UIATableCell[@name=\"" + wo
						+ "\"]/UIAButton[contains(@name, \"EntityInfoButtonUnchecked\")] "), 200);
		//appiumdriver.findElementByXPath("//UIATableView[1]/UIATableCell[@name=\"" + wo
		//				+ "\"]/UIAButton[contains(@name, \"EntityInfoButtonUnchecked\")] ").click();
	}
	
	public void selectEmployeeAndTypePassword(String employee, String password) {
		selectEmployee( employee);
		securefld.setValue(password);
		Helpers.acceptAlert();
	}
	
	public void selectEmployee(String employee) {
		appiumdriver.findElementByName(employee).click();
	}
	
	public void approveWorkOrder(String wo, String employee, String pwd) {
		selectWorkOrderForApprove(wo);
		selectEmployeeAndTypePassword(employee, pwd);
		approvebtn.click();
	}
	
	public void selectWorkOrder(String wo) {
		//appiumdriver.findElementByXPath("//UIATableView[@name=\"MyWorkOrdersTable\"]/UIATableCell[@name='" + wo + "']").click();
		appiumdriver.tap(1, appiumdriver.findElement(MobileBy.IosUIAutomation(".tableViews()['MyWorkOrdersTable'].cells()['" + wo + "']")), 300);
	}
	
	public void selectWorkOrderForEidt(String wo) throws InterruptedException {
		searchWO(wo);
		selectWorkOrder(wo);
		editbtn.click();
	}
	
	public void selectWorkOrderForCopyVehicle(String wo) throws InterruptedException {
		searchWO(wo);
		selectWorkOrder(wo);
		copyvehiclemenu.click();
	}
	
	public void selectWorkOrderForAddingNotes(String wo) throws InterruptedException {
		searchWO(wo);
		selectWorkOrder(wo);
		notesmenu.click();
	}
	
	public void selectWorkOrderForCopyServices(String wo) throws InterruptedException {
		searchWO(wo);
		selectWorkOrder(wo);
		copyservicesmenu.click();
	}
	
	public void clickCreateInvoicesMenuItemForWO(String wonum) throws InterruptedException {
		searchWO(wonum);
		selectWorkOrder(wonum);
		createinvoicesmenu.click();
		Helpers.acceptAlert();
	}
	
	public void selectWorkOrderNewInspection(String wonum) throws InterruptedException {
		searchWO(wonum);
		selectWorkOrder(wonum);
		newinspectionmenu.click();
	}
	
	public void selectContinueWorkOrder(String wonum) throws InterruptedException {
		searchWO(wonum);
		selectWorkOrder(wonum);
		continuemenu.click();
	}
	
	public void selectDiscardWorkOrder(String wonum) throws InterruptedException {
		searchWO(wonum);
		selectWorkOrder(wonum);
		discardmenu.click();
	}
	
	public void clickCreateInvoiceIconForWOViaSearch(String wonum) throws InterruptedException {
		searchWO(wonum);
		clickCreateInvoiceIconForWO(wonum);
	}

	public void clickCreateInvoiceIconForWO(String wo) throws InterruptedException {
		appiumdriver.tap(1, appiumdriver.findElement(MobileBy.xpath("//UIATableView[@name=\"MyWorkOrdersTable\"]/UIATableCell[@name = \"" + wo + "\"]/UIAButton[contains(@name, \"EntityInfoButtonUnchecked\")]")), 200);
		//appiumdriver.findElement(MobileBy.xpath("//UIATableView[@name=\"MyWorkOrdersTable\"]/UIATableCell[@name = \"" + wo + "\"]/UIAButton[contains(@name, \"EntityInfoButtonUnchecked\")]")).click();
		//appiumdriver.findElement(MobileBy.xpath("//UIATableView[@name=\"MyWorkOrdersTable\"]/UIATableCell[@name = \"" + wo + "\"]/UIAButton[@name=\"EntityInfoButtonUnchecked\"]")).click();
		//appiumdriver.findElement(MobileBy.IosUIAutomation(".tableViews()[\"MyWorkOrdersTable\"].cells()[\"" + wo + "\"].buttons()[\"EntityInfoButtonUnchecked\"]")).click();
	}
	
	public void clickCreateInvoiceIconForWOsViaSearch(String[] wos) throws InterruptedException {
		IOSElement addbtn = getAddOrderButton();

		for (int i = 0; i < wos.length; i++) {
			searchbtn.click();
			appiumdriver.findElement(MobileBy.IosUIAutomation(".elements()['OrdersSearchBar'].searchBars()['Search']")).sendKeys(wos[i].substring(6, wos[i].length()));
			//appiumdriver.findElement(By.name("Search")).click();
			appiumdriver.findElement(MobileBy.xpath("//UIAKeyboard[1]/UIAButton[@name='Search']")).click();
			clickCreateInvoiceIconForWO(wos[i]);
			
			clickCancelSearchButton();
		}
	}
	
	public String getPriceValueForWO(String wo) {
		return appiumdriver.findElementByXPath("//UIATableView[1]/UIATableCell[@name= \""
						+ wo
						+ "\"]/UIAStaticText[6]").getAttribute("name");
	}
	
	public boolean woExistsViaSearch(String wo) throws InterruptedException {
		searchbtn.click();
		appiumdriver.findElement(MobileBy.IosUIAutomation(".elements()['OrdersSearchBar'].searchBars()['Search']")).sendKeys(wo.substring(6, wo.length()));
		//appiumdriver.findElement(By.name("Search")).click();
		appiumdriver.findElement(MobileBy.xpath("//UIAKeyboard[1]/UIAButton[@name='Search']")).click();
		return appiumdriver.findElements(MobileBy.IosUIAutomation(".tableViews()['MyWorkOrdersTable'].cells()['" + wo + "']")).size() > 0;	
	}
	
	public void clickCancelSearchButton() throws InterruptedException {
		appiumdriver.findElement(MobileBy.IosUIAutomation(".elements()['OrdersSearchBar'].buttons()['Cancel']")).click();
	}
	
	public boolean searchWO(String wo) {
		searchbtn.click();
		appiumdriver.findElement(MobileBy.IosUIAutomation(".elements()['OrdersSearchBar'].searchBars()['Search']")).sendKeys(wo.substring(6, wo.length()));
		appiumdriver.findElement(MobileBy.xpath("//UIAKeyboard[1]/UIAButton[@name=\"Search\"]")).click();
		appiumdriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		return appiumdriver.findElements(MobileBy.IosUIAutomation(".tableViews()['MyWorkOrdersTable'].cells()['" + wo + "']")).size() > 0;		
	}

	public void clickInvoiceIcon() {
		newinvoice.click();
	}

	public RegularInvoiceInfoScreen selectInvoiceType(String invoicetype) {
		/*appiumdriver.findElementByXPath("//UIAPopover[1]/UIATableView/UIATableCell[contains(@name, \""
						+ invoicetype + "\")]/UIAStaticText[1]").click();*/
		appiumdriver.findElementByXPath("//UIATableView/UIATableCell[contains(@name, \""
				+ invoicetype + "\")]/UIAStaticText[1]").click();
		return new RegularInvoiceInfoScreen(appiumdriver);
	}

	public String selectInvoiceTypeAndAcceptAlert(String invoicetype) throws InterruptedException {
		selectInvoiceType(invoicetype);
		return Helpers.getAlertTextAndAccept();
	}

	public RegularVehicleScreen selectWorkOrderType(String workordertype) {

		if (Helpers.elementExists(discardbtnxpath)) {
			element(discardbtnxpath).click();
		}
		appiumdriver.findElementByName(workordertype).click();
		return new RegularVehicleScreen(appiumdriver);
	}

	public  void selectWorkOrderJob(String job) {
		appiumdriver.findElementByName(job).click();
	}
	
	public void setFilterBilling(String billing)  {
		appiumdriver.findElementByName("Billing").click();
		appiumdriver.findElementByXPath("//UIATableView[1]/UIATableCell[@name=\""
						+ billing + "\"]").click();
	}
	
	public void cancelInvoice () {
		appiumdriver.findElementByXPath("//UIAAlert[@name=\"Cancel Invoice\"]/UIACollectionView[1]/UIACollectionCell[@name=\"Yes\"]/UIAButton[@name=\"Yes\"]").click();
	}
	
	public void clickSaveFilter() {
		clickSaveButton();
	}
	
	public boolean isMenuItemForSelectedWOExists(String menuitem) {
		return elementExists(By.xpath("//UIAScrollView[2]/UIAButton[@name=\"" + menuitem + "\"]"));
	}
	
	public void clickServiceRequestButton() {
		servicerequestbtn.click();
	}
}
