package com.cyberiansoft.test.ios10_client.pageobjects.ioshddevicescreens;

import java.util.concurrent.TimeUnit;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.cyberiansoft.test.ios10_client.utils.Helpers;

public class ClaimScreen extends iOSHDBaseScreen {
	
	
	/*@iOSFindBy(className = "XCUIElementTypeSearchField")
    private IOSElement insurancecompanysearchbar;
	
	@iOSFindBy(xpath = "//XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeStaticText[@name=\"Claim#\"]")
    private IOSElement claimfld;
	
	@iOSFindBy(accessibility = "Deductible")
    private IOSElement deductiblefld;
	
	@iOSFindBy(accessibility  = "Done")
    private IOSElement donebtn;*/
	
	public ClaimScreen(AppiumDriver driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		appiumdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public void selectInsuranceCompany(String insurancecompany) {
		IOSElement insurancecompanytable = (IOSElement) appiumdriver.findElementByAccessibilityId("ClaimInfoViewTable");
		//WebElement par = insurancecompanytable.findElement(MobileBy.xpath("//XCUIElementTypeTable[@name='ClaimInfoViewTable']/XCUIElementTypeCell/XCUIElementTypeStaticText[@value='Insurance Company']/.."));		
		//par.findElement(MobileBy.xpath("//XCUIElementTypeButton[@name='custom detail button'] ")).click();
		WebElement par = insurancecompanytable.findElement(MobileBy.xpath("//XCUIElementTypeCell/XCUIElementTypeStaticText[@value='Insurance Company']/.."));		
		par.findElement(MobileBy.name("custom detail button")).click();
		((IOSElement) appiumdriver.findElementByClassName("XCUIElementTypeSearchField")).setValue(insurancecompany);
		appiumdriver.findElement(MobileBy.iOSNsPredicateString("name = '"+ insurancecompany + "' and type = 'XCUIElementTypeCell'")).click();
		//appiumdriver.findElementByXPath("//XCUIElementTypeTable/XCUIElementTypeCell[@name=\""+ insurancecompany + "\"]").click();
	}

	public void setClaim(String claim) throws InterruptedException {
		appiumdriver.findElementByAccessibilityId("Claim#").click();
		((IOSDriver) appiumdriver).getKeyboard().pressKey(claim);
		((IOSDriver) appiumdriver).getKeyboard().pressKey("\n");
		Helpers.waitABit(500);

	}
	
	public void selectInsuranceCompanyAndSetClaim(String insurancecompany, String claim) throws InterruptedException {
		selectInsuranceCompany(insurancecompany);
		setClaim(claim);
	}
	

	public void setDeductible(String deductible)
			throws InterruptedException {

		appiumdriver.findElementByAccessibilityId("Deductible").click();
		((IOSDriver) appiumdriver).getKeyboard().pressKey(deductible);
		((IOSDriver) appiumdriver).getKeyboard().pressKey("\n");
		Helpers.waitABit(500);
	}

	public String getDeductibleValue() {
		return appiumdriver.
				findElementByXPath("//XCUIElementTypeTable/XCUIElementTypeCell[4]/XCUIElementTypeStaticText[2]").getAttribute("value");
	}

	public void setAccidentDate() {
		IOSElement insurancecompanytable = (IOSElement) appiumdriver.findElementByAccessibilityId("ClaimInfoViewTable");
		//WebElement par = insurancecompanytable.findElement(MobileBy.xpath("//XCUIElementTypeTable[@name='ClaimInfoViewTable']/XCUIElementTypeCell/XCUIElementTypeStaticText[@value='Insurance Company']/.."));		
		//par.findElement(MobileBy.xpath("//XCUIElementTypeButton[@name='custom detail button'] ")).click();
		WebElement par = insurancecompanytable.findElement(MobileBy.xpath("//XCUIElementTypeCell/XCUIElementTypeStaticText[@value='Accident date']/.."));		
		par.findElement(MobileBy.name("custom detail button")).click();
		appiumdriver.findElementByAccessibilityId("Done").click();
	}
	
	public String clickSaveWithAlert() {
		clickSaveButton();
		//appiumdriver.findElementByXPath("//XCUIElementTypeNavigationBar/XCUIElementTypeButton[@name='Save']").click();
		return Helpers.getAlertTextAndAccept();
	}

}
