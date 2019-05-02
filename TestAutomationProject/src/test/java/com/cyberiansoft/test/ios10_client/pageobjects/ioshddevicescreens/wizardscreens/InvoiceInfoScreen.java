package com.cyberiansoft.test.ios10_client.pageobjects.ioshddevicescreens.wizardscreens;

import com.cyberiansoft.test.ios10_client.appcontexts.TypeScreenContext;
import com.cyberiansoft.test.ios10_client.pageobjects.ioshddevicescreens.typesscreens.BaseTypeScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.screensinterfaces.ITypeScreen;
import com.cyberiansoft.test.ios10_client.utils.Helpers;
import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InvoiceInfoScreen extends BaseWizardScreen implements ITypeScreen {

	private final TypeScreenContext INVOICEINFOCONTEXT = TypeScreenContext.INVOICEINFO;
	private static TypeScreenContext INVOICEINFOExCONTEXT = null;
	
	/*@iOSXCUITFindBy(accessibility = "Draft")
    private IOSElement draftalertbtn;
	
	@iOSXCUITFindBy(accessibility = "Final")
    private IOSElement finalalertbtn;
	
	@iOSXCUITFindBy(accessibility = "InvoiceOrdersTable")
    private IOSElement invoicewostable;
	
	@iOSXCUITFindBy(accessibility  = "action pay")
    private IOSElement invoicepaybtn;
	
	@iOSXCUITFindBy(accessibility  = "cash normal")
    private IOSElement cashnormalbtn;
	
	@iOSXCUITFindBy(accessibility  = "Save")
    private IOSElement savebtn;
	
	@iOSXCUITFindBy(accessibility  = "Cancel")
    private IOSElement cancelbtn;*/

	@iOSXCUITFindBy(accessibility = "txtPO")
	private IOSElement setpofld;

	public InvoiceInfoScreen() {
		super();
		PageFactory.initElements(new AppiumFieldDecorator(appiumdriver), this);
		WebDriverWait wait = new WebDriverWait(appiumdriver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Info")));
	}
	
	public void clickSaveEmptyPO() {
		clickSave();
		Alert alert = appiumdriver.switchTo().alert();
		alert.accept();
	}

	public <T extends BaseTypeScreen> T clickSaveAsDraft()  {
		clickSave();
		appiumdriver.findElementByAccessibilityId("Draft").click();
		clickSave();
		return getTypeScreenFromContext();
	}

	public <T extends BaseTypeScreen> T clickSaveAsFinal() {
		if (INVOICEINFOExCONTEXT != null) {
			BaseWizardScreen.typeContext = INVOICEINFOExCONTEXT;
			INVOICEINFOExCONTEXT = null;
		}
		clickSave();
		appiumdriver.findElementByAccessibilityId("Final").click();
		clickSave();
		return getTypeScreenFromContext();
	}
	
	public String getInvoicePOValue() {
		return setpofld.getAttribute("value");
	}

	public void setPO(String _po) {
		setPOWithoutHidingkeyboard(_po);
		((IOSElement) appiumdriver.findElementByAccessibilityId("txtPO")).setValue("\n");
	}
	
	public void setPOWithoutHidingkeyboard(String _po) {
		((IOSElement) appiumdriver.findElementByAccessibilityId("txtPO")).setValue(_po);
	}

	public boolean isWOSelected(String wonumber) {
		return appiumdriver.findElementsByAccessibilityId(wonumber).size() > 0;
	}
	
	public void clickFirstWO() {
		((IOSElement) appiumdriver.findElementByAccessibilityId("InvoiceOrdersTable")).findElementByXPath("//XCUIElementTypeCell[1]").click();
		INVOICEINFOExCONTEXT = BaseWizardScreen.typeContext;
		BaseWizardScreen.typeContext = INVOICEINFOCONTEXT;
	}
	
	public String getOrderSumm() {
		FluentWait<WebDriver> wait = new WebDriverWait(appiumdriver, 25);
		wait.until(ExpectedConditions.elementToBeClickable(By.name("TotalAmount")));
		return appiumdriver.findElementByAccessibilityId("TotalAmount").getAttribute("value");
	}
	
	public InvoiceInfoScreen addWorkOrder(String wonumber) {
		((IOSElement) appiumdriver.findElementByAccessibilityId("InvoiceOrdersTable")).findElementByAccessibilityId("Insert").click();
		WebDriverWait wait = new WebDriverWait(appiumdriver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("TeamInvoiceOrdersView")));
		IOSElement invoicesOrdersTable = (IOSElement) appiumdriver.findElement(MobileBy.iOSNsPredicateString("name = 'TeamInvoiceOrdersView' and type = 'XCUIElementTypeTable'"));
		invoicesOrdersTable.findElementByAccessibilityId(wonumber).findElementByAccessibilityId("unselected").click();

		appiumdriver.findElementByAccessibilityId("Done").click();

		wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(MobileBy.AccessibilityId("Done")));
		return this;
	}
	
	public String getInvoiceNumber() {
		IOSElement toolbar = (IOSElement) appiumdriver.findElementByClassName("XCUIElementTypeToolbar");
		return toolbar.findElementByIosNsPredicate("name contains 'I-00'").getAttribute("value");
		//return appiumdriver.findElementByXPath("//XCUIElementTypeToolbar[1]/XCUIElementTypeOther/XCUIElementTypeStaticText[contains(@name, \"I-00\")]").getAttribute("value");
	}
	
	public String getInvoiceCustomer() {
		return appiumdriver.findElementByAccessibilityId("viewPrompt").getAttribute("value");
	}

	//public void clickSaveButton() {
	//	savebtn.click();
	//}

	public void clickCancelButton() {
        WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Cancel")));
		appiumdriver.findElementByAccessibilityId("Cancel").click();
	}
	
	public <T extends BaseTypeScreen> T cancelInvoice() {
		if (INVOICEINFOExCONTEXT != null) {
			BaseWizardScreen.typeContext = INVOICEINFOExCONTEXT;
			INVOICEINFOExCONTEXT = null;
		}
		clickCancelButton();
		acceptAlert();
		return getTypeScreenFromContext();
	}

	public void clickInvoicePayButton() {
		appiumdriver.findElementByAccessibilityId("action pay").click();
	}
	
	public void changePaynentMethodToCashNormal() {
		appiumdriver.findElementByAccessibilityId("cash normal").click();
	}
	
	public void setCashCheckAmountValue(String amountvalue) {
		appiumdriver.findElementByAccessibilityId("Payment_Tab_Cash").click();
		//IOSElement par = (IOSElement) appiumdriver.findElementsByAccessibilityId("InvoicePaymentView").get(1);
		appiumdriver.findElementByAccessibilityId("Payment_Cash_Amount").clear();
		appiumdriver.findElementByAccessibilityId("Payment_Cash_Amount").sendKeys(amountvalue);
	}
	
	public void clickInvoicePayDialogButon() {
		appiumdriver.findElementByAccessibilityId("Pay").click();
	}

	public void clickSaveIvoiceWithoutPONumber() {
		appiumdriver.findElementByAccessibilityId("Save").click();
		Helpers.waitForAlert();
		appiumdriver.switchTo().alert().accept();
		new InvoiceInfoScreen();
	}
}
