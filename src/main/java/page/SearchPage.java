package page;

import core.BasePage;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class SearchPage extends BasePage {
    /**
     * /search
     */
    public SearchPage(AppiumDriver driver) {
        super(driver);
    }


    private static final String SEARCH_INPUT = "//*[contains(@text,'Search Wikipedia')]",
            ELEMENT_BY_SEARCH_JAVA = "//*[contains(@text,'{SUBSTRING}')]",
            SEARCH_INPUT_ID = "org.wikipedia:id/search_container",
            SEARCH_RESULTS_TITLE_ID = "org.wikipedia:id/page_list_item_title",
            BACK_BUTTON = "//*[contains(@content-desc,'Navigate up')]",
            SAVE_PAGES_BUTTON = "//*[contains(@text,'Save')]",
//            SAVED_PAGES_BUTTON = "//*[contains(@text,'Saved')]",
            SEARCH_TITLE = "//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{SUBSTRING}']";

    public void clickSearchBySubstring(String substring) {
        this.waitForElementByAndClick(
                By.xpath(searchClickBySubstringTmp(substring)),
                "Click by article",
                5
        );
    }

    public void backButtonNotVisible() {
        this.waitForElementNotPresent(
                By.xpath(BACK_BUTTON),
                "button 'back' not present in system",
                5
        );
    }

    public void clickBackButton() {
        this.waitForElementByAndClick(
                By.xpath(BACK_BUTTON),
                "Cannot click button 'back'",
                5
        );
    }

//    public void clickSavedButton() {
//        this.waitForElementByAndClick(
//                By.xpath(SAVED_PAGES_BUTTON),
//                "Cannot click button 'saved'",
//                5
//        );
//    }

    public void clickSaveButton() {
        this.waitForElementByAndClick(
                By.xpath(SAVE_PAGES_BUTTON),
                "Cannot click button 'save'",
                5
        );
    }

    public void clickSearchInput() {
        this.waitForElementByAndClick(
                By.xpath(SEARCH_INPUT),
                "Cannot click search field",
                5
        );
    }

    public void setTextSearchInput(String value) {
        this.waitForElementByAndSendKeys(
                By.xpath(SEARCH_INPUT),
                value,
                "Cannot input '" + value + "'",
                5
        );
    }

    public void assertInputHasText(String expected) {
        WebElement element = this.waitForElementPresentBy(
                By.xpath(SEARCH_INPUT),
                "Text not found in this locator",
                15
        );
        String actual = element.getAttribute("text");
        assertEquals("Text not found in this locator", expected, actual);
    }

    public void assertTitleHasText(String expected) {
        WebElement element = this.waitForElementPresentBy(
                By.id(SEARCH_RESULTS_TITLE_ID),
                "Text not found in this locator",
                15
        );
        String actual = element.getAttribute("text");
        assertEquals("Text not found in this locator", expected, actual);
    }

    public void assertListHasText(String expected) {
        WebElement element = this.waitForElementPresentBy(
                By.xpath(searchClickBySubstringTmp(expected)),
                "Text not found in this locator",
                15
        );
        String actual = element.getAttribute("text");
        assertEquals("Text not found in this locator", expected, actual);
    }

    public void assertListHasTitleText(String expected) {
        List<WebElement> listOfSearch = driver.findElements(By.id(SEARCH_RESULTS_TITLE_ID));

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
                By.xpath(titleBySubstringTmp(title)),
                "Click by article",
                5
        );
    }

    private String titleBySubstringTmp(String substring) {
        return SEARCH_TITLE.replace("{SUBSTRING}", substring);
    }
}
