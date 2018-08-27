package com.cyberiansoft.test.vnext.screens;

import com.cyberiansoft.test.bo.webelements.ExtendedFieldDecorator;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VNextEnvironmentSelectionScreen extends VNextBaseScreen {

    @FindBy(xpath="//*[@data-page='env-list']")
    private WebElement environmentlistscreeen;

    @FindBy(xpath="//*[@data-autotests-id='env-list']")
    private WebElement envlist;

    public VNextEnvironmentSelectionScreen(AppiumDriver<MobileElement> appiumdriver) {
        super(appiumdriver);
        PageFactory.initElements(new ExtendedFieldDecorator(appiumdriver), this);
        WebDriverWait wait = new WebDriverWait(appiumdriver, 20);
        wait.until(ExpectedConditions.visibilityOf(environmentlistscreeen));
    }

    public VNextTeamEditionVerificationScreen selectEnvironment(String environmentName) {
        WebDriverWait wait = new WebDriverWait(appiumdriver, 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@data-env-name='" + environmentName + "']")));
        tap(envlist.findElement(By.xpath(".//div[@data-env-name='" + environmentName + "']")));
        return new VNextTeamEditionVerificationScreen(appiumdriver);
    }
}
