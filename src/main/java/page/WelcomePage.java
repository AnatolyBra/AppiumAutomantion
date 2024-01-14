package page;

import core.BasePage;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class WelcomePage extends BasePage {
    public WelcomePage(AppiumDriver driver) {
        super(driver);
    }
    private static final String SKIP_BUTTON = "//*[contains(@name,'Пропустить')]";
    public void waitFreeEncyclopedia(){
        this.waitForElementPresentBy(
                By.id("Свободная энциклопедия"),
                "Cannot find 'Свободная энциклопедия'",
                10
        );
    }
    public void waitSearchWikipedia(){
        this.waitForElementPresentBy(
                By.id("Поиск по Википедии"),
                "Cannot find 'Поиск по Википедии'",
                10
        );
    }

    public void clickSkipButton(){
        this.waitForElementByAndClick(
                By.xpath(SKIP_BUTTON),
                "Cannot find 'Свободная энциклопедия'",
                10
        );
    }
}
