package Amazon.Pages;

import Config.common.WebTestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

import static Amazon.WebElements.HomePageElements.*;
import static Config.common.GlobalReUsableMethods.*;

public class FooterInHomePage extends WebTestBase {
    public FooterInHomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = footerElements1)
    public static List<WebElement> footer1;
    @FindBy (xpath=verifyFooterElements) public static WebElement footerVerify;
    @FindBy(xpath=footerElements2) public static List<WebElement> footer2;

    public static void testFooterElements() {
        Boolean status = false;
        for (int i = 0; i < footer1.size(); i++) {
            System.out.println(footer1.get(i).getText());
            if (footer1.get(i).getText().equalsIgnoreCase("Amazon Newsletter")) {
                status = true;
            }
        }
        if (status == true) {
            System.out.println("it is found ===================");
        } else
            System.out.println("nothing");
    }

    public static void testcase2findElementsVerifyAndClick() throws InterruptedException {
        scrollElement1(6500);
        //Thread.sleep(5000);
        for (int i = 0; i < footer1.size(); i++) {
            System.out.println("Value : "+footer1.get(i).getText());

            if (footer1.get(i).getText().equalsIgnoreCase("Amazon Advertising\n" +
                    "Find, attract, and\n" +
                    "engage customers")) {

                footer1.get(i).click();
                break;
            }
            Thread.sleep(5000);
        }
    }
    public static void verifyCase2findElementsVerifyAndClick(String expectedProduct){
        String actualResult=footerVerify.getText();
        Assert.assertEquals(actualResult,expectedProduct,"Expected Text does not match");
    }
}



