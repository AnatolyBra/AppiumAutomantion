package core;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Map;
import java.util.regex.Pattern;


public class BasePage extends BaseTest {
    protected AppiumDriver driver;

    public BasePage(AppiumDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForElementPresentBy(String locator, String errorMessage, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementPresentBy(String locator, String errorMessage) {
        return waitForElementPresentBy(locator, errorMessage, 5);
    }

    public WebElement waitForElementByAndClick(String locator, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresentBy(locator, errorMessage, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementByAndSendKeys(String locator, String value, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresentBy(locator, errorMessage, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(String locator, String errorMessage, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public void assertElementHasText(String locator, String expected) {
        WebElement element = waitForElementPresentBy(
                locator,
                "Text not found in this locator",
                15
        );
        String actual = element.getAttribute("text");
        Assert.assertEquals("Text not found in this locator", expected, actual);
    }

    public void assertElementPresent(String locator, String expected) {
        WebElement element = waitForElementPresentBy(
                locator,
                "Text not found in this locator for title",
                15
        );
        String actual = element.getAttribute("contentDescription");
        assertEquals("Text not found in this locator", expected, actual);
    }

    protected void swipeUpToFindElement(String locator, String errorMessage, int maxSwipes) {
        By by = this.getLocatorByString(locator);
        Dimension dimension = driver.manage().window().getSize();
        Point start = new Point((int) (dimension.width * 0.5), (int) (dimension.height * 0.85));
        Point end = new Point((int) (dimension.width * 0.5), (int) (dimension.height * 0.1));


        int alreadySwiped = 0;
        while (driver.findElements(by).isEmpty()) {
            W3cActions.doSwipe(driver, start, end, 1000);  //with duration 1s
            if (alreadySwiped > maxSwipes) {
                waitForElementPresentBy(locator, "Cannot find element by swiping up. \n" + errorMessage, 0);
                return;
            }
            ++alreadySwiped;
        }
    }

    protected void swipeElementToLeft(String locator, String errorMessage) {
        RemoteWebElement carousel = (RemoteWebElement) waitForElementPresentBy(
                locator,
                errorMessage,
                10);

        driver.executeScript("mobile: swipeGesture", Map.of("elementId", carousel.getId(), "percent", 0.5f, "direction", "left"));
    }

    private By getLocatorByString(String locatorWithType) {
        String[] explodedLocator = locatorWithType.split(Pattern.quote(":"), 2);
        String byType = explodedLocator[0];
        String locator = explodedLocator[1];
        if (byType.equals("xpath")) {
            return By.xpath(locator);
        } else if (byType.equals("id")) {
            return By.id(locator);
        } else {
            throw new IllegalArgumentException("Cannot get type of locator. Locator: " + locatorWithType);
        }
    }
}
