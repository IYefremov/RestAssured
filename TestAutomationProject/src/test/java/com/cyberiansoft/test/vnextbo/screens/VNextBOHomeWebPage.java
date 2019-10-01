package com.cyberiansoft.test.vnextbo.screens;

import com.cyberiansoft.test.baseutils.Utils;
import com.cyberiansoft.test.bo.pageobjects.webpages.HomeWebPage;
import com.cyberiansoft.test.bo.webelements.ExtendedFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class VNextBOHomeWebPage extends VNextBOBaseWebPage {

    @FindBy(xpath = "//a[@class='logo customLogo']")
    private WebElement logo;

    @FindBy(xpath = "//span[contains(@data-bind, 'username')]")
    private WebElement userEmail;

    @FindBy(xpath = "//a[@class='login user-info__block']")
    private WebElement logoutButton;

    @FindBy(id = "helpMenu")
    private WebElement helpButton;

    @FindBy(xpath = "//a[contains(@data-bind, 'help.lmsUrl') and contains(text(), 'Learn')]")
    private WebElement helpLearnButton;

    @FindBy(xpath = "//a[text()='Access Client Portal']")
    private WebElement accessClientPortalLink;

    @FindBy(xpath = "//a[text()='Access ReconPro BackOffice']")
    private WebElement accessReconProBOLink;

    @FindBy(xpath = "//div[@class='support-buttons-row']/a[contains(@href, 'back-office')]")
    private WebElement supportForBOButton;

    @FindBy(xpath = "//div[@class='support-buttons-row']/a[contains(@href, 'mobile-app')]")
    private WebElement supportForMobileAppButton;

    @FindBy(xpath = "//a[@data-bind='click: showTermsAndConditions']")
    private WebElement termsAndConditionsLink;

    @FindBy(xpath = "//a[@data-bind='click: showPrivacyPolicy']")
    private WebElement privacyPolicyLink;

    @FindBy(xpath = "//iframe[@name='intercom-launcher-frame']")
    private WebElement intercomFrame;

    @FindBy(xpath = "//div[contains(@class, 'intercom-launcher')]")
    private WebElement intercom;

    @FindBy(xpath = "//div[@aria-label='Close Intercom Messenger']")
    private WebElement closeIntercomButton;

    @FindBy(xpath = "//div[contains(@class, 'intercom-launcher' ) and contains (@class, 'active')]")
    private WebElement intercomOpen;

    public VNextBOHomeWebPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new ExtendedFieldDecorator(driver), this);
        new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOf(logo));
    }

    public boolean isLogoDisplayed() {
        return Utils.isElementDisplayed(logo);
    }

    public boolean isUserEmailDisplayed() {
        return Utils.isElementDisplayed(userEmail);
    }

    public boolean isLogoutButtonDisplayed() {
        return Utils.isElementDisplayed(logoutButton);
    }

    public boolean isHelpButtonDisplayed() {
        return Utils.isElementDisplayed(helpButton);
    }

    public boolean isAccessClientPortalLinkDisplayed() {
        return Utils.isElementDisplayed(accessClientPortalLink);
    }

    public boolean isAccessReconProBOLinkDisplayed() {
        return Utils.isElementDisplayed(accessReconProBOLink);
    }

    public boolean isSupportForBOButtonDisplayed() {
        return Utils.isElementDisplayed(supportForBOButton);
    }

    public boolean isSupportForMobileAppButtonDisplayed() {
        return Utils.isElementDisplayed(supportForMobileAppButton);
    }

    public boolean isTermsAndConditionsLinkDisplayed() {
        return Utils.isElementDisplayed(termsAndConditionsLink);
    }

    public boolean isPrivacyPolicyLinkDisplayed() {
        return Utils.isElementDisplayed(privacyPolicyLink);
    }

    public boolean isIntercomDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(intercomFrame));
        driver.switchTo().frame(intercomFrame);
        return isElementDisplayed(intercom);
    }

    public VNextBOHomeWebPage clickLogo() {
        wait.until(ExpectedConditions.elementToBeClickable(logo)).click();
        waitForLoading();
        return this;
    }

    public VNextBOUserProfileDialog clickUserEmail() {
        wait.until(ExpectedConditions.elementToBeClickable(userEmail)).click();
        waitForLoading();
        return PageFactory.initElements(driver, VNextBOUserProfileDialog.class);
    }

    public VNextBOLoginScreenWebPage clickLogoutButton() {
        clickButton(logoutButton);
        waitForLoading();
        return PageFactory.initElements(driver, VNextBOLoginScreenWebPage.class);
    }

    public String openHelpWindow(String mainWindow) {
        clickButton(helpButton);
        waitForNewTab();
        return getNewTab(mainWindow);
    }

    public String openLearnWindow(String mainWindow) {
        wait.until(ExpectedConditions.not(ExpectedConditions.stalenessOf(helpButton)));
        actions.moveToElement(helpButton).build().perform();
        wait.until(ExpectedConditions.elementToBeClickable(helpLearnButton)).click();
        waitForNewTab();
        return getNewTab(mainWindow);
    }

    public String openAccessClientPortalWindow(String mainWindow) {
        Utils.clickElement(accessClientPortalLink);
        waitForNewTab();
        return getNewTab(mainWindow);
    }

    public String openSupportForBOWindow(String mainWindow) {
        Utils.clickElement(supportForBOButton);
        waitForNewTab();
        return getNewTab(mainWindow);
    }

    public String openSupportForMobileAppWindow(String mainWindow) {
        Utils.clickElement(supportForMobileAppButton);
        waitForNewTab();
        return getNewTab(mainWindow);
    }

    public String openAccessReconProBOWindow(String mainWindow) {
        Utils.clickElement(accessReconProBOLink);
        final HomeWebPage BOHomeWebPage = PageFactory.initElements(driver, HomeWebPage.class);
        BOHomeWebPage.waitForLoading();
        return getNewTab(mainWindow);
    }

    public VNextBOTermsAndConditionsDialog clickTermsAndConditionsLink() {
        Utils.clickElement(termsAndConditionsLink);
        waitForLoading();
        return PageFactory.initElements(driver, VNextBOTermsAndConditionsDialog.class);
    }

    public VNextBOPrivacyPolicyDialog clickPrivacyPolicyLink() {
        Utils.clickElement(termsAndConditionsLink);
        waitForLoading();
        return PageFactory.initElements(driver, VNextBOPrivacyPolicyDialog.class);
    }

    public void clickButton(WebElement link) {
        wait.until(ExpectedConditions.not(ExpectedConditions.stalenessOf(link)));
        wait.until(ExpectedConditions.elementToBeClickable(link)).click();
    }

    public void openIntercom() {
        wait.until(ExpectedConditions.visibilityOf(intercomFrame));
        driver.switchTo().frame(intercomFrame);
        wait.until(ExpectedConditions.elementToBeClickable(intercom)).click();
    }

    public boolean isIntercomOpened() {
        try {
            waitShort.until(ExpectedConditions.visibilityOf(intercomOpen));
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

    public void closeIntercom() {
        wait.until(ExpectedConditions.elementToBeClickable(closeIntercomButton)).click();
        wait.until(ExpectedConditions.visibilityOf(intercom));
        driver.switchTo().window(driver.getWindowHandle());
    }
}
