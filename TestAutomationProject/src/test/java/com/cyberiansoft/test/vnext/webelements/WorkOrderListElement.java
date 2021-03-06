package com.cyberiansoft.test.vnext.webelements;

import com.cyberiansoft.test.vnext.utils.WaitUtils;
import com.cyberiansoft.test.vnext.webelements.decoration.IWebElement;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Getter
public class WorkOrderListElement implements IWebElement {
    private WebElement rootElement;
    private String idFieldLocator = ".//div[@data-menu='popup']/div[1]";

    public WorkOrderListElement(WebElement rootElement) {
        this.rootElement = rootElement;
    }

    public String getId() {
        return WaitUtils.getGeneralFluentWait().until(driver -> rootElement.findElement(By.xpath(idFieldLocator)).getText());
    }

    public void openMenu() {
        WaitUtils.elementShouldBeVisible(rootElement.findElement(By.xpath(idFieldLocator)), true);
        WaitUtils.getGeneralFluentWait(5, 300).until(driver -> {
            rootElement.findElement(By.xpath(idFieldLocator)).click();
            return true;
        });
    }
}
