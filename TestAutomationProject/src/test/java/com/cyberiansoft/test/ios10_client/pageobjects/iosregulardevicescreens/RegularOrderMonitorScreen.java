package com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens;


import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.typesscreens.RegularTeamWorkOrdersScreen;
import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class  RegularOrderMonitorScreen extends iOSRegularBaseScreen {
	
	@iOSFindBy(accessibility = "Phase Status")
    private IOSElement phasestatuscell;
	
	@iOSFindBy(accessibility = "Service Status")
    private IOSElement servicestatuscell;
	
	@iOSFindBy(accessibility = "Start Service")
    private IOSElement startservice;
	
	@iOSFindBy(accessibility = "Completed")
    private IOSElement completedcell;
	
	@iOSFindBy(accessibility = "Done icon")
    private IOSElement servicedetailsdonebtn;
	
	@iOSFindBy(accessibility = "custom detail button")
    private IOSElement customservicestatusbtn;
	
	@iOSFindBy(accessibility = "Start Service")
    private IOSElement startservicebtn;
	
	@iOSFindBy(accessibility = "btnStartReset")
    private IOSElement startphasebtn;
	
	@iOSFindBy(accessibility = "Back")
    private IOSElement backbtn;
	
	@iOSFindBy(accessibility = "Services")
    private IOSElement servicesbtn;
	
	@iOSFindBy(accessibility = "Active")
    private IOSElement activecaption;

	@iOSFindBy(accessibility = "MonitorOrderServicesList")
	private IOSElement monitorserviceslist;
	
	public RegularOrderMonitorScreen() {
		super();
		PageFactory.initElements(new AppiumFieldDecorator(appiumdriver), this);
		appiumdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(appiumdriver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("MonitorOrderServicesList")));
	}
	
	public void selectPanel(String panelname) {
		FluentWait<WebDriver> wait = new WebDriverWait(appiumdriver, 5);

		WebElement wotable = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell[@name='" + panelname + "']"))); 
		swipeToElement(appiumdriver.findElement(By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell[@name='" + panelname + "']")));
		appiumdriver.findElementByXPath("//XCUIElementTypeTable/XCUIElementTypeCell[@name='" + panelname + "']").click();
	}
	
	public void selectPanelToChangeStatus(String panelname) {
		selectPanel(panelname);
		appiumdriver.findElementByAccessibilityId("Change Status").click();
	}

	public void verifyPanelsStatuses(String panelname, String status) {
		List<WebElement> elements = appiumdriver.findElementsByXPath("//XCUIElementTypeCell[@name=\"" + panelname + "\"]/XCUIElementTypeStaticText[3]");
		for (WebElement element : elements) {
			Assert.assertTrue(element.getAttribute("value").equals(status));
		}

	}
	
	public String getPanelStatus(String panelname) {
		return monitorserviceslist.findElementByAccessibilityId(panelname).findElementByAccessibilityId("lblServiceStatus").getAttribute("value");
	}

	public String getPanelStatusInPopup(String panelname) {
		String status = null;
		appiumdriver.findElementByName(panelname).click();
		WebElement par = getTableParentCell("Phase Status");
		status = par.findElement(By.xpath("//XCUIElementTypeStaticText[2]")).getAttribute("name");
		appiumdriver.findElementByAccessibilityId("Done icon").click();
		return status;
	}
	
	public String getServiceStatusInPopup(String panelname) {
		String status = null;
		appiumdriver.findElementByName(panelname).click();
		WebElement par = getTableParentCell("Service Status");
		status = par.findElement(By.xpath("//XCUIElementTypeStaticText[2]")).getAttribute("name");
		appiumdriver.findElementByAccessibilityId("Done icon").click();
		return status;
	}
	
	public void setCompletedServiceStatus() {
		clickServiceStatusCell();
		clickCompletedPhaseCell();
		WebDriverWait wait = new WebDriverWait(appiumdriver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Order Monitor")));
	}
	
	public void setCompletedPhaseStatus() {
		clickPhaseStatusCell();
		clickCompletedPhaseCell();
		WebDriverWait wait = new WebDriverWait(appiumdriver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Order Monitor")));
	}
	
	public void clickCompletedPhaseCell() {
		completedcell.click();
	}
	
	public void clickPhaseStatusCell() {
		phasestatuscell.click();
	}
	
	public void clickServiceStatusCell() {
		servicestatuscell.click();
	}
	
	
	public void clickStartPhase() {
		startphasebtn.click();
		WebDriverWait wait = new WebDriverWait(appiumdriver, 40);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(MobileBy.AccessibilityId("Team Order Loading...")));
	}
	
	public RegularOrderMonitorScreen clickServiceDetailsDoneButton() throws InterruptedException {
		servicedetailsdonebtn.click();
		return this;
	}
	
	public boolean isStartServiceButtonPresent() {
		return startservicebtn.isDisplayed();
	}
	
	public boolean isStartPhaseButtonPresent() {
		return startphasebtn.isDisplayed();
	}
	
	public void clickServicesButton() {
		servicesbtn.click();
	}
	
	public void verifyServiceStartDateIsSet(String servicedisplayname, String startdate) {
		Assert.assertTrue(appiumdriver.findElements(By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell[@name='" + servicedisplayname + "']/XCUIElementTypeStaticText[contains(@name, \""
						+ startdate + "\")]")).size() > 0);
	}
	
	public boolean isStartServiceDissapeared() {
		return !elementExists("Start Service");
	}
	
	public void clickStartService() {
		startservicebtn.click();
	}
	
	public String getServiceStartDate() {
		WebElement par = getTableParentCell("Start Date");
		return par.findElement(By.xpath("//XCUIElementTypeStaticText[2]")).getAttribute("name");
	}
	
	public boolean isServiceStartDateExists() { 
		return appiumdriver.findElementsByAccessibilityId("Start Date").size() > 0;
	}
	
	public RegularTeamWorkOrdersScreen clickBackButton() {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 15);
		wait.until(ExpectedConditions.visibilityOf(backbtn));
		backbtn.click();
		return new RegularTeamWorkOrdersScreen();
	}
	
	public WebElement getTableParentCell(String cellname) {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId(cellname)));
		return appiumdriver.findElement(MobileBy.xpath("//XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeStaticText[@label='" + cellname + "']/.."));
	}

	public boolean isRepairPhaseExists() { 
		return appiumdriver.findElementsByAccessibilityId("Repair phase").size() > 0;
	}
	
	public void clicksRepairPhaseLine() {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AccessibilityId("Repair phase"))).click();
	}
	
	public void clickStartPhaseButton() {
		startphasebtn.click();
		WebDriverWait wait = new WebDriverWait(appiumdriver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(monitorserviceslist));
	}
	
	public boolean isStartPhaseButtonExists() { 
		return appiumdriver.findElementsByAccessibilityId("btnStartReset").size() > 0;
	}
	
	public boolean isServicePresent(String servicename) { 
		return appiumdriver.findElementsByXPath("//XCUIElementTypeTable/XCUIElementTypeCell[@name='" + servicename + "']").size() > 0;
	}
	
	public void checkMyWorkCheckbox() {
		if (appiumdriver.findElementsByAccessibilityId("checkbox unchecked").size() > 0)
			appiumdriver.findElementByAccessibilityId("checkbox unchecked").click();
	}
	
	public void uncheckMyWorkCheckbox() {
		if (appiumdriver.findElementsByAccessibilityId("checkbox checked").size() > 0)
			appiumdriver.findElementByAccessibilityId("checkbox checked").click();
	}
}
