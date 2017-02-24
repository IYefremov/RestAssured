package com.cyberiansoft.test.ios10_client.pageobjects.ioshddevicescreens;

import java.util.concurrent.TimeUnit;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.SwipeElementDirection;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.cyberiansoft.test.ios_client.utils.Helpers;

public class ApproveInspectionsScreen extends iOSHDBaseScreen {
	
	//@iOSFindBy(accessibility = "TouchInputViewInPopover")
	@iOSFindBy(accessibility = "TouchInputView")
    private IOSElement approvepopup;
	
	@iOSFindBy(accessibility = "Approve")
    private IOSElement approvebtn;
	
	@iOSFindBy(accessibility = "ApproveAll")
    private IOSElement approveallbtn;
	
	@iOSFindBy(accessibility = "SkipAll")
    private IOSElement skipallbtn;
	
	@iOSFindBy(accessibility = "DeclineAll")
    private IOSElement declineallbtn;
	
	@iOSFindBy(accessibility ="Cancel")
    private IOSElement cancelstatusreasonbtn;
	
	@iOSFindBy(accessibility ="Done")
    private IOSElement donebtn;
	
	public ApproveInspectionsScreen(AppiumDriver driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		appiumdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public void clickApproveButton() {
		approvebtn.click();
	}
	
	public void clickApproveAfterSelection() {
		appiumdriver.findElementByClassName("XCUIElementTypeAlert").findElement(MobileBy.AccessibilityId("Approve")).click();
		Helpers.waitABit(500);
	}
	

	public void clickDoneButton() {
		//approvebtn.click();
		if (appiumdriver.findElements(MobileBy.AccessibilityId("Done")).size() > 1)
			((IOSElement) appiumdriver.findElements(MobileBy.AccessibilityId("Done")).get(1)).click();
		else
			appiumdriver.findElement(MobileBy.AccessibilityId("Done")).click();
		Helpers.waitABit(2000);
	}
	
	public void clickApproveAllServicesButton() {
		approveallbtn.click();
	}
	
	public void clickDeclineAllServicesButton() {
		declineallbtn.click();
	}
	
	public void clickSkipAllServicesButton() {
		skipallbtn.click();
	}

	public void drawApprovalSignature () {
		//clickSignButton();
		//Helpers.drawSignature1();
		Helpers.waitABit(1000);
		int xx = approvepopup.getLocation().getX();
		int yy = approvepopup.getLocation().getY();
		
		TouchAction action = new TouchAction(appiumdriver);
		action.press(appiumdriver.manage().window().getSize().width - yy - approvepopup.getSize().getHeight() + 10,xx + 10).
		waitAction(1000).moveTo(appiumdriver.manage().window().getSize().width - yy - approvepopup.getSize().getHeight() + 100, xx + 100).release().perform();
	}
	
	public void selectInspectionForApprove(String inspnum) {
		/*System.out.println("+++++" + appiumdriver.findElementsByAccessibilityId("ApproveInspectionsView").size());
		IOSElement approvepopup = (IOSElement) appiumdriver.findElementByAccessibilityId("ApproveInspectionsView");
		approvepopup.findElementByAccessibilityId(inspnum).click();*/
		if (appiumdriver.findElements(MobileBy.xpath("//XCUIElementTypeCell[@name='" + inspnum + "']")).size() > 1)
			((IOSElement) appiumdriver.findElements(MobileBy.xpath("//XCUIElementTypeCell[@name='" + inspnum + "']")).get(1)).click();
		else
			appiumdriver.findElement(MobileBy.xpath("//XCUIElementTypeCell[@name='" + inspnum + "']")).click();
		Helpers.waitABit(1000);
	}
	
	public void clickSignButton() {
		appiumdriver.findElement(MobileBy.AccessibilityId("Sign")).click();
	}
	
	
	public void drawSignature AfterSelection() {
		clickSignButton();
		
		Helpers.waitABit(1000);
		IOSElement approveimage = null;
		if (appiumdriver.findElements(MobileBy.AccessibilityId("TouchInputView")).size() > 1)
			approveimage = (IOSElement) appiumdriver.findElements(MobileBy.AccessibilityId("TouchInputView")).get(1);
		else
			approveimage  = (IOSElement) appiumdriver.findElement(MobileBy.AccessibilityId("TouchInputView"));
		int  xx = approveimage.getLocation().getX();
		int yy = approveimage.getLocation().getY();		
		
		TouchAction action = new TouchAction(appiumdriver);
		action.press(appiumdriver.manage().window().getSize().width - yy - approveimage.getSize().getHeight()/2 + 30, xx+40).waitAction(3000).
		moveTo(appiumdriver.manage().window().getSize().width - yy - approveimage.getSize().getHeight()/2 + 200, xx+100).release().perform();
		Helpers.waitABit(1000);
	}
	
	
	public void approveInspectionWithSelectionAndSignature(String inspnum) {
		selectInspectionForApprove(inspnum);
		clickSaveButton();
		drawSignature AfterSelection();
		clickDoneButton();
	}
	
	public void approveInspectionApproveAllAndSignature(String inspnum) {
		selectInspectionForApprove(inspnum);
		approveallbtn.click();
		clickSaveButton();
		clickSignButton();
		drawSignature AfterSelection();
		clickDoneButton();
	}
	
	public boolean isInspectionServiceExistsForApprove(String inspservice) {
		return appiumdriver.findElements(MobileBy.AccessibilityId(inspservice)).size() > 0;
	}
	
	public String getInspectionServicePrice(String inspservice) {
		return appiumdriver.findElement(MobileBy.xpath("//XCUIElementTypeCell[@name='" + inspservice + "']/XCUIElementTypeStaticText[2]")).getAttribute("value");
	}
	
	public void clickCancelStatusReasonButton() {
		cancelstatusreasonbtn.click();
	}
	
	public void clickDoneStatusReasonButton() {
		donebtn.click();
		Helpers.waitABit(2000);
	}
	
	public void selectStatusReason(String statusreson) throws InterruptedException {
		selectUIAPickerValue(statusreson);
		clickDoneStatusReasonButton();
	}
	
	public void selectInspectionServiceToApprove(String inspservice) {
		appiumdriver.findElement(MobileBy.xpath("//XCUIElementTypeTable/XCUIElementTypeCell[@name='" + inspservice + "']/XCUIElementTypeButton[@name='approve little off']")).click();
	}
	
	public void selectInspectionServiceToApproveByIndex(String inspservice, int inspnumber) {
		TouchAction action = new TouchAction(appiumdriver);
		action.press((WebElement) appiumdriver.findElements(By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell[@name='" + inspservice + "']/XCUIElementTypeButton[@name='approve little off']")).get(inspnumber)).waitAction(1000).release().perform();
	}

	public void selectInspectionServiceToDecline(String inspservice) {
		appiumdriver.findElement(MobileBy.xpath("//XCUIElementTypeTable/XCUIElementTypeCell[@name='" + inspservice + "']/XCUIElementTypeButton[@name='decline little off']")).click();
	}
	
	public void selectInspectionServiceToSkip(String inspservice) {
		appiumdriver.findElement(MobileBy.xpath("//XCUIElementTypeTable/XCUIElementTypeCell[@name='" + inspservice + "']/XCUIElementTypeButton[@name='skip little off']")).click();
	}
	
	public void selectInspectionServiceToSkipByIndex(String inspservice, int inspnumber) {
		TouchAction action = new TouchAction(appiumdriver);
		action.press((WebElement) appiumdriver.findElements(By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell[@name='" + inspservice + "']/XCUIElementTypeButton[@name='skip little off']")).get(inspnumber)).waitAction(1000).release().perform();
	}
	
	public int getNumberOfActiveApproveButtons() {
		return appiumdriver.findElements(MobileBy.AccessibilityId("approve little")).size();
	}
	
	public int getNumberOfActiveDeclineButtons() {
		return appiumdriver.findElements(MobileBy.AccessibilityId("decline little")).size();
	}

	public int getNumberOfActiveSkipButtons() {
		return appiumdriver.findElements(MobileBy.AccessibilityId("skip little")).size();
	}

}
