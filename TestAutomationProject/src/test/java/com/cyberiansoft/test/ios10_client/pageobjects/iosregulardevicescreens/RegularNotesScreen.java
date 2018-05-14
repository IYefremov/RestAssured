package com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens;

import com.cyberiansoft.test.ios10_client.utils.Helpers;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class RegularNotesScreen extends iOSRegularBaseScreen {

	final public static String quicknotesvalue = "This is test Quick Notes";

	@iOSFindBy(xpath = "//XCUIElementTypeButton[@label='Done'] ")
    private IOSElement donebtn;
	
	@iOSFindBy(accessibility = "Photo")
    private IOSElement phototab;
	
	@iOSFindBy(accessibility = "ImageSelectorCameraButton")
    private IOSElement camerabtn;
	
	@iOSFindBy(accessibility = "Cancel")
    private IOSElement cancelbtn;
	
	public RegularNotesScreen(AppiumDriver driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		appiumdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public void setNotes(String _notes) {
		//appiumdriver.findElementByXPath("//XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTextView").click();
		//appiumdriver.findElementByXPath("//XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTextView").click();
		//appiumdriver.findElementByAccessibilityId("txtNotes").sendKeys(_notes);
		appiumdriver.findElementByAccessibilityId("txtNotes").click();
		((IOSDriver) appiumdriver).getKeyboard().pressKey(_notes);
		appiumdriver.findElementByAccessibilityId("Done").click();
	}

	public void addQuickNotes() {
		appiumdriver.findElementByXPath("//XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeStaticText[@value='"
						+ quicknotesvalue + "'] ").click();
		//Helpers.hideKeyboard();
	}

	public String getNotesAndQuickNotes() {
		return appiumdriver.findElementByXPath("//XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTextView")
						.getAttribute("value");
	}
	
	public String getNotesValue() {
		Helpers.waitABit(500);
		return appiumdriver.findElementByXPath("//XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTextView")
						.getAttribute("value");
	}

	public void clickDoneButton() {
		donebtn.click();
	}
	
	public void clickCameraButton() {
		camerabtn.click();
	}
	
	public void addNotesCapture() throws InterruptedException {
		phototab.click();
		clickCameraButton();
		Helpers.makeCapture();
		cancelbtn.click();
	}
	
	public int getNumberOfAddedPhotos() {
		return appiumdriver.findElements(MobileBy.name("ImageSelectorImage")).size();
	}
	
	public boolean isNotesPresent(String _notes) {
		return appiumdriver.findElementsByAccessibilityId(_notes).size() > 0;
	}

	public void clickSaveButton() {
		appiumdriver.findElementByAccessibilityId("Save").click();
	}

}
