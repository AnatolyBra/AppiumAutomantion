package page;

import core.BasePage;
import io.appium.java_client.AppiumDriver;

public class MenuPage extends BasePage {
    public MenuPage(AppiumDriver driver) {
        super(driver);
    }

    private static final String NAME_OF_THIS_LIST = "id:org.wikipedia:id/text_input",
            PAGE_SAVE = "id:org.wikipedia:id/page_save",
            ADD_TO_LIST = "id:org.wikipedia:id/snackbar_action",
            OK_BUTTON = "xpath://*[contains(@text,'OK')]",
            NAME_FOLDER = "xpath://*[@resource-id='org.wikipedia:id/item_title'][@text='{SUBSTRING}']",
            CURRENT_LIST = "xpath://*[contains(@text,'{SUBSTRING}')]",
            TITLE_OF_ARTICLE_IN_LIST = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{SUBSTRING}']";

    public void deleteToSwipeArticle(String title) {
        this.swipeElementToLeft(
                titleOfArticleBySubstringTmp(title),
                "Cannot find saved article"
        );
    }

    public void assertTitleByList(String expectedTitle) {
        this.assertElementHasText(
                titleOfArticleBySubstringTmp(expectedTitle),
                expectedTitle
        );
    }

    public void titleNotVisible(String title) {
        this.waitForElementNotPresent(
                titleOfArticleBySubstringTmp(title),
                "Title is visible",
                5
        );
    }

    public void clickTitleOfArticleByList(String titleOfArticle) {
        this.waitForElementByAndClick(
                titleOfArticleBySubstringTmp(titleOfArticle),
                "Cannot click by title in current list",
                15
        );
    }

    public void clickCurrentList(String currentList) {
        this.waitForElementByAndClick(
                currentListBySubstringTmp(currentList),
                "Cannot click by current list",
                5
        );
    }

    public void setNameOfThisList(String nameOfThisList) {
        this.waitForElementByAndSendKeys(
                NAME_OF_THIS_LIST,
                nameOfThisList,
                "Cannot click by name of this list",
                5
        );
    }

    public void clickAddToList() {
        this.waitForElementByAndClick(
                ADD_TO_LIST,
                "Cannot click by add to list",
                5
        );
    }

    public void clickPageSave() {
        this.waitForElementByAndClick(
                PAGE_SAVE,
                "Cannot click by page save",
                5
        );
    }

    public void clickOkButton() {
        this.waitForElementByAndClick(
                OK_BUTTON,
                "Cannot click by ok button",
                5
        );
    }

    public void clickFolderInSaved(String nameFolder) {
        this.waitForElementByAndClick(
                nameFolderBySubstringTmp(nameFolder),
                "Cannot click by folder in saved",
                5
        );
    }

    private String nameFolderBySubstringTmp(String substring) {
        return NAME_FOLDER.replace("{SUBSTRING}", substring);
    }

    private String currentListBySubstringTmp(String substring) {
        return CURRENT_LIST.replace("{SUBSTRING}", substring);
    }

    private String titleOfArticleBySubstringTmp(String substring) {
        return TITLE_OF_ARTICLE_IN_LIST.replace("{SUBSTRING}", substring);
    }
}
