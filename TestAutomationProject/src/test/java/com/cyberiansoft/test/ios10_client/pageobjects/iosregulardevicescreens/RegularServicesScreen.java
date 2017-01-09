package com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import io.appium.java_client.remote.HideKeyboardStrategy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.cyberiansoft.test.ios_client.pageobjects.iosdevicescreens.PriceMatrixScreen;
import com.cyberiansoft.test.ios10_client.utils.Helpers;

public class RegularServicesScreen extends iOSRegularBaseScreen {

	final static String defaultServiceValue = "Test Tax";
	final static String servicesscreencapt = "Services";
	
	@iOSFindBy(accessibility = "Save")
    private IOSElement savebtn;
	
	@iOSFindBy(accessibility = "Cancel")
    private IOSElement cancelbtn;
	
	@iOSFindBy(accessibility = "Delete")
    private IOSElement deletebtn;
	
	@iOSFindBy(accessibility = "AvailableServicesSwitchButton")
    private IOSElement servicetypesbtn;
	
	@iOSFindBy(accessibility = "Price Matrices")
    private IOSElement pricematrixespopupname;
	
	@iOSFindBy(accessibility = "Compose")
    private IOSElement composebtn;
	
	@iOSFindBy(accessibility = "Vehicle Part")
    private IOSElement vehiclepartsbtn;
	
	@iOSFindBy(accessibility = "Final")
    private IOSElement finalalertbtn;
	
	@iOSFindBy(accessibility = "Draft")
    private IOSElement draftalertbtn;
	
	public RegularServicesScreen(AppiumDriver driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver, 10, TimeUnit.SECONDS), this);
		appiumdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public void clickCancelButton() {
		cancelbtn.click();
	}

	public void assertDefaultServiceIsSelected() {
		Assert.assertTrue(appiumdriver.findElementByXPath("//XCUIElementTypeTable[1]/XCUIElementTypeCell[@name='"
						+ defaultServiceValue + "']/XCUIElementTypeButton[@name='selected']").isDisplayed());
	}

	public void assertServiceIsSelected(String servicename) {
		WebDriverWait wait = new WebDriverWait(appiumdriver,10);
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId(servicename)));
		
		Assert.assertTrue(appiumdriver.findElements(By.xpath(".//XCUIElementTypeTable[1]/XCUIElementTypeCell[@name='" + servicename + "']/XCUIElementTypeButton[@name='selected']")).size() > 0);
	}
	
	public int getNumberOfServiceSelectedItems(String servicename) {
		return appiumdriver.findElements(By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell[@name='" + servicename + "']/XCUIElementTypeButton[@name='selected']")).size();
	}
	
	public int getNumberOfSelectedServices() {
		return appiumdriver.findElements(MobileBy.xpath("//XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeButton[@name='selected']")).size();
	}
	
	
	public void assertServiceIsSelectedAndVisible(String service, String pricevalue) throws InterruptedException {
		Helpers.scroolTo(service);
		
		Assert.assertTrue(appiumdriver.findElement(By.name(service)).isDisplayed());
		Assert.assertTrue(appiumdriver.findElement(By.name("selected")).isDisplayed());
		Assert.assertTrue(appiumdriver.findElement(By.name(pricevalue)).isDisplayed());
	}
	
	public void assertServiceIsSelectedWithServiceValues(String servicename, String pricevalue) {
		Assert.assertTrue(appiumdriver.findElements(By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell[@name='" + servicename + "']/XCUIElementTypeButton[@name='selected']")).size() > 0);
		Assert.assertTrue(appiumdriver.findElements(By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell[@name='" + servicename + "']/XCUIElementTypeStaticText[@name='" + pricevalue + "']")).size() > 0);
		//WebElement par = getServiceTableCell(servicename);		
		//par.findElement(MobileBy.xpath(".//XCUIElementTypeButton[@name='custom detail button']")).click();
		//Assert.assertTrue(par.findElements(MobileBy.AccessibilityId("selected")).size() >0);
		//Assert.assertTrue(par.findElements(MobileBy.AccessibilityId(pricevalue)).size() >0);
	}

	public int getServiceSelectedNumber(String servicename) {
		return appiumdriver.findElements(MobileBy.AccessibilityId(servicename)).size();
	}
	
	public void assertTotalAmauntIsCorrect(String price) {
		WebDriverWait wait = new WebDriverWait(appiumdriver,10);
        wait.until(ExpectedConditions.visibilityOf(appiumdriver.findElementByAccessibilityId("TotalAmount")));
		Assert.assertEquals(appiumdriver.findElementByAccessibilityId("TotalAmount").getAttribute("value"), price);
	}
	
	public void assertSubTotalAmauntIsCorrect(String price) {
		Assert.assertEquals(appiumdriver.findElementByName("SubtotalAmount").getAttribute("value"), price);
	}

	public void assertServiceTypeExists(String servicetype) {
		Assert.assertTrue(appiumdriver.findElements(MobileBy.AccessibilityId(servicetype)).size() > 0);
	}

	public void searchServiceByName(String servicename) {
		appiumdriver.findElementByAccessibilityId("Search").clear();
		appiumdriver.findElementByAccessibilityId("Search").sendKeys(servicename + "\n");
		Helpers.waitABit(500);
	}
	
	public void selectService(String servicename) {
		searchServiceByName(servicename);
		//appiumdriver.findElementByAccessibilityId(servicename).click();
		IOSElement el = (IOSElement) appiumdriver.findElementByClassName("XCUIElementTypeTable").findElement(MobileBy.xpath("//XCUIElementTypeCell[@name='" + servicename + "']"));
		
		TouchAction action = new TouchAction(appiumdriver);
		action.tap(el.getLocation().getX()+2, el.getLocation().getY()+2).perform();
		Assert.assertTrue(appiumdriver.findElementByClassName("XCUIElementTypeTable").
				findElements(MobileBy.xpath("//XCUIElementTypeCell[@name='" + servicename + "']/XCUIElementTypeButton[@name='selected']")).size() > 0);
		//action.press(appiumdriver.findElementByClassName("XCUIElementTypeTable").findElement(MobileBy.className("XCUIElementTypeCell"))).waitAction(1000).release().perform();
		//appiumdriver.findElementByXPath("//XCUIElementTypeTable[1]/XCUIElementTypeCell[@name='" + servicename + "']").click();
		//appiumdriver.findElementByXPath("//UIATableView/UIATableCell[@name=\""
		//	+ service + "\"]").click();
	}
	
	public void selectSubService(String servicename) {
		if (appiumdriver.findElementsByAccessibilityId("Search").size() > 0)
			searchServiceByName(servicename);
		IOSElement el = (IOSElement) appiumdriver.findElementByClassName("XCUIElementTypeTable").findElement(MobileBy.xpath("//XCUIElementTypeCell[@name='" + servicename + "']/XCUIElementTypeButton[@name='unselected']"));
		TouchAction action = new TouchAction(appiumdriver);
		action.tap(el.getLocation().getX()+2, el.getLocation().getY()+2).perform();
		//Assert.assertTrue(appiumdriver.findElementByClassName("XCUIElementTypeTable").
		//		findElements(MobileBy.xpath("//XCUIElementTypeCell[@name='" + servicename + "']/XCUIElementTypeButton[@name='selected']")).size() > 0);
		/*
		Helpers.waitABit(300);
		appiumdriver.findElementByAccessibilityId(servicename).click();
		//appiumdriver.findElementByAccessibilityId("Search").sendKeys(servicename);
		Helpers.waitABit(500);
		
		if (appiumdriver.findElementsByAccessibilityId("Save").size() > 0) {
			//appiumdriver.findElementByAccessibilityId("Save").click();
		}
		else {
			if (appiumdriver.findElementsByXPath("//XCUIElementTypeTable[1]/XCUIElementTypeCell[@name='" + servicename + "']/XCUIElementTypeButton[@name='unselected']").size() > 0)
				new TouchAction(appiumdriver).tap(appiumdriver.findElementByXPath("//XCUIElementTypeTable[1]/XCUIElementTypeCell[@name='" + servicename + "']/XCUIElementTypeButton[@name='unselected']")).perform();
			//WebElement par = getServiceTableCell(servicename);
			//new TouchAction(appiumdriver).tap(par.findElement(MobileBy.AccessibilityId("unselected"))).perform() ;
		}
		//appiumdriver.findElementByAccessibilityId("Cancel").click();
		//par.findElement(MobileBy.xpath(".//XCUIElementTypeButton[@name='unselected']")).click();
		 */
	}
	
	public WebElement getServiceTableCell(String servicename) {
		return appiumdriver.findElement(MobileBy.xpath("//XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeStaticText[@label='" + servicename + "']/.."));
	}

	public RegularSelectedServiceDetailsScreen openCustomServiceDetails(String servicename) {
		if (appiumdriver.findElementsByAccessibilityId("Search").size() > 0)
			searchServiceByName(servicename);
		IOSElement el = (IOSElement) appiumdriver.findElementByClassName("XCUIElementTypeTable").findElement(MobileBy.xpath("//XCUIElementTypeCell[@name='" + servicename + "']/XCUIElementTypeButton[@name='custom detail button']"));
		TouchAction action = new TouchAction(appiumdriver);
		action.tap(el.getLocation().getX()+2, el.getLocation().getY()+2).perform();
		
		/*Helpers.waitABit(2000);
		appiumdriver.findElementByAccessibilityId(servicename).click();
		//TouchAction action = new TouchAction(appiumdriver);
		//action.press(appiumdriver.findElementByXPath("//XCUIElementTypeTable[1]/XCUIElementTypeCell[@name='" + servicename + "']/XCUIElementTypeButton[@name='custom detail button']")).waitAction(1000).release().perform();
		if (appiumdriver.findElementsByXPath("//XCUIElementTypeNavigationBar/XCUIElementTypeOther/XCUIElementTypeStaticText[@value='" + servicename + "']").size() > 0) {
			//appiumdriver.findElementByAccessibilityId("Save").click();
		}
		
		WebElement opensrvbtn = appiumdriver.findElement(MobileBy.
				xpath("//XCUIElementTypeTable/XCUIElementTypeCell[@name='" + servicename + "']/XCUIElementTypeButton[@name='custom detail button']"));	
		new TouchAction(appiumdriver).tap(opensrvbtn).perform() ;
		*/
		return new RegularSelectedServiceDetailsScreen(appiumdriver);
	}
	
	public RegularSelectedServiceDetailsScreen clickServiceCustomDetailButton(String service) {
		//appiumdriver.findElementByXPath("//UIAScrollView[2]/UIATableView[@name=\"ServiceGroupServicesTable\"]/UIATableCell[@name='" + service + "']/UIAButton[@name='custom detail button']").click();
		
		appiumdriver.findElementByXPath("//XCUIElementTypeTable[@name='ServiceGroupServicesTable']/XCUIElementTypeCell[@name='" + service + "']/XCUIElementTypeButton[@name='custom detail button']").click();
		//Helpers.scroolToByXpath("//UIATableView[1]/UIATableCell[@name='" + service + "']/UIAButton[@name='custom detail button']");
		return new RegularSelectedServiceDetailsScreen(appiumdriver);
	}
	
	public RegularSelectedServiceDetailsScreen openSelectedServiceDetails(String service) {
		List<WebElement> selectedservices = appiumdriver.findElementsByXPath("//XCUIElementTypeTable[1]/XCUIElementTypeCell[@name='" + service + "']/XCUIElementTypeButton[@name='custom detail button']");
		if (selectedservices.size() > 1) {			
			Helpers.waitABit(300);
			((WebElement) appiumdriver.findElementsByXPath("//XCUIElementTypeTable[1]/XCUIElementTypeCell[@name='" + service + "']/XCUIElementTypeButton[@name='custom detail button']").get(1)).click();
		} else {
			selectedservices.get(0).click();
		}
		return new RegularSelectedServiceDetailsScreen(appiumdriver);
	}
	
	public void clickToolButton() {
		appiumdriver.findElementByAccessibilityId("services").click();
	}
	
	public void openCustomServiceDetailsByPartOfServiceName(String service) throws InterruptedException {
		appiumdriver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIATableView[2]/UIATableCell[4]").click();
	}


	public RegularPriceMatrixScreen selectServicePriceMatrices(String servicepricematrices) {
		appiumdriver.findElement(MobileBy.xpath("//XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeStaticText[@label='" + servicepricematrices + "']")).click();
		return new RegularPriceMatrixScreen(appiumdriver);
	}
	
	public void setSelectedServiceRequestServicesQuantity(String servicename, String _quantity) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 60);
		Helpers.waitABit(500);
		WebElement par = getServiceTableCell(servicename);
		par.findElement(MobileBy.xpath(".//XCUIElementTypeTextField[1]")).clear();
		((IOSDriver) appiumdriver).getKeyboard().pressKey(_quantity + "\n");
		//par.findElement(MobileBy.xpath(".//XCUIElementTypeTextField[1]")).sendKeys(_quantity + "\n");
		/*wait.until(ExpectedConditions.visibilityOf(appiumdriver.findElementByXPath("//UIATableView[1]/UIATableCell[@name=\""
				+ service + "\"]/UIATextField[1]"))).click();
		
		((IOSElement) appiumdriver.findElementByXPath("//UIATableView[1]/UIATableCell[@name=\""
						+ service + "\"]/UIATextField[1]")).setValue("");
		((IOSElement) appiumdriver.findElementByXPath("//UIATableView[1]/UIATableCell[@name=\""
						+ service + "\"]/UIATextField[1]")).setValue(_quantity);
		Helpers.keyboadrType("\n");*/
	}
	
	public void openServiceDetailsByIndex(String service, int servicedetailindex) {
		
		List<WebElement> selectedservices = appiumdriver.findElementsByXPath("//XCUIElementTypeTable[1]/XCUIElementTypeCell/XCUIElementTypeButton[@name='custom detail button']");
		//Helpers.scroolToElement(selectedservices.get(servicedetailindex));
		//Helpers.waitABit(2000);
		
		((WebElement) appiumdriver.findElementsByXPath("//XCUIElementTypeTable[1]/XCUIElementTypeCell/XCUIElementTypeButton[@name='custom detail button']").get(servicedetailindex)).click();
	}
	
	public void setSelectedServiceRequestServicePrice(String servicename, String price) {
		//Helpers.waitABit(500);
		appiumdriver.findElementByXPath("//XCUIElementTypeTable/XCUIElementTypeCell[@name='" + servicename + "']/XCUIElementTypeStaticText[2]").click();
		appiumdriver.findElementByXPath("//XCUIElementTypeTable/XCUIElementTypeCell[@name='" + servicename + "']/XCUIElementTypeStaticText[2]").clear();
		appiumdriver.findElementByXPath("//XCUIElementTypeTable/XCUIElementTypeCell[@name='" + servicename + "']/XCUIElementTypeStaticText[2]").sendKeys(price + "\n");
		//WebElement par = getServiceTableCell(servicename);
		//par.findElement(MobileBy.xpath(".//XCUIElementTypeTextField[1]")).clear();
		//par.findElement(MobileBy.xpath(".//XCUIElementTypeTextField[1]")).sendKeys(price + "\n");
		
		/*appiumdriver.findElementByXPath("//UIATableView[@name=\"ServiceGroupServicesTable\"]/UIATableCell[@name=\""
						+ service
						+ "\"]/UIATextField[1]").click();
		appiumdriver.findElementByXPath("//UIATableView[@name=\"ServiceGroupServicesTable\"]/UIATableCell[@name=\""
						+ service
						+ "\"]/UIATextField[1]/UIAButton[@name=\"Clear text\"]").click();
		((IOSElement) appiumdriver.findElementByXPath("//UIATableView[@name=\"ServiceGroupServicesTable\"]/UIATableCell[@name=\""
						+ service
						+ "\"]/UIATextField[1]")).setValue(price);
		Helpers.keyboadrType("\n");*/
	}
	
	public boolean priceMatricesPopupIsDisplayed() {
		return pricematrixespopupname.isDisplayed();
	}

	public RegularPriceMatrixScreen selectPriceMatrices(String pricematrice) {
		appiumdriver.findElementByAccessibilityId(pricematrice).click();
		return new RegularPriceMatrixScreen(appiumdriver);
	}
	
	public void removeSelectedServices(String servicename) {
		searchServiceByName(servicename);
		IOSElement el = (IOSElement) appiumdriver.findElementByClassName("XCUIElementTypeTable").findElement(MobileBy.xpath("//XCUIElementTypeCell[@name='" + servicename + "']/XCUIElementTypeButton[@name='selected']"));
		TouchAction action = new TouchAction(appiumdriver);
		action.tap(el.getLocation().getX()+2, el.getLocation().getY()+2).perform();	
		Assert.assertTrue(appiumdriver.findElementByClassName("XCUIElementTypeTable").findElements(MobileBy.xpath("//XCUIElementTypeCell[@name='" + servicename + "']/XCUIElementTypeButton[@name='unselected']")).size() > 0);
	}
	
	public void clickNotesButton() {
		composebtn.click();
	}
	
	public void clickVehiclePartsButton() {
		vehiclepartsbtn.click();
	}

	public static String getServicesScreenCaption() {
		return servicesscreencapt;
	}
	
	public void clickBackServicesButton() {	
		appiumdriver.findElement(MobileBy.name("Back")).click();
	}
	
	public void clickAddServicesButton() {	
		appiumdriver.findElement(MobileBy.AccessibilityId("Add")).click();
		Helpers.waitABit(500);
	}
	
	public void clickSaveAsFinal() {
		clickSaveButton();
		finalalertbtn.click();
	}
	
	public void clickSaveAsDraft() {
		clickSaveButton();
		draftalertbtn.click();
	}
	
	public String getListOfSelectedVehicleParts() {
		WebElement par = getServiceTableCell("Vehicle Part");	
		return par.findElement(MobileBy.xpath(".//XCUIElementTypeStaticText[2]")).getAttribute("value");
	}
	
	public boolean isServiceWithVehiclePartExists(String srvname, String srvvehiclepart) {
		return Helpers.elementExists(By.xpath("//XCUIElementTypeTable[@name='ServiceGroupServicesTable']/XCUIElementTypeCell[@name='" + 
				srvname + "]/XCUIElementTypeStaticText[@name='" + srvvehiclepart + "']"));
	}
	
	public boolean isServiceApproved(String srvname) {
		return appiumdriver.findElements(By.xpath("//XCUIElementTypeTable[@name='ServiceGroupServicesTable']/XCUIElementTypeCell[@name='" + 
				srvname + "']/XCUIElementTypeButton[@name='selected']")).size() > 0;
	}
	
	public boolean isServiceDeclinedSkipped(String srvname) {
		return appiumdriver.findElements(By.xpath("//XCUIElementTypeTable[@name='ServiceGroupServicesTable']/XCUIElementTypeCell[@name='" + 
				srvname + "']/XCUIElementTypeButton[@name='declined']")).size() > 0;
	}

}
