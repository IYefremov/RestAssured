package com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.wizarscreens;

import com.cyberiansoft.test.baseutils.BaseUtils;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.RegularSelectedServiceDetailsScreen;
import com.cyberiansoft.test.ios10_client.utils.Helpers;
import com.cyberiansoft.test.vnext.utils.WaitUtils;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RegularServicesScreen extends RegularBaseServicesScreen {

	final static String servicesscreencapt = "Services";
	
	/*@iOSXCUITFindBy(accessibility = "Save")
    private IOSElement savebtn;
	
	@iOSXCUITFindBy(accessibility = "Cancel")
    private IOSElement cancelbtn;
	
	@iOSXCUITFindBy(accessibility = "Delete")
    private IOSElement deletebtn;
	
	@iOSXCUITFindBy(accessibility = "AvailableServicesSwitchButton")
    private IOSElement servicetypesbtn;
	
	@iOSXCUITFindBy(accessibility = "Price Matrices")
    private IOSElement pricematrixespopupname;
	
	@iOSXCUITFindBy(accessibility = "Compose")
    private IOSElement composebtn;
	
	@iOSXCUITFindBy(accessibility = "Vehicle Part")
    private IOSElement vehiclepartsbtn;
	
	@iOSXCUITFindBy(accessibility = "Final")
    private IOSElement finalalertbtn;*/

	@iOSXCUITFindBy(accessibility = "AvailableServiceList")
	private IOSElement availableservicestbl;
	
	public RegularServicesScreen() {
		super();
		PageFactory.initElements(new AppiumFieldDecorator(appiumdriver), this);
	}

	public void waitServicesScreenLoaded() {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.iOSNsPredicateString("label = 'Services' and type = 'XCUIElementTypeButton'")));
	}

	public void clickCancelButton() {
		appiumdriver.findElement(MobileBy.AccessibilityId("Cancel")).click();
	}

	
	public String getServicePriceValue(String servicename) {
		return appiumdriver.findElement(By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell[@name='" + servicename + "']/XCUIElementTypeStaticText[3]")).getAttribute("value").replaceAll("[^a-zA-Z0-9$.%]", "");
	}
	
	public String getTotalAmaunt() {
		String totalAmaunt = "";
		if (appiumdriver.findElementsByName("TotalAmount").size() > 0)
			totalAmaunt = appiumdriver.findElementByAccessibilityId("TotalAmount").getAttribute("value");
		else
			totalAmaunt = appiumdriver.findElementByAccessibilityId("AmountTotal").getAttribute("value");
		return totalAmaunt;
	}
	
	public String getSubTotalAmaunt() {
		String subtotalAmaunt = "";
		if (appiumdriver.findElementsByName("SubtotalAmount").size() > 0)
			subtotalAmaunt = appiumdriver.findElementByName("SubtotalAmount").getAttribute("value");
		else
			subtotalAmaunt = appiumdriver.findElementByName("AmountSubtotal").getAttribute("value");
		return subtotalAmaunt;
	}

	public boolean isServiceTypeExists(String servicetype) {
		return appiumdriver.findElements(MobileBy.AccessibilityId(servicetype)).size() > 0;
	}

	public void searchServiceByName(String servicename) {
		appiumdriver.findElementByClassName("XCUIElementTypeSearchField").clear();
		appiumdriver.findElementByClassName("XCUIElementTypeSearchField").click();
		appiumdriver.findElementByClassName("XCUIElementTypeSearchField").sendKeys(servicename + "\n");
	}

	public void selectServicePanel(String servicePanel) {
		MobileElement panelTable = (MobileElement) appiumdriver.findElementByAccessibilityId("AvailableGroupItemList");
		if (!panelTable.findElement(MobileBy.AccessibilityId(servicePanel)).isDisplayed())
			swipeToElement(panelTable.
					findElement(By.xpath("//XCUIElementTypeCell[@name='" + servicePanel + "']/XCUIElementTypeStaticText[@name='" + servicePanel + "']/..")));
		panelTable.findElement(MobileBy.AccessibilityId(servicePanel)).click();
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.numberOfElementsToBeLessThan(MobileBy.AccessibilityId("ServiceGroupServicePartsTable"), 1));
	}
	
	public void selectService(String servicename) {
		Instant start = Instant.now();
		//if (elementExists("Clear text"))
		//	appiumdriver.findElementByClassName("XCUIElementTypeSearchField").clear();


		IOSElement servicecell = (IOSElement) availableservicestbl.
				findElement(MobileBy.AccessibilityId(servicename));
		if (!servicecell.isDisplayed()) {

			//if (appiumdriver.findElementsByClassName("XCUIElementTypeSearchField").size() > 0)
				searchServiceByName(servicename);
		}
		/*if (appiumdriver.findElementByAccessibilityId("AvailableServiceList").
				findElement(MobileBy.AccessibilityId(servicename)).findElements(MobileBy.AccessibilityId("unselected")).size() > 0) {
			appiumdriver.findElementByAccessibilityId("AvailableServiceList").
					findElement(MobileBy.AccessibilityId(servicename)).findElement(MobileBy.AccessibilityId("unselected")).click();
		} else {
*/
		availableservicestbl.
					findElement(MobileBy.AccessibilityId(servicename)).click();
		System.out.println("===" + Duration.between(start, Instant.now()).toMillis());

	}
	
	public void selectServiceSubSrvice(String servicesubsrvicename) {
		appiumdriver.findElementByClassName("XCUIElementTypeTable").findElement(MobileBy.AccessibilityId(servicesubsrvicename)).click();
	}

	public void selectSubService(String servicename) {
		if (elementExists(MobileBy.AccessibilityId("Clear text")))
			appiumdriver.findElementByClassName("XCUIElementTypeSearchField").clear();

		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId(servicename)));
        IOSElement servicecell = (IOSElement) appiumdriver.findElementByAccessibilityId("AvailableServiceList").
                findElement(MobileBy.AccessibilityId(servicename));
        if (!servicecell.isDisplayed()) {
            if (appiumdriver.findElementsByClassName("XCUIElementTypeSearchField").size() > 0)
                searchServiceByName(servicename);
        }
		appiumdriver.findElementByAccessibilityId("AvailableServiceList").
				findElement(MobileBy.AccessibilityId(servicename)).click();
	}
	
	public WebElement getServiceTableCell(String servicename) {
		return appiumdriver.findElement(MobileBy.xpath("//XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeStaticText[@label='" + servicename + "']/.."));
	}

	public RegularSelectedServiceDetailsScreen  openBundleServiceDetails(String servicename) {

		IOSElement el = (IOSElement) appiumdriver.findElement(MobileBy.iOSNsPredicateString("name = 'BundleItemsView' and type = 'XCUIElementTypeTable'"))
				.findElement(MobileBy.AccessibilityId(servicename)).findElement(MobileBy.AccessibilityId("custom detail button"));
		TouchAction action = new TouchAction(appiumdriver);
		action.tap(PointOption.point(el.getLocation().getX()+2, el.getLocation().getY()+2)).perform();

		return new RegularSelectedServiceDetailsScreen();
	}

	public RegularSelectedServiceDetailsScreen openCustomServiceDetails(String servicename) {

		if (elementExists("Clear text"))
			appiumdriver.findElementByClassName("XCUIElementTypeSearchField").clear();
		if (elementExists(MobileBy.className("XCUIElementTypeSearchField")))
		    if (!availableservicestbl.findElementByAccessibilityId(servicename ).isDisplayed())
				searchServiceByName(servicename);
		IOSElement el = (IOSElement) availableservicestbl.findElement(MobileBy.xpath("//XCUIElementTypeCell[@name='" + servicename + "']/XCUIElementTypeButton[@name='custom detail button']"));
		TouchAction action = new TouchAction(appiumdriver);
		action.tap(PointOption.point(el.getLocation().getX()+2, el.getLocation().getY()+2)).perform();

		return new RegularSelectedServiceDetailsScreen();
	}

	public RegularPriceMatrixScreen selectServicePriceMatrices(String servicepricematrices) {
		appiumdriver.findElement(MobileBy.xpath("//XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeStaticText[@label='" + servicepricematrices + "']")).click();
		return new RegularPriceMatrixScreen();
	}
	
	public boolean priceMatricesPopupIsDisplayed() {
		return appiumdriver.findElement(MobileBy.AccessibilityId("Price Matrices")).isDisplayed();
	}

	public RegularPriceMatrixScreen selectPriceMatrices(String pricematrice) {
		appiumdriver.findElementByAccessibilityId(pricematrice).click();
		return new RegularPriceMatrixScreen();
	}
	

	
	public RegularServicesScreen clickBackServicesButton() {
		BaseUtils.waitABit(500);
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Service Types")));
		appiumdriver.findElement(MobileBy.name("Service Types")).click();
		BaseUtils.waitABit(500);
		return this;
	}

	public String getListOfSelectedVehicleParts() {
		WebElement par = getServiceTableCell("Vehicle Part");	
		return par.findElement(MobileBy.xpath("//XCUIElementTypeStaticText[2]")).getAttribute("value");
	}
	
	public boolean isServiceWithVehiclePartExists(String srvname, String srvvehiclepart) {
		return Helpers.elementExists(By.xpath("//XCUIElementTypeTable[@name='ServiceGroupServicesTable']/XCUIElementTypeCell[@name='" + 
				srvname + "]/XCUIElementTypeStaticText[@name='" + srvvehiclepart + "']"));
	}
	
	public void clickTechnicianToolbarIcon() {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("technician")));
		appiumdriver.findElementByClassName("XCUIElementTypeToolbar").findElement(MobileBy.AccessibilityId("technician")).click();
	}
	
	public void changeTechnician(String servicetype, String techname) {
		appiumdriver.findElementByAccessibilityId(servicetype).click();
		appiumdriver.findElementByAccessibilityId("DefaultEmployeeSelectorView").
			findElement(MobileBy.xpath("//XCUIElementTypeCell[@name='"
				+ techname + "']/XCUIElementTypeButton[@name='unselected']")).click();
		//}
		appiumdriver.findElementByXPath("//XCUIElementTypeNavigationBar[@name='Technicians']/XCUIElementTypeButton[@name='Save']").click();
		appiumdriver.findElementByXPath("//XCUIElementTypeNavigationBar[@name='Service Types']/XCUIElementTypeButton[@name='Save']").click();
	}

}
