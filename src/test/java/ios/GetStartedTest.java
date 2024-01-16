package ios;

import core.BaseTest;
import org.junit.Test;
import page.WelcomePage;

public class GetStartedTest extends BaseTest {

    @Test
    public void testPassThroughWelcome(){
        WelcomePage welcomePage = new WelcomePage(driver);

        welcomePage.waitFreeEncyclopedia();
        welcomePage.clickSkipButton();
        welcomePage.waitSearchWikipedia();
    }
}
