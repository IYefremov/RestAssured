package com.cyberiansoft.test.vnext.screens;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cyberiansoft.test.bo.webelements.ExtendedFieldDecorator;
import com.relevantcodes.extentreports.LogStatus;

public class VNextInformationDialog extends VNextBaseScreen {
	
	@FindBy(xpath="//div[@class='modal modal-in']")
	private WebElement modaldlg;
	
	@FindBy(xpath="//div[@class='modal-text']")
	private WebElement modaldlgmsg;
	
	public VNextInformationDialog(SwipeableWebDriver appiumdriver) {
		super(appiumdriver);
		PageFactory.initElements(new ExtendedFieldDecorator(appiumdriver), this);	
		WebDriverWait wait = new WebDriverWait(appiumdriver, 360);
		wait.until(ExpectedConditions.visibilityOf(modaldlg));
	}
	
	public String getInformationDialogMessage() {
		return modaldlgmsg.getText();
	}
	
	public void clickInformationDialogOKButton() {
		tap(modaldlg.findElement(By.xpath(".//span[text()='OK']")));
		if (testReporter != null)
			testReporter.log(LogStatus.INFO, "Tap Information Dialog OK Button");
	}
	
	public void clickInformationDialogYesButton() {
		tap(modaldlg.findElement(By.xpath(".//span[text()='Yes']")));
		if (testReporter != null)
			testReporter.log(LogStatus.INFO, "Tap Information Dialog Yes Button");
	}
	
	public void clickInformationDialogNoButton() {
		tap(modaldlg.findElement(By.xpath(".//span[text()='No']")));
		if (testReporter != null)
			testReporter.log(LogStatus.INFO, "Tap Information Dialog No Button");
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
	
	public String clickInformationDialogNoButtonAndGetMessage() {
		String msg = getInformationDialogMessage();
		clickInformationDialogNoButton();
		return msg;
	}
	
	

}
