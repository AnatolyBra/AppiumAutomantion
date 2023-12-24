import core.BaseTest;
import org.junit.Test;
import page.IntroPage;
import page.SearchPage;

public class FirstTest extends BaseTest {
    private final IntroPage introPage = new IntroPage();
    private final SearchPage searchPage = new SearchPage();


    @Test
    public void secondTest() {
        waitForElementByAndClick(
                introPage.getSkipButton(),
                "Cannot find button 'Skip'",
                5
        );

        waitForElementByAndClick(
                searchPage.getSearchInputId(),
                "Cannot click search field",
                5
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

    //    @Test
    public void testCompareArticleTitle() {
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
        waitForElementByAndSendKeys(
                searchPage.getSearchInput(),
                "Java",
                "Cannot input 'Java'",
                5
        );
        waitForElementByAndClick(
                searchPage.getElementBySearchJava(),
                "Click by article",
                5
        );
    }
}
