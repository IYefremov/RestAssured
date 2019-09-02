package com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens;

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

public class RegularSelectedServiceBundleScreen extends iOSRegularBaseScreen {
	
	@iOSXCUITFindBy(accessibility = "Close")
	private IOSElement tollbarcloseservicesbtn;
	
	public RegularSelectedServiceBundleScreen() {
		super();
		PageFactory.initElements(new AppiumFieldDecorator(appiumdriver), this);
	}

	public boolean checkBundleIsSelected(String bundle) {

		FluentWait<WebDriver> wait = new WebDriverWait(appiumdriver, 15);
		MobileElement bundleview = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(MobileBy.iOSNsPredicateString("name = 'BundleItemsView' and type = 'XCUIElementTypeTable'")));
		return bundleview.findElementByAccessibilityId(bundle).findElementsByAccessibilityId("selected").size() > 0;
	}

	public boolean checkBundleIsNotSelected(String bundle) {
		FluentWait<WebDriver> wait = new WebDriverWait(appiumdriver, 15);
		MobileElement bundleview = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(MobileBy.iOSNsPredicateString("name = 'BundleItemsView' and type = 'XCUIElementTypeTable'")));
		return bundleview.findElementByAccessibilityId(bundle).findElementsByAccessibilityId("unselected").size() > 0;
	}

	public void selectBundle(String bundle) {
		FluentWait<WebDriver> wait = new WebDriverWait(appiumdriver, 15);
		MobileElement bundleview = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(MobileBy.iOSNsPredicateString("name = 'BundleItemsView' and type = 'XCUIElementTypeTable'")));
		bundleview.findElementByAccessibilityId(bundle).findElementByAccessibilityId("unselected").click();
	}

	public RegularSelectedServiceDetailsScreen openBundleInfo(String bundle) {
		FluentWait<WebDriver> wait = new WebDriverWait(appiumdriver, 15);
		MobileElement bundleview = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(MobileBy.iOSNsPredicateString("name = 'BundleItemsView' and type = 'XCUIElementTypeTable'")));
		if (!bundleview.findElementByAccessibilityId(bundle).isDisplayed())
			swipeToElement(bundleview.
					findElement(MobileBy.AccessibilityId(bundle)));
		bundleview.findElement(MobileBy.AccessibilityId(bundle))
				.findElement(MobileBy.AccessibilityId("custom detail button")).click();
		return new RegularSelectedServiceDetailsScreen();
	}
	
	public void clickServicesIcon() {
		appiumdriver.findElementByAccessibilityId("services").click();
	}
	
	public void clickCloseServicesPopup() {
		appiumdriver.findElementByAccessibilityId("Close").click();
	}
	
	public boolean isBundleServiceExists(String bundle) {
		return appiumdriver.findElements(MobileBy.AccessibilityId(bundle)).size() > 0;
	}

	public String getServiceDetailsPriceValue() {

		IOSElement toolbar = (IOSElement) appiumdriver.findElementByClassName("XCUIElementTypeToolbar");
		return toolbar.findElementByClassName("XCUIElementTypeStaticText").getAttribute("value");
	}

	public void changeAmountOfBundleService(String newamount) {
		List<WebElement> toolbarbtns = appiumdriver.findElementByClassName("XCUIElementTypeToolbar").findElements(MobileBy.className("XCUIElementTypeButton"));
		for (WebElement btn : toolbarbtns)
			if (btn.getAttribute("name").contains("$")) {
				btn.click();
				break;
			}
		IOSElement amountfld = (IOSElement) appiumdriver.findElementByClassName("XCUIElementTypeAlert").findElement(MobileBy.className("XCUIElementTypeTextField"));
		amountfld.clear();
		amountfld.sendKeys(newamount);
		appiumdriver.findElementByAccessibilityId("Override").click();
	}

	public void clickSaveButton() {
		appiumdriver.findElementByAccessibilityId("Save").click();
	}

	public void clickTechniciansIcon() {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("technician")));
		wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("technician"))).click();
	}

}
