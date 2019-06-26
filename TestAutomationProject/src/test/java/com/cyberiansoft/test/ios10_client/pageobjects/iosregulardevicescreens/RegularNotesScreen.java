package com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens;

import com.cyberiansoft.test.ios10_client.utils.Helpers;
import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class RegularNotesScreen extends iOSRegularBaseScreen {

	final public static String quicknotesvalue = "This is test Quick Notes";

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@label='Done'] ")
    private IOSElement donebtn;
	
	@iOSXCUITFindBy(accessibility = "Photo")
    private IOSElement phototab;
	
	@iOSXCUITFindBy(accessibility = "ImageSelectorCameraButton")
    private IOSElement camerabtn;
	
	@iOSXCUITFindBy(accessibility = "Cancel")
    private IOSElement cancelbtn;
	
	public RegularNotesScreen() {
		super();
		PageFactory.initElements(new AppiumFieldDecorator(appiumdriver), this);
	}
	
	public void setNotes(String _notes) {
		//appiumdriver.findElementByXPath("//XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTextView").click();
		//appiumdriver.findElementByXPath("//XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTextView").click();
		//appiumdriver.findElementByAccessibilityId("txtNotes").sendKeys(_notes);
		appiumdriver.findElementByAccessibilityId("txtNotes").click();
		appiumdriver.findElementByAccessibilityId("txtNotes").sendKeys(_notes);
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
	
	public void addNotesCapture() {
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
