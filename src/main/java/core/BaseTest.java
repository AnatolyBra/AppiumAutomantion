package core;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

abstract public class BaseTest extends TestCase {
    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";

    protected AppiumDriver driver;
    private String appiumURL = "http://127.0.0.1:4723/wd/hub";

    @Override
    public void setUp() throws Exception {
        super.setUp();
        this.getCapabilitiesByPlatformEnv();
        this.rotateScreenPortrait();
    }

    @Override
    public void tearDown() throws Exception {
        driver.quit();
        super.tearDown();
    }

    protected void rotateScreenPortrait() {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    private DesiredCapabilities getCapabilitiesByPlatformEnv() throws Exception {
        String platform = System.getenv("PLATFORM");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        if (platform.equals(PLATFORM_ANDROID)) {
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("deviceName", "AndroidTestName");
            capabilities.setCapability("platformVersion", "8");
            capabilities.setCapability("automationName", "Appium");
            capabilities.setCapability("appPackage", "org.wikipedia");
            capabilities.setCapability("appActivity", ".main.MainActivity");
            capabilities.setCapability("app", "/Users/gudvin/IdeaProjects/AppiumAutomantion/apk/wikipedia-2-7-50463-r-2023-12-04.apk");

            driver = new AndroidDriver(new URL(appiumURL), capabilities);
        } else if (platform.equals(PLATFORM_IOS)) {
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("deviceName", "iPhone 15 Pro");
            capabilities.setCapability("platformVersion", "17.2");
            capabilities.setCapability("automationName", "XCUITest");
            capabilities.setCapability("app", "/Users/gudvin/IdeaProjects/AppiumAutomantion/apk/Wikipedia.app");

            driver = new IOSDriver(new URL(appiumURL), capabilities);
        } else {
            throw new Exception("Cannot get run platform from env variable. Platform value " + platform);
        }
        return capabilities;
    }
}
