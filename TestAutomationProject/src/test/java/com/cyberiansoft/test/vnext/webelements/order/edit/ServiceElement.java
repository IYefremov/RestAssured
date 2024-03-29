package com.cyberiansoft.test.vnext.webelements.order.edit;

import com.cyberiansoft.test.vnext.utils.WaitUtils;
import com.cyberiansoft.test.vnext.webelements.decoration.IWebElement;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Getter
public class ServiceElement implements IWebElement {
    private WebElement rootElement;
    private String statusLocator = ".//div[@class='icon-item-status-title']";
    private String nameLocator = ".//div[contains(@class,'icon-item-entity-name') or contains(@class,'icon-item-content-title') or contains(@class,'icon-item-phase-title')]";
    private String expandElementLocator = ".//*[@action='open-phase-services']";
    private String clockIconLocator = ".//*[@class='icon-svg ']";
    private String startDateLocator = ".//div[contains(@class,'icon-item-content-title')]/span[1]";
    private String technicianLocator = ".//div[@data-phase-tech]";
    private String checkElementLocator = ".//*[@action='check-item']";
    private String editElementLocator = ".//*[@action='edit-service']";
    private String notesElementLocator = ".//*[@data-service-notes]";
    private String startIconLocator = ".//div[@class='icon-item-content-title']/span[@class='icon-item-arrow']";
    private String editTaskActionButtonLocator = ".//div[@action='edit-task']//span[@class='icon-item-arrow']";
    private String priceWithQuantityElement = ".//div[contains(@class, 'amount-part-received')]/div";

    public ServiceElement(WebElement rootElement) {
        this.rootElement = rootElement;
    }

    public String getName() {
        return WaitUtils.getGeneralFluentWait().until(
                driver -> rootElement.findElement(By.xpath(nameLocator)).getText().trim()
        );
    }

    public String getTechnician() {
        return WaitUtils.getGeneralFluentWait().until(
                driver -> rootElement.findElement(By.xpath(technicianLocator)).getText().trim()
        );
    }

    public String getNotes() {
        return WaitUtils.getGeneralFluentWait().until(
                driver -> rootElement.findElement(By.xpath(notesElementLocator)).getText().trim()
        );
    }

    public String getStatus() {
        WaitUtils.elementShouldBeVisible(rootElement.findElement(By.xpath(statusLocator)), true);
        return rootElement.findElement(By.xpath(statusLocator)).getText();
    }

    public Boolean isClockIconPresent() {
        return rootElement.findElements(By.xpath(clockIconLocator)).size() > 0;
    }

    public Boolean isStartDatePresent() {
        return WaitUtils.isElementPresent(rootElement.findElement(By.xpath(startDateLocator)));
    }

    public Boolean isStartIconPresent() {
        return rootElement.findElements(By.xpath(startIconLocator)).size() > 0;
    }
}
