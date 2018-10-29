package com.cyberiansoft.test.vnext.screens;

import com.cyberiansoft.test.vnext.screens.wizardscreens.VNextBaseWizardScreen;
import com.cyberiansoft.test.vnext.screens.wizardscreens.VNextVehicleInfoScreen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VNextVehicleVINHistoryScreen extends VNextBaseWizardScreen {

    @FindBy(xpath="//*[@data-page='long-text']")
    private WebElement vinhistoryscreen;

    public VNextVehicleVINHistoryScreen(AppiumDriver<MobileElement> appiumdriver) {
        super(appiumdriver);
        PageFactory.initElements(new AppiumFieldDecorator(appiumdriver), this);
        WebDriverWait wait = new WebDriverWait(appiumdriver, 60);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@data-page='long-text']")));
    }

    public VNextVehicleInfoScreen clickBackButton() {
        clickScreenBackButton();
        return new VNextVehicleInfoScreen(appiumdriver);
    }
}