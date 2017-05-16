package com.cyberiansoft.test.vnext.builder;

import java.io.File;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.cyberiansoft.test.core.IOSRegularDeviceInfo;
import com.cyberiansoft.test.vnext.screens.SwipeableWebDriver;

import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

public abstract class VNextAppiumDriverBuilder<SELF, DRIVER extends SwipeableWebDriver> {

	private static String PLATFORM_NAME;
	
	public static String getPlatformName() {
        return PLATFORM_NAME;
    }
	
    public static AndroidDriverBuilder forAndroid() {
        return new AndroidDriverBuilder();
    }

    public static class AndroidDriverBuilder extends VNextAppiumDriverBuilder<AndroidDriverBuilder, SwipeableWebDriver> {

        DesiredCapabilities appiumcap = new DesiredCapabilities();

        public SwipeableWebDriver build() {
        	PLATFORM_NAME = MobilePlatform.ANDROID;
        	File appDir = new File("./data/");
    	    File app = new File(appDir, "ReconPro.apk");
    	    appiumcap = new DesiredCapabilities();

    		appiumcap.setCapability(MobileCapabilityType.DEVICE_NAME, "mydroid19"); 
    		appiumcap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "1500");
    		appiumcap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
    		appiumcap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
    		appiumcap.setCapability(MobileCapabilityType.FULL_RESET, false);
    		appiumcap.setCapability(MobileCapabilityType.NO_RESET, true);
    		appiumcap.setCapability(AndroidMobileCapabilityType.RECREATE_CHROME_DRIVER_SESSIONS, true);
    		
            return new SwipeableWebDriver(endpoint, appiumcap);

        }

    }
    
    public static IOSDriverBuilder forIOS() {
        return new IOSDriverBuilder();
    }
    
    public static class IOSDriverBuilder extends VNextAppiumDriverBuilder<IOSDriverBuilder, SwipeableWebDriver> {

        DesiredCapabilities appiumcap = new DesiredCapabilities();

        public SwipeableWebDriver build() {
        	PLATFORM_NAME = MobilePlatform.IOS;
        	File appDir = new File("/Users/kolin/Documents");
    	    File app = new File(appDir, "Repair360_0328.app.zip");
    		appiumcap.setCapability(MobileCapabilityType.BROWSER_NAME, "");
    		appiumcap.setCapability(MobileCapabilityType.PLATFORM_VERSION, IOSRegularDeviceInfo.getInstance().getPlatformVersion());
    		appiumcap.setCapability(MobileCapabilityType.FULL_RESET, false);
    		appiumcap.setCapability(MobileCapabilityType.NO_RESET, false);
    		appiumcap.setCapability("nativeWebTap", false);
    		appiumcap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest"); 
    		appiumcap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS); 
    		appiumcap.setCapability("appiumVersion", "1.6.3");
    	
    		appiumcap.setCapability("waitForAppScript", "$.delay(5000); $.acceptAlert();");
    		appiumcap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, IOSRegularDeviceInfo.getInstance().getNewCommandTimeout());

    		appiumcap.setCapability("bundleId", "com.automobiletechnologies.repair360");
    		appiumcap.setCapability(MobileCapabilityType.UDID, IOSRegularDeviceInfo.getInstance().getDeviceUDID());
    		appiumcap.setCapability(MobileCapabilityType.DEVICE_NAME, IOSRegularDeviceInfo.getInstance().getDeviceName());
    		
    		appiumcap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());

            return new SwipeableWebDriver(endpoint, appiumcap);

        }

    }

    protected URL endpoint;

    public SELF withEndpoint(URL endpoint) {
        this.endpoint = endpoint;

        return (SELF) this;
    }

    public abstract DRIVER build();

}