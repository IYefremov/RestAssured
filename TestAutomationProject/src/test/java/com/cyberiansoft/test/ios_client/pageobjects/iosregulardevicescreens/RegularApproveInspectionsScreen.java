package com.cyberiansoft.test.ios_client.pageobjects.iosregulardevicescreens;

import java.util.concurrent.TimeUnit;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

import org.openqa.selenium.support.PageFactory;

import com.cyberiansoft.test.ios_client.utils.Helpers;

public class RegularApproveInspectionsScreen extends iOSRegularBaseScreen {
	
	@iOSFindBy(uiAutomator =".navigationBar().buttons()[\"Cancel\"]")
    private IOSElement cancelbtn;
	
	@iOSFindBy(uiAutomator =".navigationBar().buttons()[\"Approve\"]")
    private IOSElement approvebtn;
	
	@iOSFindBy(uiAutomator =".toolbars()[0].buttons()[0]")
    private IOSElement approveallbtn;
	
	@iOSFindBy(uiAutomator =".toolbars()[0].buttons()[1]")
    private IOSElement declineallbtn;
	
	@iOSFindBy(uiAutomator =".toolbars()[0].buttons()[2]")
    private IOSElement skipallbtn;
	
	@iOSFindBy(uiAutomator =".toolbars()[1].buttons()['Cancel']")
    private IOSElement cancelstatusreasonbtn;
	
	@iOSFindBy(uiAutomator =".toolbars()[1].buttons()['Done']")
    private IOSElement donestatusreasonbtn;
	
	public RegularApproveInspectionsScreen(AppiumDriver driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver, 10, TimeUnit.SECONDS), this);
		appiumdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public void selectInspectionToApprove() {
		appiumdriver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]").click();
	}

	public void clickApproveButton() {
		approvebtn.click();
	}
	
	public void clickDeclineAllServicesButton() {
		declineallbtn.click();
	}
	
	public void clickSkipAllServicesButton() {
		skipallbtn.click();
	}

	public void clickCancelButton() {
		cancelbtn.click();
	}
	
	public void drawApprovalSignature () throws InterruptedException {
		
		Thread.sleep(1000);
		MobileElement element = (MobileElement) appiumdriver.findElementByXPath("//UIAApplication[1]/UIAWindow[2]/UIANavigationBar[1]");
		int xx = element.getLocation().getX();

		int yy = element.getLocation().getY();
		TouchAction action = new TouchAction(appiumdriver);
		action.press(xx + 100,yy + 100).moveTo(xx + 200, yy + 200).release().perform();

	}

	public void clickInspectionInfoAlertCloseButton() {
		Helpers.waitForAlert();
		appiumdriver.findElementByXPath("//UIAAlert[1]/UIATableView[1]/UIATableCell[@name=\"Close\"]").click();
	}
	
	public void approveInspectionApproveAllAndSignature(String inspnum) throws InterruptedException {
		approveallbtn.click();
		clickSaveButton();
		drawApprovalSignature ();
		clickApproveButton();
	}
	
	public boolean isInspectionServiceExistsForApprove(String inspservice) {
		return appiumdriver.findElements(MobileBy.IosUIAutomation(".tableViews()[0].cells()['" + inspservice + "']")).size() > 0;
	}
	
	public String getInspectionServicePrice(String inspservice) {
		return appiumdriver.findElement(MobileBy.IosUIAutomation(".tableViews()[0].cells()['" + inspservice + "'].staticTexts()[1]")).getAttribute("name");
	}
	
	public void clickCancelStatusReasonButton() {
		cancelstatusreasonbtn.click();
	}
	
	public void clickDoneStatusReasonButton() {
		donestatusreasonbtn.click();
	}
	
	public void selectStatusReason(String statusreson) throws InterruptedException {
		selectUIAPickerValue(statusreson);
		clickDoneStatusReasonButton();
	}
	
	public void selectInspectionServiceToApprove(String inspservice) {
		appiumdriver.findElement(MobileBy.IosUIAutomation(".tableViews()[0].cells()['" + inspservice + "'].buttons()['approve little off']")).click();
	}

	public void selectInspectionServiceToDecline(String inspservice) {
		appiumdriver.findElement(MobileBy.IosUIAutomation(".tableViews()[0].cells()['" + inspservice + "'].buttons()['decline little off']")).click();
	}
	
	public void selectInspectionServiceToSkip(String inspservice) {
		appiumdriver.findElement(MobileBy.IosUIAutomation(".tableViews()[0].cells()['" + inspservice + "'].buttons()['skip little off']")).click();
	}

}
