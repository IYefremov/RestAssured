package com.cyberiansoft.test.baseutils;

import com.cyberiansoft.test.driverutils.DriverBuilder;
import com.cyberiansoft.test.vnextbo.screens.VNextBOBaseWebPage;
import org.awaitility.core.ConditionTimeoutException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

public class WaitUtilsWebDriver {

    public static WebDriverWait getWebDriverWait(int timeout) {
        return new WebDriverWait(DriverBuilder.getInstance().getDriver(), timeout);
    }

    public static WebDriverWait getWait() {
        return getWebDriverWait(15);
    }

    public static WebDriverWait getShortWait() {
        return getWebDriverWait(5);
    }

    public static WebDriverWait getLongWait() {
        return getWebDriverWait(30);
    }

    public static void waitABit(int milliSeconds) {
        if (milliSeconds > 0) {
            try {
                TimeUnit.MILLISECONDS.sleep(milliSeconds);
            } catch (InterruptedException ignored) {}
        }
    }

    public static FluentWait<WebDriver> getFluentWait() {
        return getFluentWait(Duration.ofMillis(500), Duration.ofSeconds(15));
    }

    public static FluentWait<WebDriver> getFluentWait(Duration pollingMillis, Duration timeout) {
        return new FluentWait<>(DriverBuilder.getInstance().getDriver())
                .pollingEvery(pollingMillis)
                .withTimeout(timeout)
                .ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class)
                .ignoring(WebDriverException.class);
    }

    @Deprecated
    public static void waitForLoading() {
        try {
            waitForVisibility(VNextBOBaseWebPage.loadingProcess);
        } catch (Exception ignored) {}
        try {
            waitForInvisibility(VNextBOBaseWebPage.loadingProcess);
        } catch (Exception ignored) {}
    }

    public static void waitForSpinnerToDisappear() {
        try {
            ConditionWaiter.create(__ -> VNextBOBaseWebPage.spinner.isDisplayed()).execute();
            WebElement spinner = DriverBuilder.getInstance().getDriver().findElement(By.xpath("//div[contains(@class, 'k-loading-image')]"));
            ConditionWaiter.create(15000, 500, __ -> !spinner.isDisplayed()).execute();
        } catch (Exception ex) { }
    }

    public static void waitForSpinnerToDisappear(int timeout) {
        elementShouldBeVisible(VNextBOBaseWebPage.spinner, false, timeout);
    }

    public static void waitForPageToBeLoaded() {
        waitUntilPageIsLoadedWithJs();
        waitForPendingRequestsToComplete();
        waitForSpinnerToDisappear();
    }

    public static void waitForPageToBeLoaded(int timeout) {
        waitUntilPageIsLoadedWithJs();
        waitForPendingRequestsToComplete();
        waitForSpinnerToDisappear(timeout);
    }

    public static WebElement waitForVisibility(WebElement element) {
        return getWait().until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitForVisibility(WebElement element, int timeout) {
        return getWebDriverWait(timeout).until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitForVisibility(By xpath) {
        return getWait().until(ExpectedConditions.visibilityOf(DriverBuilder.getInstance().getDriver().findElement(xpath)));
    }

    public static WebElement waitForVisibility(By xpath, int timeout) {
        return getWebDriverWait(timeout)
                .until(ExpectedConditions.visibilityOf(DriverBuilder.getInstance().getDriver().findElement(xpath)));
    }

    public static void waitForInvisibility(WebElement element) {
        getWait().until(ExpectedConditions.invisibilityOf(element));
    }

    public static void waitForInvisibility(WebElement element, int timeoutInSeconds) {
        getWebDriverWait(timeoutInSeconds).until((ExpectedCondition<Boolean>) driver -> Utils.isElementInvisible(element));
    }

    public static void waitForInvisibility(By by) {
        getWait().until(ExpectedConditions.invisibilityOf(DriverBuilder.getInstance().getDriver().findElement(by)));
    }

    public static void waitForInvisibility(By by, int timeoutInSeconds) {
        final WebDriver driver = DriverBuilder.getInstance().getDriver();
        getWebDriverWait(timeoutInSeconds).until(ExpectedConditions.invisibilityOf(driver.findElement(by)));
    }

    public static void waitForInvisibilityIgnoringException(WebElement element) {
        getFluentWait(Duration.ofMillis(500), Duration.ofSeconds(15)).until(ExpectedConditions.invisibilityOf(element));
    }

    public static void waitForInvisibilityIgnoringException(WebElement element, int timeoutSeconds) {
        getFluentWait(Duration.ofMillis(500), Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.invisibilityOf(element));
    }

    public static WebElement waitForElementToBeClickable(WebElement element) {
        return getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement waitForElementToBeClickable(WebElement element, int timeoutSeconds) {
        return getWebDriverWait(timeoutSeconds).until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForNewTab() {
        try {
            getLongWait().until((ExpectedCondition<Boolean>) driver -> (
                    Objects
                            .requireNonNull(driver)
                            .getWindowHandles()
                            .size() != 1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void waitForUrl() {
        try {
            await().atMost(15, TimeUnit.SECONDS)
                    .ignoreExceptions()
                    .pollInterval(500, TimeUnit.MILLISECONDS)
                    .until(DriverBuilder.getInstance().getDriver()::getCurrentUrl);
        } catch (ConditionTimeoutException ignored) {}
    }

    public static List<WebElement> waitForVisibilityOfAllOptions(List<WebElement> listBox) {
        return waitForVisibilityOfAllOptions(listBox, 5);
    }

    public static List<WebElement> waitForVisibilityOfAllOptions(List<WebElement> listBox, int timeoutSeconds) {
        try {
            getWebDriverWait(timeoutSeconds).until((ExpectedCondition<Boolean>) driver -> listBox.size() > 0);
        } catch (Exception ignored) {}
        return listBox;
    }

    public static void waitForVisibilityIgnoringException(WebElement element) {
        waitForVisibilityIgnoringException(element, 15);
    }

    public static void waitForVisibilityIgnoringException(WebElement element, int timeoutSeconds) {
        getFluentWait(Duration.ofMillis(500), Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForDropDownToBeOpened(WebElement dropDown) {
        try {
            waitForAttributeToBe(dropDown, "aria-hidden", "false", 2);
        } catch (Exception ignored) {}
    }

    public static void waitForDropDownToBeClosed(WebElement dropDown) {
        waitForDropDownToBeClosed(dropDown, 5);
    }

    public static void waitForDropDownToBeClosed(WebElement dropDown, int timeout) {
        try {
            waitForElementNotToBeStale(dropDown, timeout);
            waitForAttributeToBe(dropDown, "aria-hidden", "true", 1);
        } catch (Exception ignored) {}
    }

    public static void waitForListToDisappear(WebElement list) {
        try {
            waitForAttributeToBe(list, "aria-hidden", "true");
        } catch (Exception e) {
            try {
                new Actions(DriverBuilder.getInstance().getDriver()).moveToElement(list).sendKeys(Keys.ENTER).build().perform();
                waitForAttributeToBe(list, "aria-hidden", "true");
            } catch (Exception ignored) {
                waitABit(1000);
            }
        }
    }

    public static boolean waitForAttributeToBe(WebElement element, String attribute, String value) {
        return waitForAttributeToBe(element, attribute, value, 5);
    }

    public static boolean waitForAttributeToBe(WebElement element, String attribute, String value, int timeout) {
        return getWebDriverWait(timeout).until(ExpectedConditions.attributeToBe(element, attribute, value));
    }

    public static boolean waitForAttributeToContain(WebElement element, String attribute, String value) {
        return getWait().until(ExpectedConditions.attributeContains(element, attribute, value));
    }

    public static boolean waitForAttributeToContain(WebElement element, String attribute, String value, int timeout) {
        try {
            return getWebDriverWait(timeout).until(ExpectedConditions.attributeContains(element, attribute, value));
        } catch (TimeoutException ignored) {
            return false;
        }
    }

    public static boolean waitForAttributeNotToContain(WebElement element, String attribute, String value, int timeout) {
        try {
            return getWebDriverWait(timeout)
                    .until(ExpectedConditions.not(ExpectedConditions.attributeContains(element, attribute, value)));
        } catch (Exception ignored) {
            return false;
        }
    }

    public static boolean waitForAttributeNotToContain(WebElement element, String attribute, String value) {
        return waitForAttributeNotToContain(element, attribute, value, 5);
    }

    public static boolean waitForAttributeToContainIgnoringException(WebElement element, String attribute, String value) {
        try {
            return waitForAttributeToContain(element, attribute, value);
        } catch (Exception ignored) {
            return false;
        }
    }

    public static boolean waitForAttributeToContainIgnoringException(WebElement element, String attribute, String value, int timeOut) {
        try {
            return waitForAttributeToContain(element, attribute, value, timeOut);
        } catch (Exception ignored) {
            ignored.printStackTrace();
            return false;
        }
    }

    public static void waitForInputFieldValueIgnoringException(WebElement element, String value) {
        getFluentWait().until(ExpectedConditions.attributeToBe(element, "value", value));
    }

    public static void waitForTextToBePresentInElement(WebElement element, String text) {
        getWait().until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public static void waitForTextToBePresentInElement(WebElement element, String text, int timeOut) {
        getWebDriverWait(timeOut).until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public static WebElement waitForElementNotToBeStale(WebElement element) {
        return waitForElementNotToBeStale(element, 10);
    }

    public static WebElement waitForElementNotToBeStale(WebElement element, int timeOut) {
        try {
            getWebDriverWait(timeOut).until(ExpectedConditions.not(ExpectedConditions.stalenessOf(element)));
        } catch (Exception ignored) {}
        return element;
    }

    public static WebElement waitForElementNotToBeStale(By by) {
        return waitForElementNotToBeStale(DriverBuilder.getInstance().getDriver().findElement(by));
    }

    public static void waitForPendingRequestsToComplete() {

        boolean requestsAreCompleted;
        try {
            do {
                requestsAreCompleted = (boolean) ((JavascriptExecutor) DriverBuilder.getInstance().getDriver()).executeScript("return angular.element(document.body).injector().get('$http').pendingRequests.length === 0");
            } while (!requestsAreCompleted);
        } catch (Exception ignored) {
            waitABit(1500);
        }
    }

    public static void waitUntilPageIsLoadedWithJs() {
        getWait().until(webDriver ->
                ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public static void waitForNumberOfElementsToBe(By by, int elementsNumber) {
        getWait().until(ExpectedConditions.numberOfElementsToBe(by, elementsNumber));
    }

    public static boolean elementShouldBeVisible(WebElement element, boolean shouldBeVisible) {
        return elementShouldBeVisible(element, shouldBeVisible, 15);
    }

    public static boolean elementShouldBeVisible(By by, boolean shouldBeVisible) {
        return elementShouldBeVisible(DriverBuilder.getInstance().getDriver().findElement(by), shouldBeVisible, 15);
    }

    public static boolean elementShouldBeVisible(By by, boolean shouldBeVisible, int timeOut) {
        return elementShouldBeVisible(DriverBuilder.getInstance().getDriver().findElement(by), shouldBeVisible, timeOut);
    }

    public static boolean elementShouldBeVisible(WebElement element, boolean shouldBeVisible, int timeOut) {
        try {
            return getWebDriverWait(timeOut)
                    .until((webDriver) -> {
                        if (shouldBeVisible)
                            try {
                                return element.isDisplayed();
                            } catch (TimeoutException | NoSuchElementException ex) {
                                return false;
                            }
                        else {
                            try {
                                return !element.isDisplayed();
                            } catch (TimeoutException | NoSuchElementException ex) {
                                return true;
                            }
                        }
                    });
        } catch (Exception ignored) {
            return false;
        }
    }

    public static boolean elementShouldBeClickable(WebElement element, boolean condition, int timeOut) {
        final WebDriverWait wait = getWebDriverWait(timeOut);
        if (condition) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(element));
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            try {
                wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(element)));
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }

    public static boolean elementShouldBeClickable(List<WebElement> elements, boolean condition) {
        return elementShouldBeClickable(elements, condition, 5);
    }

    public static boolean elementShouldBeClickable(List<WebElement> elements, boolean condition, int timeOut) {
        final FluentWait<WebDriver> wait = getFluentWait(Duration.ofMillis(100), Duration.ofMillis(500));
        waitForVisibilityOfAllOptions(elements, timeOut);
        if (condition) {
            try {
                elements.forEach(e -> wait.until(ExpectedConditions.elementToBeClickable(e)));
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            try {
                elements.forEach(e -> wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(e))));
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }

    public static void waitUntilTitleContains(String title) {
        getWait().until(ExpectedConditions.titleContains(title));
    }

    public static void waitUntilTitleContains(String title, int timeOut) {
        getWebDriverWait(timeOut).until(ExpectedConditions.titleContains(title));
    }

    public static void waitUntilTitleContainsIgnoringException(String title, int timeOut) {
        try {
            waitUntilTitleContains(title, timeOut);
        } catch (Exception ignored) {}
    }

    public static void waitUntilTitleIs(String title) {
        getWait().until(ExpectedConditions.titleIs(title));
    }
}