package com.cyberiansoft.test.ios10_client.pageobjects.ioshddevicescreens;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

import com.cyberiansoft.test.ios_client.utils.Helpers;

public class InspectionScreen extends iOSHDBaseScreen {
	
	@iOSFindBy(accessibility = "Save")
    private IOSElement savechangesbtn;
	
	@iOSFindBy(accessibility  = "Advisor")
    private IOSElement advisorcell;

	public InspectionScreen(AppiumDriver driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver, 10, TimeUnit.SECONDS), this);
		appiumdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public void saveChanges() {
		savechangesbtn.click();
	}

	public String clickSaveWithAlert() {
		clickSaveButton();
		return Helpers.getAlertTextAndAccept();
	}

	public void setVIN(String vin) throws InterruptedException  {
		Helpers.setVIN(vin);
	}

	public String getMake() {
		return Helpers.getMake();
	}

	public String getModel() {
		return Helpers.getModel();

	}

	public String getYear() {
		return Helpers.getYear();
	}

	public void seletAdvisor(String advisor) {
		advisorcell.click();
		appiumdriver.findElement(MobileBy.IosUIAutomation(".popover().tableViews()[1].cells()[\""
						+ advisor + "\"]")).click();
	}
	
	public void seletRegularAdvisor(String advisor) {
		advisorcell.click();
		appiumdriver.findElement(MobileBy.IosUIAutomation(".scrollViews()[1].tableViews()[0].cells()[\""
						+ advisor + "\"]")).click();
	}

}