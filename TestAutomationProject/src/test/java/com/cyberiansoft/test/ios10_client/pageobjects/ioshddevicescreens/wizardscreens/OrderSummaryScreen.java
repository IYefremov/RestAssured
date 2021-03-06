package com.cyberiansoft.test.ios10_client.pageobjects.ioshddevicescreens.wizardscreens;

import com.cyberiansoft.test.ios10_client.pageobjects.ioshddevicescreens.typesscreens.MyWorkOrdersScreen;
import com.cyberiansoft.test.ios10_client.types.invoicestypes.IInvoicesTypes;
import com.cyberiansoft.test.ios10_client.utils.Helpers;
import io.appium.java_client.MobileBy;
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

public class OrderSummaryScreen extends BaseWizardScreen {

	final static String defaultServiceValue = "Test Tax";
	final static String ordersummaryscreencapt = "Summary";

	@iOSXCUITFindBy(accessibility  = "Default")
	private IOSElement defaultinvoicetype;
	
	/*@iOSXCUITFindBy(accessibility  = "Save")
    private IOSElement savebtn;
	
	@iOSXCUITFindBy(accessibility  = "Default")
    private IOSElement defaultinvoicetype;
	
	@iOSXCUITFindBy(accessibility = "Total Sale")
    private IOSElement totalsalefinal;*/
	
	private By approveandcreateinvoicechekbox = By.name("black unchecked");

	public OrderSummaryScreen() {
		super();
		PageFactory.initElements(new AppiumFieldDecorator(appiumdriver), this);
	}

	public void waitOrderSummaryScreenLoaded() {
		FluentWait<WebDriver> wait = new WebDriverWait(appiumdriver, 5);
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("OrderSummaryForm")));
	}
	
	public boolean checkDefaultServiceIsSelected() {
		return appiumdriver.findElementByAccessibilityId(defaultServiceValue).isDisplayed();
	}

	public boolean checkServiceIsSelected(String servicename) {
		return appiumdriver.findElementByAccessibilityId(servicename).isDisplayed();
	}

	public void checkApproveAndCreateInvoice() {
		waitOrderSummaryScreenLoaded();
		appiumdriver.findElement(approveandcreateinvoicechekbox).click();
	}
	
	public InvoiceInfoScreen checkCreateInvoice() {
		appiumdriver.findElement(approveandcreateinvoicechekbox).click();
		clickSave();
		return new InvoiceInfoScreen();
	}
	
	public void checkApproveAndSaveWorkOrder() {
		
		appiumdriver.findElement(approveandcreateinvoicechekbox).click();
		clickSave();
	}
	
	public boolean isApproveAndCreateInvoiceExists() {
		return appiumdriver.findElements(approveandcreateinvoicechekbox).size() > 0;
	}

	public InvoiceInfoScreen selectDefaultInvoiceType() {
		defaultinvoicetype.click();
		return new InvoiceInfoScreen();
	}
	
	public InvoiceInfoScreen selectInvoiceType(IInvoicesTypes invoiceType) {
		appiumdriver.findElementByName(invoiceType.getInvoiceTypeName()).click();
		return new InvoiceInfoScreen();
	}

	public void selectWorkOrderDetails(String workorderdetails) {

		appiumdriver.findElementByName(workorderdetails).click();
	}

	public String getOrderSumm() {
		return appiumdriver.findElementByAccessibilityId("TotalAmount").getAttribute("value");
	}

	public String getWorkOrderNumber() {
		waitOrderSummaryScreenLoaded();
		return appiumdriver.findElementByClassName("XCUIElementTypeToolbar")
				.findElement(By.xpath("//XCUIElementTypeOther/XCUIElementTypeStaticText[3]")).getText();
	}

	public String getTotalSaleValue() {
		WebElement par = appiumdriver.findElement(MobileBy.xpath("//XCUIElementTypeStaticText[@value='Total Sale']/.."));
		return par.findElement(MobileBy.xpath("//XCUIElementTypeTextField[1] ")).getAttribute("value");
	}
	
	public void setTotalSale(String totalsale)  {
		waitOrderSummaryScreenLoaded();
		WebElement par = appiumdriver.findElement(MobileBy.xpath("//XCUIElementTypeStaticText[@value='Total Sale']/.."));
		IOSElement totalsalefld = (IOSElement)  par.findElement(MobileBy.xpath("//XCUIElementTypeTextField[1] "));
		totalsalefld.click();
		
		totalsalefld.clear();
		totalsalefld.sendKeys(totalsale + "\n");
		//((IOSDriver) appiumdriver).getKeyboard().pressKey("\n");
	}
	
	public boolean isTotalSaleFieldPresent()  {
		return appiumdriver.findElementsByAccessibilityId("Total Sale").size() > 0;
	}

	public static String getOrderSummaryScreenCaption() {
		return ordersummaryscreencapt;
	}

	public MyWorkOrdersScreen saveWorkOrderWithInvalidVIN() {
		clickSaveWithInvalidVINWarning();
		return new MyWorkOrdersScreen();
	}

	public void clickSaveWithInvalidVINWarning() {
		clickSave();
		Helpers.waitForAlert();
		appiumdriver.switchTo().alert().accept();
	}

	public MyWorkOrdersScreen cancelWorkOrder() {
		clickCancelButton();
		acceptAlert();
		return new MyWorkOrdersScreen();
	}
}
