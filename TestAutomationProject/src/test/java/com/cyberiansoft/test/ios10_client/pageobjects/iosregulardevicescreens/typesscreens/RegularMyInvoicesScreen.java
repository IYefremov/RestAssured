package com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.typesscreens;

import com.cyberiansoft.test.ios10_client.appcontexts.TypeScreenContext;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.RegularNotesScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.wizarscreens.RegularBaseWizardScreen;
import com.cyberiansoft.test.ios10_client.utils.Helpers;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class RegularMyInvoicesScreen extends RegularBaseTypeScreenWithTabs {

	private final TypeScreenContext INVOICECONTEXT = TypeScreenContext.INVOICE;

	@iOSXCUITFindBy(accessibility = "Notes")
    private IOSElement notesmenu;

	/*@iOSXCUITFindBy(accessibility = "My Invoices")
    private IOSElement myinvoicesmenu;
	
	@iOSXCUITFindBy(accessibility = "Team Invoices")
    private IOSElement teaminvoicesmenu;
	
	@iOSXCUITFindBy(accessibility = "Summary")
    private IOSElement summarymenu;
	
	@iOSXCUITFindBy(accessibility = "Done")
    private IOSElement donebtn;

	@iOSXCUITFindBy(accessibility = "Add")
    private IOSElement sendmailaddmailbtn;
	
	@iOSXCUITFindBy(xpath = "//UIAToolbar[1]/UIAButton[@visible=\"true\" and (contains(@name,\"Share\"))] ")
    private IOSElement sharebtn;*/

	@iOSXCUITFindBy(accessibility = "Edit")
	private IOSElement editmenu;

	@iOSXCUITFindBy(accessibility = "Change\nCustomer")
	private IOSElement changecustomermenu;

	@iOSXCUITFindBy(accessibility = "Change\nPO#")
	private IOSElement changeponumbermenu;

	@iOSXCUITFindBy(accessibility = "Approve")
	private IOSElement approvepopupmenu;

	@iOSXCUITFindBy(accessibility = "Pay")
	private IOSElement paymenu;

	@iOSXCUITFindBy(accessibility = "InvoicesTable")
	private IOSElement invoicesTable;

	@iOSXCUITFindBy(accessibility = "Single Email")
	private IOSElement singlemailbtn;

	@iOSXCUITFindBy(accessibility = "Send\nEmail")
	private IOSElement sendemailbtn;

	@iOSXCUITFindBy(accessibility = "Send")
	private IOSElement sendbtn;
	
	public RegularMyInvoicesScreen() {
		super();
		PageFactory.initElements(new AppiumFieldDecorator(appiumdriver), this);
	}

	public void waitInvoicesScreenLoaded() {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("InvoicesTable")));
	}

	public boolean myInvoiceExists(String invoiceNumber) {
		return appiumdriver.findElements(MobileBy.AccessibilityId(invoiceNumber)).size() > 0;
	}
	
	public String getWOsForInvoice(String invoiceNumber) {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId(invoiceNumber)));
		WebElement invoicestable = appiumdriver.findElementByAccessibilityId("InvoicesTable");
		return invoicestable.findElement(MobileBy.AccessibilityId(invoiceNumber)).findElement(MobileBy.AccessibilityId("labelDetails")).getAttribute("value");
	}
	
	public void selectInvoice(String invoiceNumber) {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId(invoiceNumber)));
		invoicesTable.findElement(MobileBy.AccessibilityId(invoiceNumber)).click();
	}
	
	public boolean isInvoiceHasInvoiceNumberIcon(String invoiceNumber) {
		return appiumdriver.findElementByAccessibilityId("InvoicesTable").findElement(MobileBy.AccessibilityId(invoiceNumber)).findElement(MobileBy.AccessibilityId("INVOICE_NO")).isDisplayed();
	}
	
	public boolean isInvoiceHasInvoiceSharedIcon(String invoiceNumber) {
		return appiumdriver.findElementByAccessibilityId("InvoicesTable").findElement(MobileBy.AccessibilityId(invoiceNumber)).findElement(MobileBy.AccessibilityId("INVOICE_SHARED")).isDisplayed();
	}
	
	public RegularMyInvoicesScreen changeCustomerForInvoive(String invoiceNumber, String customer) {
		selectInvoice(invoiceNumber);
		clickChangeCustomerPopup();
		selectCustomer(customer);
		FluentWait<WebDriver>  wait = new WebDriverWait(appiumdriver, 60);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(MobileBy.AccessibilityId("Customer changing...")));
		return this;
		//WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		//wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("InvoicesTable")));
	}
	
	public void selectCustomer(String customer) {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Customers")));
		wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(MobileBy.name(customer)));
		appiumdriver.findElementByName(customer).click();
	}
	
	public String getInvoicePrice(String invoiceNumber) {
		IOSElement invoicesTable = (IOSElement) appiumdriver.findElementByAccessibilityId("InvoicesTable");
		return invoicesTable.findElementByAccessibilityId(invoiceNumber).findElementByAccessibilityId("labelInvoiceAmount").getAttribute("label");
	}
	
	public RegularNotesScreen clickNotesPopup() {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Notes")));
		notesmenu.click();
		return new RegularNotesScreen();
	}
	
	public void clickEditPopup() {
		editmenu.click();
	}
	
	public void clickChangeCustomerPopup() {
		changecustomermenu.click();
	}
	
	public void clickChangePOPopup() {
		changeponumbermenu.click();
	}

	protected void clickApproveInvoiceButton() {
		approvepopupmenu.click();
	}
	
	public void changePO(String newpo) {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.className("XCUIElementTypeCollectionView")));
		appiumdriver.findElementByClassName("XCUIElementTypeCollectionView").findElement(By.className("XCUIElementTypeTextField")).clear();
		appiumdriver.findElementByClassName("XCUIElementTypeCollectionView").findElement(By.className("XCUIElementTypeTextField")).sendKeys(newpo);
		appiumdriver.switchTo().alert().accept();
	}
	
	public boolean myInvoicesIsDisplayed() {
		return appiumdriver.findElementByAccessibilityId("InvoicesTable").isDisplayed();
	}
	
	public boolean teamInvoicesIsDisplayed() {
		return appiumdriver.findElementByAccessibilityId("Team Invoices").isDisplayed();
	}
	
	public void clickActionButton() {
		appiumdriver.findElement(MobileBy.AccessibilityId("Share")).click();
	}
	
	public void selectInvoiceForApprove(String inspnumber) {
		selectInvoice(inspnumber);
		clickApproveInvoiceButton();
	}
	
	public void selectEmployee(String employee) {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10); 
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Employees")));
		appiumdriver.findElementByName(employee).click();
	}
	
	public void selectEmployeeAndTypePassword(String employee, String password) {
		selectEmployee( employee);
		((IOSElement) appiumdriver.findElementByXPath("//UIASecureTextField[@value=\"Enter password here\"]")).setValue(password);
		Helpers.acceptAlert();
	}
	
	public boolean isInvoiceApproveButtonExists(String invoiceNumber) {
		
		appiumdriver.findElementByAccessibilityId(invoiceNumber).click();
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.className("XCUIElementTypeScrollView")));
		boolean approved = appiumdriver.findElementsByAccessibilityId("Approve").size() > 0;
		clickCancel();
		new RegularMyInvoicesScreen();
		return approved;		
	}
	
	public void clickDoneButton() {
		appiumdriver.findElementByAccessibilityId("Done").click();
	}
	
	public void sendEmail(String email) {
		clickSendEmail();
		clickSendEmailAddMailButton();
		enterEmailAddress(email);
		clickSendButton();
	}
	
	public void sendSingleEmail(String email) {
		clickSendEmail();
		clickSendEmailAddMailButton();
		enterEmailAddress(email);
		clickSendButton();
		singlemailbtn.click();
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("InvoicesTable")));
	}
	
	public void clickSendEmail() {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Send\nEmail")));
		sendemailbtn.click();
	}
	
	public void clickSendButton() {
		sendbtn.click();
	}
	
	public void clickSendEmailAddMailButton() {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Add")));
		appiumdriver.findElementByAccessibilityId("Add").click();
	}
	
	public void enterEmailAddress(String email) {
		IOSElement invoicetable = (IOSElement) appiumdriver.findElementByClassName("XCUIElementTypeAlert");
		IOSElement mailfld = ((IOSElement) invoicetable.findElementByClassName("XCUIElementTypeTextField"));
		mailfld.sendKeys(email);
		Helpers.acceptAlert();
	}
	
	public String getPriceForInvoice(String invoiceNumber) {
		return appiumdriver.findElementByAccessibilityId("InvoicesTable").
				findElement(MobileBy.AccessibilityId(invoiceNumber)).findElement(MobileBy.AccessibilityId("labelInvoiceAmount")).getAttribute("value");
	}
	
	public void selectInvoices(int numberInvoicesToSelect) {
		FluentWait<WebDriver> wait = new WebDriverWait(appiumdriver, 5);

		IOSElement invoicetable = (IOSElement) wait.until(ExpectedConditions.presenceOfElementLocated(By.name("InvoicesTable"))); 
		List<MobileElement> invoices = invoicetable.findElementsByClassName("XCUIElementTypeCell");
		for (int i = 0; i < numberInvoicesToSelect; i++)
			invoices.get(i).click();
	}

	public void clickBackButton() {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Back")));
		wait = new WebDriverWait(appiumdriver, 15);
		wait.until(ExpectedConditions.visibilityOf(appiumdriver.findElementByAccessibilityId("Back"))).click();
	}

	public void clickInvoicePayMenuItem() {
		paymenu.click();
	}

}
