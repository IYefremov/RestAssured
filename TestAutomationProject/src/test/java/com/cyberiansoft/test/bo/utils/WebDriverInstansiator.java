package com.cyberiansoft.test.bo.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverInstansiator {

	private static InheritableThreadLocal<WebDriver> webDriver = new InheritableThreadLocal<WebDriver>();
	 WebDriverWait wait = new WebDriverWait(WebDriverInstansiator.getDriver() , 30);

    private static WebDriverFactory factory;

    public static void setDriver(String browserName){
        factory = new WebDriverFactory();
        webDriver.set(factory.getDriver(browserName));
    }

    public static WebDriver getDriver(){
        return webDriver.get();
    }

    public static WebDriverWait getWait(){
    WebDriverWait wait = new WebDriverWait(WebDriverInstansiator.getDriver() , 50);
    return wait;
    }
    
    public static WebDriverWait getUpdateWait(){
        WebDriverWait wait = new WebDriverWait(WebDriverInstansiator.getDriver() , 30 , 1);
        return wait;
        }
}
