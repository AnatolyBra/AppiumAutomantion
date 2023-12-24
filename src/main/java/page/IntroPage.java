package page;

import core.BasePage;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class IntroPage extends BasePage {
    /**
     *
     * /intro
     *
     */
    public IntroPage(AppiumDriver driver) {
        BasePage.driver = driver;
    }
    public IntroPage(){

    }
    private final By skipButton = By.xpath("//*[contains(@text,'Skip')]");

    public By getSkipButton() {
        return skipButton;
    }
}
