package page;

import core.BasePage;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.regex.Pattern;

public class SearchPage extends BasePage {
    /**
     * /search
     */
    public SearchPage(AppiumDriver driver) {
        super(driver);
    }


    private static final String SEARCH_INPUT = "xpath://*[contains(@text,'Search Wikipedia')]",
            ELEMENT_BY_SEARCH_JAVA = "xpath://*[contains(@text,'{SUBSTRING}')]",
            SEARCH_INPUT_ID = "id:org.wikipedia:id/search_container",
            SEARCH_RESULTS_TITLE_ID = "id:org.wikipedia:id/page_list_item_title",
            BACK_BUTTON = "xpath://*[contains(@content-desc,'Navigate up')]",
            SAVE_PAGES_BUTTON = "xpath://*[contains(@text,'Save')]",
            SEARCH_TITLE = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{SUBSTRING}']",
            SEARCH_TITLE_AND_DESCRIPTION = "xpath://*[contains(@resource-id,'org.wikipedia:id/page_list_item')][@text='{SUBSTRING}']";
    public void waitForElementByTitleAndDescription(String title, String description) {
        this.waitForElementPresentBy(
                titleAndDescriptionBySubstringTmp(title),
                "Title is not visible",
                5
        );
        this.waitForElementPresentBy(
                titleAndDescriptionBySubstringTmp(description),
                "Description is not visible",
                5
        );
    }

    public void clickSearchBySubstring(String substring) {
        this.waitForElementByAndClick(
                searchClickBySubstringTmp(substring),
                "Click by article",
                5
        );
    }

    public void backButtonNotVisible() {
        this.waitForElementNotPresent(
                BACK_BUTTON,
                "button 'back' not present in system",
                5
        );
    }

    public void clickBackButton() {
        this.waitForElementByAndClick(
                BACK_BUTTON,
                "Cannot click button 'back'",
                5
        );
    }


    public void clickSaveButton() {
        this.waitForElementByAndClick(
                SAVE_PAGES_BUTTON,
                "Cannot click button 'save'",
                5
        );
    }

    public void clickSearchInput() {
        this.waitForElementByAndClick(
                SEARCH_INPUT,
                "Cannot click search field",
                5
        );
    }

    public void setTextSearchInput(String value) {
        this.waitForElementByAndSendKeys(
                SEARCH_INPUT,
                value,
                "Cannot input '" + value + "'",
                5
        );
    }

    public void assertInputHasText(String expected) {
        WebElement element = this.waitForElementPresentBy(
                SEARCH_INPUT,
                "Text not found in this locator",
                15
        );
        String actual = element.getAttribute("text");
        assertEquals("Text not found in this locator", expected, actual);
    }

    public void assertTitleHasText(String expected) {
        WebElement element = this.waitForElementPresentBy(
                SEARCH_RESULTS_TITLE_ID,
                "Text not found in this locator",
                15
        );
        String actual = element.getAttribute("text");
        assertEquals("Text not found in this locator", expected, actual);
    }

    public void assertListHasText(String expected) {
        WebElement element = this.waitForElementPresentBy(
                searchClickBySubstringTmp(expected),
                "Text not found in this locator",
                15
        );
        String actual = element.getAttribute("text");
        assertEquals("Text not found in this locator", expected, actual);
    }

    public void assertListHasTitleText(String expected) {
        String[] explodedLocator = SEARCH_RESULTS_TITLE_ID.split(Pattern.quote(":"), 2);
        String locator = explodedLocator[1];

        List<WebElement> listOfSearch = driver.findElements(By.id(locator));

        for (WebElement element : listOfSearch) {
            String actualText = element.getAttribute("text");
            boolean actual = actualText.contains(expected);
            assertTrue("Text not contains " + actualText, actual);
        }
    }

    private String searchClickBySubstringTmp(String substring) {
        return ELEMENT_BY_SEARCH_JAVA.replace("{SUBSTRING}", substring);
    }

    public void clickByTitleInSearch(String title) {
        this.waitForElementByAndClick(
                titleBySubstringTmp(title),
                "Click by article",
                5
        );
    }

    private String titleBySubstringTmp(String substring) {
        return SEARCH_TITLE.replace("{SUBSTRING}", substring);
    }
    private String titleAndDescriptionBySubstringTmp(String substring) {
        return SEARCH_TITLE_AND_DESCRIPTION.replace("{SUBSTRING}", substring);
    }
}
