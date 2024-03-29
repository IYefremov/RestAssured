package com.cyberiansoft.test.vnext.screens;

import com.cyberiansoft.test.driverutils.ChromeDriverProvider;
import com.cyberiansoft.test.vnext.webelements.WorkOrderListElement;
import com.cyberiansoft.test.vnext.webelements.decoration.FiledDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class VNextSelectWorkOrdersScreen extends VNextBaseScreen {


    private final By selectworkordersscreen = By.xpath("//div[@data-page='select-work-orders']");

    @FindBy(xpath="//*[@action='save']")
    private WebElement addworkordersbtn;

    @FindBy(xpath = "//*[@data-autotests-id='-list']/div")
    private List<WorkOrderListElement> workOrdersList;

    @FindBy(xpath="//*[@data-autotests-id='-list']")
    private WebElement workorderslist;

    public VNextSelectWorkOrdersScreen(WebDriver appiumdriver) {
        super(appiumdriver);
        PageFactory.initElements(appiumdriver, this);
        WebDriverWait wait = new WebDriverWait(appiumdriver, 60);
        wait.until(ExpectedConditions. presenceOfElementLocated(selectworkordersscreen));
    }

    public VNextSelectWorkOrdersScreen() {
        PageFactory.initElements(new FiledDecorator(ChromeDriverProvider.INSTANCE.getMobileChromeDriver()), this);
    }

    public WebElement getWorkOrderCell(String wonumber) {
        List<WebElement> workorders = workorderslist.findElements(By.xpath(".//*[@class='entity-item accordion-item order-select-item']"));
        WebElement wocell = workorders.stream().filter(elemnt -> elemnt.findElement(By.xpath(".//div[@class='checkbox-item-title']")).getText().trim().equals(wonumber))
                    .findFirst().orElse(null);
        if (wocell == null)
            Assert.fail( "Can't find work order: " + wonumber);
        return wocell;
    }

    public void selectWorkOrder(String wonumber) {
        WebElement workordercell = getWorkOrderCell(wonumber);
        if (workordercell.findElement(By.xpath(".//input[@type='checkbox']")).getAttribute("checked") == null)
            tap(workordercell.findElement(By.xpath(".//input[@type='checkbox']")));
    }

    public void unselectWorkOrder(String wonumber) {
        WebElement workordercell = getWorkOrderCell(wonumber);
        if (workordercell.findElement(By.xpath(".//input[@type='checkbox']")).getAttribute("checked") != null)
            tap(workordercell.findElement(By.xpath(".//input[@type='checkbox']")));
    }

    public void clickAddWorkOrders() {
        tap(addworkordersbtn);
    }
}
