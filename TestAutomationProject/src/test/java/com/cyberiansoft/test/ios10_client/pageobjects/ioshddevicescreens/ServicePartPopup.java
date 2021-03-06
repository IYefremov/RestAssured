package com.cyberiansoft.test.ios10_client.pageobjects.ioshddevicescreens;

import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ServicePartPopup extends iOSHDBaseScreen {
	
	@iOSXCUITFindBy(accessibility = "Category")
    private IOSElement categorycell;
	
	@iOSXCUITFindBy(accessibility = "Subcategory")
    private IOSElement subcategorycell;
	
	@iOSXCUITFindBy(accessibility = "Part")
    private IOSElement partcell;
	
	@iOSXCUITFindBy(accessibility = "Position")
    private IOSElement positioncell;
	
	public ServicePartPopup() {
		super();
		PageFactory.initElements(new AppiumFieldDecorator(appiumdriver), this);
	}
	
	public void selectServicePartCategory(String categoryname) {
		//categorycell.click();
		appiumdriver.findElementByAccessibilityId(categoryname).click();
	}
	
	public String getServicePartCategoryValue() {
		WebElement par = getTableParentCell("Category");
		return par.findElement(By.xpath("//XCUIElementTypeStaticText[2]")).getAttribute("value");
	}
	
	public void selectCategory(String categoryname) {
		//categorycell.click();
		appiumdriver.findElementByAccessibilityId(categoryname).click();
	}
	
	public void selectServicePartSubcategory(String subcategoryname) {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId(subcategoryname)));
		appiumdriver.findElementByAccessibilityId(subcategoryname).click();
	}
	
	public String getServicePartSubCategoryValue() {
		WebElement par = getTableParentCell("Subcategory");
		return par.findElement(By.xpath("//XCUIElementTypeStaticText[2]")).getAttribute("value");
	}
	
	public void selectServicePartSubcategoryPart(String subcategorypartname) {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId(subcategorypartname)));
		appiumdriver.findElementByAccessibilityId(subcategorypartname).click();
		appiumdriver.findElementByAccessibilityId("Done").click();
	}
	
	public void selectServicePartSubcategoryPosition(String subcategorypositionname) {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId(subcategorypositionname)));
		appiumdriver.findElementByAccessibilityId(subcategorypositionname).click();
	}
	
	public void saveSelectedServicePart() {
		appiumdriver.findElement(MobileBy.xpath("//XCUIElementTypeOther/XCUIElementTypeNavigationBar[@name='Selected Parts']/XCUIElementTypeButton[@name='Done']")).click();
	}
	
	public WebElement getTableParentCell(String cellname) {
		return appiumdriver.findElement(MobileBy.xpath("//XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeStaticText[@label='" + cellname + "']/.."));
	}

}
