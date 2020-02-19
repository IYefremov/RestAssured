package com.cyberiansoft.test.vnext.screens;

import com.cyberiansoft.test.baseutils.BaseUtils;
import com.cyberiansoft.test.dataclasses.AppCustomer;
import com.cyberiansoft.test.driverutils.ChromeDriverProvider;
import com.cyberiansoft.test.vnext.interactions.HelpingScreenInteractions;
import com.cyberiansoft.test.vnext.screens.customers.VNextCustomersScreen;
import com.cyberiansoft.test.vnext.screens.typesscreens.VNextInspectionsScreen;
import com.cyberiansoft.test.vnext.screens.typesscreens.VNextInvoicesScreen;
import com.cyberiansoft.test.vnext.screens.typesscreens.VNextWorkOrdersScreen;
import com.cyberiansoft.test.vnext.screens.wizardscreens.VNextVehicleInfoScreen;
import com.cyberiansoft.test.vnext.utils.WaitUtils;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Getter
public class VNextHomeScreen extends VNextBaseScreen {

    @FindBy(xpath = "//div[@data-page='home']")
    private WebElement rootElement;

    @FindBy(xpath = "//*[@action='navigate-to-customers']")
    private WebElement customerslist;

    @FindBy(xpath = "//span[@class='client-mode']")
    private WebElement clientmode;

    @FindBy(xpath = "//*[@action='navigate-to-inspections']")
    private WebElement inspectionslist;

    @FindBy(xpath = "//*[@action='navigate-to-orders']")
    private WebElement workorderslist;

    @FindBy(xpath = "//*[@action='navigate-to-invoices']")
    private WebElement invoiceslist;

    @FindBy(xpath = "//*[@action='work-queue']")
    private WebElement workQueue;

    @FindBy(xpath = "//*[@action='monitor-update-work']")
    private WebElement monitorUpdateWork;

    @FindBy(xpath = "//*[@action='navigate-to-monitor']")
    private WebElement monitor;

    @FindBy(xpath = "//a[@class='tile-link tile-item more-tile']")
    private WebElement morelist;

    @FindBy(xpath = "//*[@action='navigate-to-settings']")
    private WebElement settingslist;

    @FindBy(xpath = "//*[@action='navigate-to-status']")
    private WebElement statuslist;

    @FindBy(xpath = "//*[@action='messager-send']/span[@class='messager-counter']")
    private WebElement queuemessage;

    @FindBy(xpath = "//*[@action='messager-send']")
    private WebElement queuemessageicon;

    @FindBy(xpath = "//*[@action='logout']")
    private WebElement logoutbtn;

    @FindBy(xpath = "//div[@class='speed-dial']/a[@class='floating-button color-red']")
    private WebElement addbtn;

    @FindBy(xpath = "//*[@action='new_order']")
    private WebElement newworkorderbtn;

    @FindBy(xpath = "//*[@action='new_inspection']")
    private WebElement newinspectionbtn;

    public VNextHomeScreen(WebDriver appiumdriver) {
        super(appiumdriver);
        PageFactory.initElements(appiumdriver, this);
        WaitUtils.elementShouldBeVisible(getRootElement(),true);
    }

    public VNextHomeScreen() {
    }

    public VNextCustomersScreen clickCustomersMenuItem() {
        tap(customerslist);
        return new VNextCustomersScreen(appiumdriver);
    }

    public VNextWorkOrdersScreen clickWorkOrdersMenuItem() {
        //waitABit(2000);
        WaitUtils.elementShouldBeVisible(workorderslist,true);
        tap(workorderslist);
        return new VNextWorkOrdersScreen(appiumdriver);
    }

    @Step
    public VNextInspectionsScreen clickInspectionsMenuItem() {
        WebDriver webDriver = ChromeDriverProvider.INSTANCE.getMobileChromeDriver();
        WaitUtils.getGeneralFluentWait().until(driver -> {
            JavascriptExecutor executor = (JavascriptExecutor) webDriver;
            executor.executeScript("arguments[0].click();", inspectionslist);
            return true;
        });
        return new VNextInspectionsScreen(appiumdriver);
    }

    public VNextInvoicesScreen clickInvoicesMenuItem() {
        tap(invoiceslist);
        BaseUtils.waitABit(2000);
        return new VNextInvoicesScreen();
    }

    public VNextSettingsScreen clickSettingsMenuItem() {
        if (!settingslist.isDisplayed())
            tap(morelist);
        tap(settingslist);
        return new VNextSettingsScreen(appiumdriver);
    }

    public VNextStatusScreen clickStatusMenuItem() {
        WebDriver webDriver = ChromeDriverProvider.INSTANCE.getMobileChromeDriver();
        JavascriptExecutor executor = (JavascriptExecutor) webDriver;
        executor.executeScript("arguments[0].click();", statuslist);
        return new VNextStatusScreen(appiumdriver);
    }

    public void clickQueueMessageIcon() {
        tap(queuemessageicon);
        BaseUtils.waitABit(500);
    }

    public String getQueueMessageValue() {
        WaitUtils.elementShouldBeVisible(queuemessage, true);
        return queuemessage.getText();
    }

    public boolean isQueueMessageVisible() {
        return WaitUtils.isElementPresent(By.xpath("//*[@action='messager-send']"));
    }

    public void waitUntilQueueMessageInvisible() {
        WebDriverWait wait = new WebDriverWait(appiumdriver, 240);
        wait.until(ExpectedConditions.invisibilityOf(appiumdriver.findElement(By.xpath("//*[@action='messager-send']"))));
    }

    public VNextLoginScreen clickLogoutButton() {
        tap(logoutbtn);
        return new VNextLoginScreen(appiumdriver);
    }

    public VNextVehicleInfoScreen openCreateWOWizard(AppCustomer testcustomer) {
        VNextWorkOrdersScreen workordersscreen = clickWorkOrdersMenuItem();
        VNextCustomersScreen customersscreen = workordersscreen.clickAddWorkOrderButton();
        customersscreen.selectCustomer(testcustomer);
        VNextVehicleInfoScreen vehicleInfoScreen = new VNextVehicleInfoScreen();
        HelpingScreenInteractions.dismissHelpingScreenIfPresent();
        return vehicleInfoScreen;
    }

    public void clickUpgrateToProBanner() {
        tap(appiumdriver.findElement(By.xpath("//div[@class='upgrade-image' and @action='ad']")));
    }

    public boolean isUpgrateToProBannerVisible() {
        return appiumdriver.findElement(By.xpath("//div[@class='upgrade-image' and @action='ad']")).isDisplayed();
    }

    public void clickAddButton() {
        tap(addbtn);
    }

    public VNextCustomersScreen clickNewWorkOrderPopupMenu() {
        clickAddButton();
        tap(newworkorderbtn);
        return new VNextCustomersScreen(appiumdriver);
    }

    public VNextCustomersScreen clickNewInspectionPopupMenu() {
        clickAddButton();
        tap(newinspectionbtn);
        return new VNextCustomersScreen(appiumdriver);
    }

    public String getDefaultCustomerValue() {
        return clientmode.getText();
    }

    public void clickMonitor() {
        WaitUtils.elementShouldBeVisible(workQueue,true);
        workQueue.click();
    }
}
