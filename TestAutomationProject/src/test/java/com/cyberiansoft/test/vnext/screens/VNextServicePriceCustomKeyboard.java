package com.cyberiansoft.test.vnext.screens;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cyberiansoft.test.bo.webelements.ExtendedFieldDecorator;

public class VNextServicePriceCustomKeyboard extends VNextBaseScreen {
	
	@FindBy(xpath="//div[contains(@class, 'picker-modal picker-keypad picker-keypad-type-numpad')]")
	private WebElement keyboard;
	
	public VNextServicePriceCustomKeyboard(SwipeableWebDriver appiumdriver) {
		super(appiumdriver);
		PageFactory.initElements(new ExtendedFieldDecorator(appiumdriver), this);
		WebDriverWait wait = new WebDriverWait(appiumdriver, 15);
		wait.until(ExpectedConditions.visibilityOf(keyboard));
	}
	
	public void clickKeyboardBackspaceButton() {
		waitABit(1000);
		tap(keyboard.findElement(By.xpath(".//span[contains(@class, 'picker-keypad-delete')]")));		
	}
	
	public void clickKeyboardButton(char button) {
		tap(keyboard.findElement(By.xpath("./div[@class='picker-modal-inner picker-keypad-buttons']/span/span[text()='" + button + "']")));
	}
	
	public void clickKeyboardDoneButton() {
		tap(keyboard.findElement(By.xpath(".//a[@class='link close-picker']")));
	}
	
	public void clickKeyboardMinusButton() {
		tap(keyboard.findElement(By.xpath("./div[@class='picker-modal-inner picker-keypad-buttons']/span/span[text()='-/+']")));
	}
	
	public void setFieldValue(String existingvalue, String newvalue) {
		for (int i = 0; i <= existingvalue.length()+1; i++) {
			clickKeyboardBackspaceButton();
		}
		for (int i = 0; i < newvalue.length(); i++) {
			if (Character.toString(newvalue.charAt(i)).equals("-"))
				clickKeyboardMinusButton();
			else
				clickKeyboardButton(newvalue.charAt(i));
		}
		clickKeyboardDoneButton();
	}

}