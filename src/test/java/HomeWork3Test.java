import core.BaseTest;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import page.IntroPage;
import page.MenuPage;
import page.SearchPage;

import java.util.List;

public class HomeWork3Test extends BaseTest {
    private final IntroPage introPage = new IntroPage();
    private final SearchPage searchPage = new SearchPage();
    private final MenuPage menuPage = new MenuPage();

//    @Test
    public void testSwitchArticle() {
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
                "Appium",
                "Cannot input 'Appium'",
                5
        );
        waitForElementByAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Click by article",
                5
        );
        swipeUpToFindElement(
                By.xpath("//*[contains(@content-desc,'View article in browser')]"),
                "Не проскролил до футтера",
                20
        );
    }


    @Test
    public void articleSaveToMyList() {
        driver.rotate(ScreenOrientation.PORTRAIT);

        List<String> titles = List.of("Java", "Appium");
        String titleName = "My favorite articles";
        String listPageName = String.format("//*[@resource-id='org.wikipedia:id/item_title'][@text='%s']", titleName);
        waitForElementByAndClick(
                introPage.getSkipButton(),
                "Cannot find button 'Skip'",
                5
        );

        addTitleToList(titles, titleName);

        waitForElementByAndClick(
                searchPage.getSavedPagesButton(),
                "Cannot click button 'back'",
                5
        );

        waitForElementByAndClick(
                By.xpath(listPageName),
                "Cannot find created folder '" + titleName + "'",
                5
        );

        assertElementHasText(
                pageTitle(titles.get(0)),
                titles.get(0)
        );

        assertElementHasText(
                pageTitle(titles.get(1)),
                titles.get(1)
        );

        swipeElementToLeft(
                pageTitle(titles.get(0)),
                "Cannot find saved article"
        );

        waitForElementNotPresent(
                pageTitle(titles.get(0)),
                "Cannot delete saved article",
                5
        );

        assertElementHasText(
                pageTitle(titles.get(1)),
                titles.get(1)
        );

        waitForElementByAndClick(
                pageTitle(titles.get(1)),
                "Cannot click by saved article",
                5
        );

        waitForElementPresentBy(
                By.xpath("//*[contains(@content-desc,'" + titles.get(1) + "')]"),
                "Заголовок статьи appium не найден",
                5
        );


    }

    @Test
    public void checkTitle() {


        String searchText = "Appium";
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

        waitForElementByAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='" + searchText + "']"),
                "Click by article '" + searchText + "'",
                5
        );

        assertElementPresent(
                By.xpath("//android.webkit.WebView[@content-desc='" + searchText + "']"),
                searchText
        );

        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    private By pageTitle(String title) {
        return By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='" + title + "']");
    }

    private void addTitleToList(List<String> titles, String titleName) {
        boolean listExist = false;
        for (String title : titles) {
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
                    title,
                    "Cannot input 'Java'",
                    5
            );
            waitForElementByAndClick(
                    By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='" + title + "']"),
                    "Click by article '" + title + "'",
                    5
            );

            waitForElementByAndClick(
                    menuPage.getPageSave(),
                    "Cannot find button 'Save'",
                    5
            );

            if (!listExist) {

                waitForElementByAndClick(
                        menuPage.getAddToList(),
                        "Cannot find button 'Add to List'",
                        5
                );
                waitForElementByAndSendKeys(
                        menuPage.getNameOfThisList(),
                        titleName,
                        "Cannot create 'Name of this list'",
                        5
                );

                waitForElementByAndClick(
                        menuPage.getOkButton(),
                        "Cannot find button 'Ok'",
                        5
                );
            } else {
                waitForElementByAndClick(
                        menuPage.getAddToList(),
                        "Cannot find button 'Add to List'",
                        5
                );
                waitForElementByAndClick(
                        By.xpath("//*[contains(@text,'" + titleName + "')]"),
                        "Cannot find button current list",
                        5
                );
            }
//ушли из статьи
            waitForElementByAndClick(
                    searchPage.getBackButton(),
                    "Cannot click button 'back'",
                    5
            );
//ушли из поиска
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

            listExist = true;
        }
    }
}
