package Amazon.Pages;

import Config.common.WebTestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import static Amazon.WebElements.HomePageElements.*;
import static Config.common.GlobalReUsableMethods.*;
import static Config.common.GlobalReUsableMethods.ClickWebElement;

public class SignInPage extends WebTestBase {
    public SignInPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
      @FindBy(how = How.XPATH, using = SignInWindowElement) public WebElement signInElement;
    //@FindBy(xpath=SignInWindowElement) public  WebElement signInElement;
    @FindBy(id=emailInputBox1) public static WebElement emailInputBox;
    static String wrongEmail="jhasgfj.sakfjlas";
    static String validEmail="hasnabenjillali@icloud.com";
    static String wrongPass="145sdhvshbsh";
    @FindBy(id=continueElement) public static WebElement continueButton;
    @FindBy(xpath=verifyEmailSignIn)public static WebElement verifySignInWithWrongEmail;
    @FindBy(id="ap_password")public static WebElement passwordInputBox;
    @FindBy(id="signInSubmit") public static WebElement signInButton;
    @FindBy(id="nav-link-accountList-nav-line-1") public static WebElement verifySignInWithValidEmail;
    @FindBy(xpath="//span[@class='a-list-item']")public static WebElement verifySignInWithValidEmailAndWrongPass;
    @FindBy(id="nav-hamburger-menu") public static WebElement navigationBar;
    @FindBy(xpath=signOutElement) public static WebElement SignOut;
    @FindBy(xpath=verifySignOut) public static WebElement signOutVerify;
    public void signInWithWrongEmail() throws InterruptedException {
        ClickWebElement(signInElement);
        sendKeyWebElement(emailInputBox,wrongEmail);
        Thread.sleep(3000);
        ClickWebElement(continueButton);
    }
    public  void verifySignInUsingWrongEmail(String expectedResult){
         String actualResult=verifySignInWithWrongEmail.getText();
        Assert.assertEquals(actualResult,expectedResult,"Result does not match!");

    }
    public  void signInWithValidEmailAndWrongPass() throws InterruptedException {
        ClickWebElement(signInElement);
        sendKeyWebElement(emailInputBox,validEmail);
        Thread.sleep(3000);
        ClickWebElement(continueButton);
        sendKeyWebElement(passwordInputBox,wrongPass);
        ClickWebElement(signInButton);
    }
    public  void verifySignInUsingValidEmailAndWrongPass(String expectedResult){
        String actualResult=verifySignInWithValidEmailAndWrongPass.getText();
        Assert.assertEquals(actualResult,expectedResult,"Result does not match!");

    }
    public  void signInWithValidEmailAndValidPass() throws InterruptedException {
        ClickWebElement(signInElement);
        sendKeyWebElement(emailInputBox,validEmail);
        Thread.sleep(3000);
        ClickWebElement(continueButton);
        sendKeyWebElement(passwordInputBox,"QA_automation2022");
        ClickWebElement(signInButton);
    }
    public  void verifySignInUsingValidEmailAndValidPass(String expectedResult){
        String actualResult=verifySignInWithValidEmail.getText();
        Assert.assertEquals(actualResult,expectedResult,"Result does not match!");
    }
    public  void signOutWithValidEmailAndValidPass(){
        ClickWebElement(signInElement);
        sendKeyWebElement(emailInputBox,validEmail);
        ClickWebElement(continueButton);
        sendKeyWebElement(passwordInputBox,"QA_automation2022");
        ClickWebElement(signInButton);
        ClickWebElement(navigationBar);
        ClickWebElement(SignOut);
    }
    public  void verifySignOutUsingValidEmailAndValidPass(String expectedResult){
        String actualResult=signOutVerify.getText();
        Assert.assertEquals(actualResult,expectedResult,"Result does not match!");

    }



}
