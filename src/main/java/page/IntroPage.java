package page;

import core.BasePage;
import io.appium.java_client.AppiumDriver;

public class IntroPage extends BasePage {
    /**
     * /intro
     */
    public IntroPage(AppiumDriver driver) {
        super(driver);
    }

    private static final String SKIP_BUTTON = "xpath://*[contains(@text,'Skip')]";

    public void clickSkipButton() {
        this.waitForElementPresentBy(
                SKIP_BUTTON,
                "Cannot find button 'Skip'",
                5
        );
        this.waitForElementByAndClick(
                SKIP_BUTTON,
                "Cannot find button 'Skip'",
                5
        );
    }
}
