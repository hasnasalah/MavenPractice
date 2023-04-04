package amazonTests;

import Amazon.Pages.FooterInHomePage;
import Amazon.Pages.HomePage;
import Config.common.WebTestBase;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class AmazonFooterHomePageTest extends WebTestBase {
    static FooterInHomePage footerInHomePage;
    public static void getInitElement(){
        PageFactory.initElements(driver, HomePage.class);
        footerInHomePage=new FooterInHomePage(driver);
    }
    @Test @Ignore
    public void verifyFooterElements1(){
        getInitElement();
        FooterInHomePage.testFooterElements();

    }
    @Test
    public void verifyFooterElements2() throws InterruptedException {
        getInitElement();
        FooterInHomePage.testcase2findElementsVerifyAndClick();
        FooterInHomePage.verifyCase2findElementsVerifyAndClick("Amazon Ads");

    }
}
