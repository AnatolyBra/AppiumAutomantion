package page;

import core.BasePage;
import io.appium.java_client.AppiumDriver;

public class WelcomePage extends BasePage {
    public WelcomePage(AppiumDriver driver) {
        super(driver);
    }

    private static final String SKIP_BUTTON = "xpath://*[contains(@name,'Пропустить')]",
            FREE_ENCYCLOPEDIA = "id:Свободная энциклопедия",
            SEARCH_WIKIPEDIA = "id:Поиск по Википедии";

    public void waitFreeEncyclopedia() {
        this.waitForElementPresentBy(
                FREE_ENCYCLOPEDIA,
                "Cannot find 'Свободная энциклопедия'",
                10
        );
    }

    public void waitSearchWikipedia() {
        this.waitForElementPresentBy(
                SEARCH_WIKIPEDIA,
                "Cannot find 'Поиск по Википедии'",
                10
        );
    }

    public void clickSkipButton() {
        this.waitForElementByAndClick(
                SKIP_BUTTON,
                "Cannot find 'Свободная энциклопедия'",
                10
        );
    }
}
