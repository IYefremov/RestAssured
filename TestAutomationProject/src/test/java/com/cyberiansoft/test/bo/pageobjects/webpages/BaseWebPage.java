package com.cyberiansoft.test.bo.pageobjects.webpages;

import com.google.common.base.Function;
import org.awaitility.core.ConditionTimeoutException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

public abstract class BaseWebPage {

	public WebDriver driver;
	public static WebDriverWait wait;
    public static WebDriverWait waitLong;
    public static WebDriverWait waitShort;

    @FindBy(className = "updateProcess")
    private WebElement updateProcess;

	private static final long SLEEP_TIMEOUT_IN_SEC = 15;

	public BaseWebPage(WebDriver driver) {
		this.driver = driver;
		driver.manage().timeouts().implicitlyWait(SLEEP_TIMEOUT_IN_SEC, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(SLEEP_TIMEOUT_IN_SEC*4, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(SLEEP_TIMEOUT_IN_SEC*2, TimeUnit.SECONDS);

		wait = new WebDriverWait(driver, 15, 1);
        waitShort = new WebDriverWait(driver, 5, 1);
        waitLong = new WebDriverWait(driver, 30, 1);
	}

	protected WebElement waitUntilElementIsClickable(final WebElement parent, final By locator) {
		final WebElement childElement = wait.until(new Function<WebDriver, WebElement>() {
			@Override
			public WebElement apply(WebDriver input) {
				return parent.findElement(locator);
			}
		});

		moveToElement(childElement);

		wait.until(new Function<WebDriver, Boolean>() {
			@Override
			public Boolean apply(WebDriver input) {
				return childElement.isEnabled();
			}
		});

		return childElement;
	}

	protected WebElement waitUntilElementIsClickable(final WebElement element) {
		final WebElement childElement = wait.until(new Function<WebDriver, WebElement>() {
			@Override
			public WebElement apply(WebDriver input) {
				return element;
			}
		});
		moveToElement(element);
		wait.until(new Function<WebDriver, Boolean>() {
			@Override
			public Boolean apply(WebDriver input) {
				return childElement.isEnabled();
			}
		});

		return childElement;
	}

    WebElement waitForElementNotToMove(final WebElement element) {
        wait.until((ExpectedCondition<Boolean>) wDriver -> {
            Point loc = element.getLocation();
            waitABit(10000);
            return element.getLocation().equals(loc);
        });
        return element;
    }

	public String getBrowserType() {
		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		return cap.getBrowserName().toLowerCase();
	}

	public void closeNewTab(String mainWindowHandle) {
		driver.close();
		driver.switchTo().window(mainWindowHandle);
	}

	private void moveToElement(WebElement webElement) {
		Actions actions = new Actions(driver);
		actions.moveToElement(webElement);
		actions.perform();
	}

	public void waitForNewTab() {
	    try {
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        } catch (TimeoutException e) {
	        waitABit(5000);
        }
		waitABit(5000);
		wait.until((ExpectedCondition<Boolean>) d -> (d.getWindowHandles().size() > 1));
	}

    // Bot actions

	/* Wait For */
	public void waitABit(int milliseconds) {
		if (milliseconds > 0) {
			try {
				TimeUnit.MILLISECONDS.sleep(milliseconds);
			} catch (InterruptedException ex) {
				// Swallow exception
				ex.printStackTrace();
			}
		}
	}

	public void clickDeleteTableRow(WebElement row) {
		row.findElement(By.xpath(".//td/input[@title='Delete']")).click();
	    waitABit(1000);
	}

	public void clickArchiveTableRow(WebElement row) {
		row.findElement(By.xpath(".//*[@title='Archive']")).click();
		waitABit(1000);
	}

	public void clickRestoreTableRow(WebElement row) {
		row.findElement(By.xpath(".//*[@title='Restore']")).click();
		waitABit(1000);
	}

	public void clickApproveMarkerTableRow(WebElement row) {
		row.findElement(By.xpath(".//img[@alt='New' and @title='New']")).click();
		waitABit(1000);
	}

	public void acceptAlertAndWait() {
		acceptAlert();
		waitForLoading();
	}

	public void acceptAlert() {
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	public void dismissAlertAndWait() {
		wait.until(ExpectedConditions.alertIsPresent()).dismiss();
		waitForLoading();
	}

//	public void waitUntilPageReloaded() {
//		waitABit(1500);
//		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(), 'Loading...')]")));
//	}

	public void deleteTableRow(WebElement row) {
		clickDeleteTableRow(row);
		try {
		    acceptAlertAndWait();
		} catch (NoAlertPresentException ignored) {}
	}

	public void archiveTableRow(WebElement row) {
		clickArchiveTableRow(row);
		acceptAlertAndWait();
	}

	public void restoreTableRow(WebElement row) {
		clickRestoreTableRow(row);
		acceptAlertAndWait();
	}

	public void cancelDeletingTableRow(WebElement row) {
		clickDeleteTableRow(row);
		dismissAlertAndWait();
	}

	public void clickEditTableRow(WebElement row) {
	    wait.ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.elementToBeClickable(row.findElement(By.xpath(".//*[@title='Edit']"))))
                    .click();
        waitForLoading();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ModalDialog")));
	}

	public void checkboxSelect(String checkboxvalue) {
		waitABit(3000);
		wait.until(ExpectedConditions.elementToBeClickable(
                driver.findElement(By.xpath("//label[text()='" + checkboxvalue + "']")))).click();
	}

	public void checkboxSelect(WebElement checkbox) {
		if (!isCheckboxChecked(checkbox))
			checkbox.click();
	}

	public void checkboxUnselect(WebElement checkbox) {
		if (isCheckboxChecked(checkbox))
			checkbox.click();
	}

	public boolean isCheckboxChecked(WebElement element) {
		boolean result = false;
		WebElement parent = element.findElement(By.xpath("parent::*"));

		String selected = parent.findElement(By.xpath(".//input")).getAttribute("checked");
		if (selected != null) {
			if (selected.equals("true"))
				result = true;
		}
		return result;
	}

	public void labeledCheckBoxSelect(WebElement checkbox) {
        if (!isCheckboxChecked(checkbox)) {
            WebElement parent = checkbox.findElement(By.xpath("parent::*"));
            parent.findElement(By.xpath("./label")).click();
        }
    }

    public void waitForLoading(){
        waitForLoadingToBegin();
        try {
            await().atMost(80, TimeUnit.SECONDS)
                    .ignoreExceptions()
                    .pollInterval(500, TimeUnit.MILLISECONDS)
                    .until(this::waitForLoadingToStop);
        } catch (ConditionTimeoutException ignored) {}
    }

    private void waitForLoadingToBegin() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Loading...')]")));
        } catch (Exception ignored) {}
    }

    private Boolean waitForLoadingToStop() {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(), 'Loading...')]")));
    }

    public void waitForUpdate() {
        try {
            wait.until(ExpectedConditions.visibilityOf(updateProcess));
            wait.until(ExpectedConditions.invisibilityOf(updateProcess));
        } catch (TimeoutException ignored) {}
    }

    public void setAttribute(WebElement element, String attribute, String value) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);",
                element, attribute, value);
        try {
            waitShort.until(ExpectedConditions.attributeContains(element, attribute, null));
        } catch (Exception ignored) {}
    }

    BaseWebPage clickWithJS(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        return this;
    }
}