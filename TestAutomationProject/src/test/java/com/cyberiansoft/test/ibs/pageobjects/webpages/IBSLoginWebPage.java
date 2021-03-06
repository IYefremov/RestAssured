package com.cyberiansoft.test.ibs.pageobjects.webpages;

import com.cyberiansoft.test.bo.pageobjects.webpages.BaseWebPage;
import com.cyberiansoft.test.bo.webelements.ExtendedFieldDecorator;
import com.cyberiansoft.test.bo.webelements.TextField;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.cyberiansoft.test.bo.utils.WebElementsBot.clearAndType;
import static com.cyberiansoft.test.bo.utils.WebElementsBot.click;

public class IBSLoginWebPage extends BaseWebPage {
	
	@FindBy(id = "txtUserName")
	private TextField userNameField;
	
	@FindBy(id = "txtPassword")
	private TextField userPasswordField;
	
	@FindBy(id = "btn-login")
	private WebElement loginButton;
	
	public IBSLoginWebPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(new ExtendedFieldDecorator(driver), this);
	}
	
	public IBSDashboardPage UserLogin(String userName, String userPassword) {
		wait.until(ExpectedConditions.visibilityOf(loginButton));
		clearAndType(userNameField, userName);
		clearAndType(userPasswordField, userPassword);
		click(loginButton);
        return PageFactory.initElements(driver, IBSDashboardPage.class);
	}
}
