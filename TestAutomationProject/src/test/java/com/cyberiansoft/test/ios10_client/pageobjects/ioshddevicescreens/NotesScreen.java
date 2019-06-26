package com.cyberiansoft.test.ios10_client.pageobjects.ioshddevicescreens;

import com.cyberiansoft.test.ios10_client.utils.Helpers;
import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class NotesScreen extends iOSHDBaseScreen {

	final public static String quicknotesvalue = "This is test Quick Notes";

	@iOSXCUITFindBy(accessibility = "Done ")
    private IOSElement donebtn;
	
	@iOSXCUITFindBy(accessibility  = "ImageSelectorCameraButton")
    private IOSElement camerabtn;
	
	@iOSXCUITFindBy(accessibility  = "Cancel")
    private IOSElement cancelbtn;
	
	public NotesScreen() {
		super();
		PageFactory.initElements(new AppiumFieldDecorator(appiumdriver), this);
	}
	
	public void setNotes(String _notes) {
		WebElement el =appiumdriver.findElementByAccessibilityId("txtNotes");
		el.click();
		el.clear();
		el.sendKeys(_notes);
	}

	public void addQuickNotes() {
		appiumdriver.findElementByXPath("//XCUIElementTypeStaticText[@value='"
				+ quicknotesvalue + "']").click();
	}
	
	public String getNotesValue() {
		return appiumdriver.findElementByAccessibilityId("txtNotes")
						.getAttribute("value");
	}
	
	public boolean isNotesPresent(String _notes) {
		return appiumdriver.findElementsByXPath("//XCUIElementTypeStaticText[@value='"
				+ quicknotesvalue + "']").size() > 0;
	}

	public void clickDoneButton() {
		donebtn.click();
	}
	
	public void clickCameraButton() {
		camerabtn.click();
	}
	
	public void addNotesCapture() {
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
