package Amazon.Pages;

import Config.common.WebTestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import static Amazon.WebElements.HomePageElements.*;
import static Config.common.GlobalReUsableMethods.*;
import static Config.common.GlobalReUsableMethods.ClickWebElement;

public class HomePage extends WebTestBase {
    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        //  WebTestBase.driver = driver;
    }
//    @FindBy(how = How.XPATH, using = searchBoxWebElement) public WebElement searchBox1;
//    @FindBy(how = How.XPATH, using = searchButtonWebElement) public WebElement searchButton1;
    @FindBy (xpath= scrollWebElement)public WebElement scroll;
    @FindBy(xpath = searchBoxWebElement) public WebElement searchBox;
    @FindBy(xpath = searchButtonWebElement) public WebElement searchButton;
    @FindBy(xpath = verifySearchedProductWebElement) public WebElement verifySearchedProduct;
   // @FindBy (xpath  =searchScrollWebElement ) public WebElement searchScrollElement;
    @FindBy(xpath = verifySearchScrollWebElement) public WebElement verifySearchScroll;
    @FindBy(xpath=searchElement)public WebElement searchElementEaster;
    @FindBy(id="add-to-cart-button") public WebElement addToCart;
    @FindBy(id="nav-cart-count") public WebElement cartIcon;
    @FindBy(xpath=verifyCart) public WebElement verifyIfProductIsInCart;
    @FindBy(xpath=hoverOver)public static WebElement hover;
    @FindBy(xpath=elementHover)public static WebElement elementToHover;
    @FindBy(id="prime-hero-module-headline") public static WebElement actual;


    public void searchProductUsingValidProductName()  {
        sendKeyWebElement(searchBox,"Hand Sanitizer");
        ClickWebElement(searchButton);
    }
    public void scrollDropBox()  {
        selectByVisibleText(scroll,"Arts, Crafts & Sewing");
        sendKeyWebElement(searchBox,"hand sewing machine");
        ClickWebElement(searchButton);

    }
    public void verifyProductUsingValidProductNameScrollSearch( String expectedProduct) {
        String actualProduct=verifySearchScroll.getText();
        Assert.assertEquals(actualProduct, expectedProduct, "Expected Text does not match");
    }
    public void verifyProductUsingValidProductName( String expectedResult) {
        String actualProduct=verifySearchedProduct.getText();
        Assert.assertEquals(actualProduct, expectedResult, "Expected Text does not match");
    }
    public void addProductToCart() throws InterruptedException {
        sendKeyWebElement(searchBox,"plastic easter eggs");
        ClickWebElement(searchButton);
        scrollElement1(2000);
        ClickWebElement(searchElementEaster);
        ClickWebElement(addToCart);
        ClickWebElement(cartIcon);
    }
    public void setVerifyIfProductIsInCarts(String expectedResult){
        String actualProduct=verifyIfProductIsInCart.getText();
        Assert.assertEquals(actualProduct, expectedResult, "Expected Text does not match");
    }
    public static void tryHoverOverMouse(String expected) throws InterruptedException {
        //mouseHoverByWebElementWithPerform(hover);
        Actions actions = new Actions(driver);
        actions.moveToElement(hover).perform();
        Thread.sleep(10000);
        // explicitWaitForElementUsingVisibilityOf(elementToHover);
        ClickWebElement(elementToHover);
        verifyText(actual,expected,"result doesn't match");
    }

}
