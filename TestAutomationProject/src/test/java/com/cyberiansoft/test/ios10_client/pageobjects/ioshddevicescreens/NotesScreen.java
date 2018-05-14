package com.cyberiansoft.test.ios10_client.pageobjects.ioshddevicescreens;

import com.cyberiansoft.test.ios_client.utils.Helpers;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class NotesScreen extends iOSHDBaseScreen {

	final public static String quicknotesvalue = "This is test Quick Notes";

	@iOSFindBy(accessibility = "Done ")
    private IOSElement donebtn;
	
	@iOSFindBy(accessibility  = "ImageSelectorCameraButton")
    private IOSElement camerabtn;
	
	@iOSFindBy(accessibility  = "Cancel")
    private IOSElement cancelbtn;
	
	public NotesScreen(AppiumDriver driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		appiumdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public void setNotes(String _notes) throws InterruptedException {
		WebElement el =appiumdriver.findElementByAccessibilityId("txtNotes");
		TouchAction action = new TouchAction(appiumdriver);
		action.press(el).waitAction(Duration.ofSeconds(1)).release().perform();
		el.clear();
		((IOSDriver) appiumdriver).getKeyboard().pressKey(_notes);
	}

	public void addQuickNotes() throws InterruptedException {
		TouchAction action = new TouchAction(appiumdriver);
		action.press(appiumdriver.findElementByXPath("//XCUIElementTypeStaticText[@value='"
				+ quicknotesvalue + "'] ")).waitAction(Duration.ofSeconds(1)).release().perform();
	}
	
	public String getNotesValue() {
		return appiumdriver.findElementByAccessibilityId("txtNotes")
						.getAttribute("value");
	}
	
	public boolean isNotesPresent(String _notes) {
		return appiumdriver.findElementsByXPath("//XCUIElementTypeStaticText[@value='"
				+ quicknotesvalue + "'] ").size() > 0;
	}

	public void clickDoneButton() {
		donebtn.click();
	}
	
	public void clickCameraButton() {
		camerabtn.click();
	}
	
	public void addNotesCapture() throws InterruptedException {
		clickCameraButton();
		Helpers.makeCapture();
		cancelbtn.click();
	}
	
	public int getNumberOfAddedPhotos() {
		return appiumdriver.findElements(MobileBy.name("ImageSelectorImage")).size();
	}

	public void clickSaveButton() {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Save")));
		appiumdriver.findElementByAccessibilityId("Save").click();
		if (appiumdriver.findElementsByAccessibilityId("Connecting to Back Office").size() > 0) {
			wait = new WebDriverWait(appiumdriver, 30);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(MobileBy.AccessibilityId("Connecting to Back Office")));
		}
	}

}
