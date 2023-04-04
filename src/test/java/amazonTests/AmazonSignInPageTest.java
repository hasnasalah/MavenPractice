package amazonTests;

import Amazon.Pages.HomePage;
import Amazon.Pages.SignInPage;
import Config.common.WebTestBase;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import static Config.common.WebTestBase.driver;

public class AmazonSignInPageTest extends WebTestBase {
    static SignInPage signInPage;
    public static void getInitElement(){
        PageFactory.initElements(driver, SignInPage.class);
        signInPage=new SignInPage(driver);
    }
    @Test
    public static void verifyValidEmailAndValidPass() throws InterruptedException {
        getInitElement();
        signInPage.signInWithValidEmailAndValidPass();
        signInPage.verifySignInUsingValidEmailAndValidPass("Hello, mustapha");
    }
    @Test
    public static void verifyWrongEmailSignIn() throws InterruptedException {
        getInitElement();
        signInPage.signInWithWrongEmail();
        signInPage.verifySignInUsingWrongEmail("There was a problem");
    }
    @Test
    public static void verifyValidEmailAndWrongPass() throws InterruptedException {
        getInitElement();
        signInPage.signInWithValidEmailAndWrongPass();
        signInPage.verifySignInUsingValidEmailAndWrongPass("Your password is incorrect");
    }
    @Test
    public static void verifySignOut(){
        getInitElement();
        signInPage.signOutWithValidEmailAndValidPass();
        signInPage.verifySignOutUsingValidEmailAndValidPass("Sign in");
    }

}
