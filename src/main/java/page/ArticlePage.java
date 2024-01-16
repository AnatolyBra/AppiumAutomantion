package page;

import core.BasePage;
import io.appium.java_client.AppiumDriver;

public class ArticlePage extends BasePage {
    public ArticlePage(AppiumDriver driver) {
        super(driver);
    }

    private static final String FOOTER = "xpath://*[contains(@content-desc,'View article in browser')]",
            TITLE = "xpath://android.view.View[@content-desc='{SUBSTRING}']";

    public void swipeToFooter() {
        this.swipeUpToFindElement(
                FOOTER,
                "Не удалось доскролить до футера",
                50
        );
    }

    public void titleVisible(String title) {
        this.assertElementPresent(
                titleBySubstringTmp(title),
                title
        );
    }

    private String titleBySubstringTmp(String substring) {
        return TITLE.replace("{SUBSTRING}", substring);
    }
}
