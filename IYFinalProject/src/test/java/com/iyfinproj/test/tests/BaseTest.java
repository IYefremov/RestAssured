package com.iyfinproj.test.tests;

import com.iyfinproj.test.Driver;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import static com.iyfinproj.test.Driver.getDriver;

public class BaseTest {
    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) throws InterruptedException {
        if (!result.isSuccess()) {
            screenCapture();
        }
        Driver.quit();
    }

    @Attachment(type = "image/png")
    private byte[] screenCapture() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
