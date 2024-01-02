package page;

import core.BasePage;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MenuPage extends BasePage {
    public MenuPage(AppiumDriver driver) {
        BasePage.driver = driver;
    }
    public MenuPage(){

    }

    private final By nameOfThisList=  By.id("org.wikipedia:id/text_input");
    private final By pageSave=  By.id("org.wikipedia:id/page_save");
    private final By addToList=  By.id("org.wikipedia:id/snackbar_action");
    private final By okButton=  By.xpath("//*[contains(@text,'OK')]");

    public By getNameOfThisList() {
        return nameOfThisList;
    }

    public By getPageSave() {
        return pageSave;
    }

    public By getAddToList() {
        return addToList;
    }

    public By getOkButton() {
        return okButton;
    }
}
