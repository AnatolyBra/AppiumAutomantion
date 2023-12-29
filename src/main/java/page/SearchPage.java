package page;

import core.BasePage;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPage extends BasePage {
    /**
     *
     * /search
     *
     */
    public SearchPage(AppiumDriver driver) {
        BasePage.driver = driver;
    }
    public SearchPage(){

    }
    private final By searchInput =  By.xpath("//*[contains(@text,'Search Wikipedia')]");
    private final By elementBySearchJava = By.xpath("//*[contains(@text,'Object-oriented programming language')]");
    private final By searchInputId =  By.id("org.wikipedia:id/search_container");
    private final By searchResultsTitleId =  By.id("org.wikipedia:id/page_list_item_title");
    private final By backButton =  By.xpath("//*[contains(@content-desc,'Navigate up')]");
    private final By savedPagesButton = By.xpath("//*[contains(@content-desc,'Saved')]");

    public By getSearchInput() {
        return searchInput;
    }
    public By getSearchInputId() {
        return searchInputId;
    }

    public By getElementBySearchJava() {
        return elementBySearchJava;
    }

    public By getBackButton() {
        return backButton;
    }

    public By getSearchResultsTitleId() {
        return searchResultsTitleId;
    }

    public By getSavedPagesButton() {
        return savedPagesButton;
    }
}
