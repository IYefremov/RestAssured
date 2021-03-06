package com.cyberiansoft.test.vnext.screens;

import com.cyberiansoft.test.baseutils.BaseUtils;
import com.cyberiansoft.test.vnext.screens.typesscreens.VNextInvoicesScreen;
import com.cyberiansoft.test.vnext.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VNextChangeInvoicePONumberDialog extends VNextBaseScreen {
	
	@FindBy(id="InvoicesChangePO")
	private WebElement changeponumberfld;
	
	@FindBy(xpath="//span[contains(@class, 'modal-button') and text()='Save']")
	private WebElement savebtn;

    public VNextChangeInvoicePONumberDialog(WebDriver appiumdriver) {
		super(appiumdriver);
        PageFactory.initElements(appiumdriver, this);
	}
	
	public VNextInvoicesScreen changeInvoicePONumber(String poNumber) {
		setInvoicePONumber(poNumber);
		clickSaveButton();
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		try {
			wait.until(ExpectedConditions.invisibilityOf(appiumdriver.findElement(By.
					xpath("//div[@class='notifier' and @data-type='success']"))));
		} catch (NoSuchElementException e) {
			//
		}
		return new VNextInvoicesScreen();
	}

	
	public void setInvoicePONumber(String poNumber) {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("InvoicesChangePO")));
		wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.visibilityOf(changeponumberfld));
		changeponumberfld.clear();
		changeponumberfld.sendKeys(poNumber);
		BaseUtils.waitABit(1000);
	}

	public String getInvoicePreviousPONumber() {
		WaitUtils.elementShouldBeVisible(changeponumberfld, true);
		return changeponumberfld.getAttribute("value");
	}
	
	public void clickSaveButton() {
		tap(savebtn);
	}
	
	public void clickDontSaveButton() {
		tap(appiumdriver.findElement(By.xpath(".//div[@class='modal-buttons']/span[@class='modal-button ']")));
	}
	
	public boolean isPONUmberShouldntBeEmptyErrorAppears() {
		return appiumdriver.findElement(By.xpath("//div[@class='modal-validation']")).isDisplayed();
	}
}
