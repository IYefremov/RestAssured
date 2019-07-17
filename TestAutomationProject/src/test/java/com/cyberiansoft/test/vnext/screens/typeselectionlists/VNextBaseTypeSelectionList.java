package com.cyberiansoft.test.vnext.screens.typeselectionlists;

import com.cyberiansoft.test.baseutils.BaseUtils;
import com.cyberiansoft.test.vnext.screens.VNextBaseScreen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Getter
public class VNextBaseTypeSelectionList extends VNextBaseScreen {

    @FindBy(xpath="//*[@data-automation-id='search-icon']")
    private WebElement searchbtn;

    @FindBy(xpath="//*[@data-autotests-id='search-input']")
    private WebElement searchfld;

    public VNextBaseTypeSelectionList(AppiumDriver<MobileElement> appiumdriver) {
        super(appiumdriver);
        PageFactory.initElements(new AppiumFieldDecorator(appiumdriver), this);
    }

    public VNextBaseTypeSelectionList() {
    }

    public void selectType(String typeName) {
        final By typeXpath = By.xpath("//*[@class='item-title']/div[text()='" + typeName + "']");
        if (!(appiumdriver.findElements(typeXpath).size() > 0)) {
            clickSearchButton();
            setSearchText(typeName);
            WebDriverWait wait = new WebDriverWait(appiumdriver, 20);
            wait.until(ExpectedConditions.elementToBeClickable(typeXpath));

        } else {
            WebDriverWait wait = new WebDriverWait(appiumdriver, 20);
            wait.until(ExpectedConditions.elementToBeClickable(typeXpath));
            if (!appiumdriver.findElement(typeXpath).isDisplayed()) {
                WebElement elem = appiumdriver.findElement(typeXpath);
                JavascriptExecutor je = (JavascriptExecutor) appiumdriver;
                je.executeScript("arguments[0].scrollIntoView(true);", elem);
                BaseUtils.waitABit(1000);
            }
        }
        try {
            tap(appiumdriver.findElement(typeXpath));
        } catch (StaleElementReferenceException e) {
            BaseUtils.waitABit(1000);
            appiumdriver.findElement(typeXpath).click();
        }
    }

    public void clickSearchButton() {
        tap(searchbtn);
    }

    private void setSearchText(String searchtext) {
        tap(searchfld);
        searchfld.clear();
        searchfld.sendKeys(searchtext);
        //appiumdriver.hideKeyboard();
    }
}
