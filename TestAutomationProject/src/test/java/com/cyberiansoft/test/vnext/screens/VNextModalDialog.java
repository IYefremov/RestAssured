package com.cyberiansoft.test.vnext.screens;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VNextModalDialog extends VNextBaseScreen {

	@FindBy(id="dialogModal")
	private WebElement modaldlg;
	
	@FindBy(xpath="//*[@class='modal-body__content']")
	private WebElement modaldlgmsg;

    public VNextModalDialog(WebDriver appiumdriver) {
		super(appiumdriver);
        PageFactory.initElements(appiumdriver, this);
		WebDriverWait wait = new WebDriverWait(appiumdriver, 400);
		wait.until(ExpectedConditions.visibilityOf(modaldlg));
	}
	
	public String getInformationDialogMessage() {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 10);
		wait.until(ExpectedConditions.visibilityOf(modaldlgmsg));
		return modaldlgmsg.getText();
	}
	
	public void clickInformationDialogOKButton() {
		WebDriverWait wait = new WebDriverWait(appiumdriver, 400);
		wait.until(ExpectedConditions.visibilityOf(modaldlg.findElement(By.xpath(".//button[contains(@data-bind, 'confirm')]"))));
		tap(modaldlg.findElement(By.xpath(".//button[contains(@data-bind, 'confirm')]")));
	}
	
	public void clickInformationDialogYesButton() {
		tap(modaldlg.findElement(By.xpath(".//button[contains(@data-bind, 'confirm')]")));
	}
	
	public void clickInformationDialogCancelButton() {
		tap(modaldlg.findElement(By.xpath(".//button[contains(@data-bind, 'cancel')]")));
	}
	
	public String clickInformationDialogOKButtonAndGetMessage() {
		String msg = getInformationDialogMessage();
		clickInformationDialogOKButton();
		return msg;
	}
	
	public String clickInformationDialogYesButtonAndGetMessage() {
		String msg = getInformationDialogMessage();
		clickInformationDialogYesButton();
		return msg;
	}
	
	public String clickInformationDialogCancelButtonAndGetMessage() {
		String msg = getInformationDialogMessage();
		clickInformationDialogCancelButton();
		return msg;
	}

}
