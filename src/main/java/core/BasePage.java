package core;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;

abstract public class BasePage {
    protected static AppiumDriver driver;

    public static void setDriver(AppiumDriver webDriver) {
        driver = webDriver;
    }
}
