package amazonTests;

import Amazon.Pages.FooterInHomePage;
import Amazon.Pages.HomePage;
import Config.common.WebTestBase;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import static Amazon.Pages.HomePage.tryHoverOverMouse;

public class AmazonHomePageTest2 extends WebTestBase {
   static HomePage homePage;
    public static void getInitElement(){
        PageFactory.initElements(driver, HomePage.class);
         homePage=new HomePage(driver);
    }
    @Test@Ignore()
    public void verifySearchProductUsingValidProductName(){
        getInitElement();
       // HomePage homePage=new HomePage(driver);
        homePage.searchProductUsingValidProductName();
        homePage.verifyProductUsingValidProductName("\"Hand Sanitizer\"");
    }
    @Test@Ignore()
    public void verifyScrollSearchProductUsingValidProductName() {
        getInitElement();
        // HomePage homePage=new HomePage(driver);
        homePage.scrollDropBox();
        homePage.verifyProductUsingValidProductNameScrollSearch("\"hand sewing machine\"");
    }
    @Test@Ignore()
    public void verifyShoppingCart() throws InterruptedException {
        getInitElement();
        homePage.addProductToCart();
        homePage.setVerifyIfProductIsInCarts("1");
    }
    @Test
    public void verifyMouseHover() throws InterruptedException {
        getInitElement();
        homePage.tryHoverOverMouse("New members, try Prime\nfree for 30 days");
    }

}
