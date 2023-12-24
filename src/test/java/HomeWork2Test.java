import core.BaseTest;
import org.junit.Test;
import page.IntroPage;
import page.SearchPage;

public class HomeWork2Test extends BaseTest {
    private final IntroPage introPage = new IntroPage();
    private final SearchPage searchPage = new SearchPage();

    @Test
    public void firstTest() {

        waitForElementByAndClick(
                introPage.getSkipButton(),
                "Cannot find button 'Skip'",
                5
        );
        waitForElementByAndClick(
                searchPage.getSearchInput(),
                "Cannot find search input'",
                5
        );
        assertElementHasText(
                searchPage.getSearchInput(),
                "Search Wikipedia"
        );
        waitForElementByAndSendKeys(
                searchPage.getSearchInput(),
                "Java",
                "Cannot input 'Java'",
                5
        );
        assertElementHasText(
                searchPage.getElementBySearchJava(),
                "Object-oriented programming language"
        );
        waitForElementPresentBy(
                searchPage.getElementBySearchJava(),
                "Cannot find 'Object-oriented programming language' topic searching by 'JAVA'",
                15
        );
    }

    //Ex3
    @Test
    public void cancelSearch() {
        String searchText = "Kiss";

        waitForElementByAndClick(
                introPage.getSkipButton(),
                "Cannot find button 'Skip'",
                5
        );
        waitForElementByAndClick(
                searchPage.getSearchInput(),
                "Cannot find search input'",
                5
        );
        assertElementHasText(
                searchPage.getSearchInput(),
                "Search Wikipedia"
        );
        waitForElementByAndSendKeys(
                searchPage.getSearchInput(),
                searchText,
                "Cannot input '" + searchText + "'",
                5
        );
        assertElementContainsText(
                searchPage.getSearchResultsTitleId(),
                searchText
        );
        waitForElementByAndClick(
                searchPage.getBackButton(),
                "Cannot click button 'back'",
                5
        );
        waitForElementNotPresent(
                searchPage.getBackButton(),
                "button 'back' not present in system",
                5
        );
    }

    //Ex4
    @Test
    public void checkWordInSearch() {
        String searchText = "Kiss";

        waitForElementByAndClick(
                introPage.getSkipButton(),
                "Cannot find button 'Skip'",
                5
        );
        waitForElementByAndClick(
                searchPage.getSearchInput(),
                "Cannot find search input'",
                5
        );
        assertElementHasText(
                searchPage.getSearchInput(),
                "Search Wikipedia"
        );
        waitForElementByAndSendKeys(
                searchPage.getSearchInput(),
                searchText,
                "Cannot input '" + searchText + "'",
                5
        );
        assertElementContainsTextSearchList(searchText);
    }
}
