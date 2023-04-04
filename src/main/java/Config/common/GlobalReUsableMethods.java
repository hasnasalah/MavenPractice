package Config.common;

import Config.reports.TestLogger;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class GlobalReUsableMethods extends WebTestBase{
    public static void ClickElementByXPath(String locator){
        driver.findElement(By.xpath(locator)).click();
    }
    public static void ClickElementByCssSelector(String locator){
        driver.findElement(By.cssSelector(locator)).click();
    }
    public static void ClickElementById(String locator){
        driver.findElement(By.id(locator)).click();
    }
    public static void ClickElementByName(String locator){
        driver.findElement(By.name(locator)).click();
    }
    public static void enterKeyElementByXPath(String locator,String key){
        driver.findElement(By.xpath(locator)).click();
    }
    public static void enterKeyElementByCssSelector(String locator,String key){
        driver.findElement(By.cssSelector(locator)).sendKeys(key);
    }
    public static void enterKeyElementById(String locator,String key){
        driver.findElement(By.id(locator)).sendKeys(key);
    }
    public static void enterKeyElementByName(String locator,String key){
        driver.findElement(By.name(locator)).sendKeys(key);
    }
    public static void ClickWebElement(WebElement element){
        element.click();
    }
    public static void sendKeyWebElement(WebElement element,String key){
        element.clear();
        element.sendKeys(key);
    }
    public static void scrollElement(WebElement element){
        JavascriptExecutor javascriptExecutor= (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("argument[0].scrollIntoView();",element);
    }
    public static void scrollElement1(int num){
        JavascriptExecutor javascriptExecutor= (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("window.scroll(0,"+num+");");
    }
    public static void selectByVisibleText(WebElement element, String visibleText)
    {
        Select select=new Select(element);
        select.selectByVisibleText(visibleText);

    }
    public static void selectByValue(WebElement element, String value)
    {
        Select select=new Select(element);
        select.selectByValue(value);

    }
    public static void selectByIndex(WebElement element, int index)
    {
        Select select=new Select(element);
        select.selectByIndex(index);

    }
    public static void navigateForward(){
      driver.navigate().forward();
    }
    public static void navigateBackWard(){
        driver.navigate().back();
    }
    public static void refreshPage(){
        driver.navigate().refresh();
    }
    public static void clearFile(WebElement element){
       element.clear();
    }
    public static void doubleClick(WebElement element){
        Actions actions =new Actions(driver);
        actions.contextClick(element).perform();
    }
    public static void checkTitle(){
        TestLogger.log("Get Title");
        driver.getTitle();
    }
    public static String getText(WebElement element, String elementName){
        TestLogger.log("Getting text from: "+elementName);
        String actualText=element.getText();
        TestLogger.log("Actual text: "+actualText);
        return actualText;
    }
    public static String getCurrentUrl(){
        return driver.getCurrentUrl();

    }
    public static void mouseHoverByWebElementWithPerform(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public static void mouseHoverByWebElementWithOutPerform(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
    }
//    public static void moveHover(WebElement element) throws InterruptedException {
//        Actions actions =new Actions(driver);
//        try {
//            actions.moveToElement(element);
//        }
//        catch(Exception e){
//            getLog("First Attempt is not working");
//            actions.moveToElement(element);
//            Thread.sleep(5000);
//        }
//    }
    public static void okAlert(){
        Alert alert=driver.switchTo().alert();
        alert.accept();
    }
    public static void cancelAlert(){
        Alert alert=driver.switchTo().alert();
        alert.dismiss();
    }
    //alert displayed/ dynamic handel
    public static void dynamicAlertHandel(){
        WebDriverWait webDriverWait=new WebDriverWait(driver, Duration.ofSeconds(30));
        if(webDriverWait.until(ExpectedConditions.alertIsPresent())==null){
          getLog("Alert is not present");
        }
        else{
            getLog("Alert is present");
           okAlert();
        }
    }
    public static void dynamicAlertHandelCancel(){
        WebDriverWait webDriverWait=new WebDriverWait(driver, Duration.ofSeconds(30));
        if(webDriverWait.until(ExpectedConditions.alertIsPresent())==null){
            getLog("Alert is not present");
        }
        else{
            getLog("Alert is present");
            cancelAlert();
        }
    }
//iframe handel
    public static void iframHandel(WebElement element){
        driver.switchTo().frame(element);
    }
// go back to home window from iframe
    public static void goBackToHomeWindow(){
        driver.switchTo().defaultContent();
    }
    // upload a file
    public static void uploadFile(WebElement element, String path){
        element.sendKeys(path);
    }
    public static void getLinks(String locator){
        driver.findElement(By.linkText(locator)).findElement(By.tagName("a"));
    }
    public void takeScreenShot() throws IOException {
        File file=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file,new File("Automation.png"));
    }
    public static WebDriver handelNewTab( WebDriver driver, int windowIndex){
        String oldTab=driver.getWindowHandle();
        List<String> newTabs=new ArrayList<>(driver.getWindowHandles());
        newTabs.remove(oldTab);
        driver.switchTo().window(newTabs.get(windowIndex));
        return driver;
    }
    //pop window displayed
    public static boolean isPopWindowDisplayed(WebDriver driver,String locator){
        return driver.findElement(By.xpath(locator)).isDisplayed();
    }
    public static boolean anotherApprochIsPopUpWindowIsDisplayed(WebElement element){
        return element.isDisplayed();
    }
    public static WebElement explicitWaitForElementUsingVisibilityOf(WebElement element){
        WebDriverWait webDriverWait=new WebDriverWait(driver, Duration.ofSeconds(30));
        return webDriverWait.until(ExpectedConditions.visibilityOf(element));
    }
    public static WebElement explicitWaitForElementUsingClickable(WebElement element){
        WebDriverWait webDriverWait=new WebDriverWait(driver, Duration.ofSeconds(30));
        return webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public static boolean explicitWaitForElementUsingToBeSelected(WebElement element){
        WebDriverWait webDriverWait=new WebDriverWait(driver, Duration.ofSeconds(30));
       return webDriverWait.until(ExpectedConditions.elementToBeSelected(element));
    }
    public static void waitUntilClickable(WebElement element){
        WebDriverWait webDriverWait=new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement clickable= webDriverWait.until(ExpectedConditions.visibilityOf(element));
        clickable.click();
    }
    public static WebElement fluentWaitUntilConditionMeet(WebElement ele){
        Wait<WebDriver> fluentWait= new FluentWait<>(driver).withTimeout(Duration.ofSeconds(60)).
                pollingEvery(Duration.ofSeconds(3)).withMessage("Time out").ignoring(NoSuchElementException.class);
        return fluentWait.until(new Function<>() {
            public WebElement apply(WebDriver driver) {
                return ele;
            }
        });
    }
    public static WebElement fluentWaitUntilConditionMeetUsingLambda(WebElement ele) {
        Wait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(60))
                .pollingEvery(Duration.ofSeconds(3)).withMessage("Time out")
                .ignoring(NoSuchElementException.class);
        return fluentWait.until(driver1 -> ele);
    }
    public static WebElement fluentWaitUntilConditionMeetUsingBeforeLambda(WebElement ele) {
        Wait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(60))
                .pollingEvery(Duration.ofSeconds(3)).withMessage("Time out")
                .ignoring(NoSuchElementException.class);
        return fluentWait.until(new Function<>() {
            public WebElement apply(WebDriver driver1) {
                return ele;
            }
        });
    }
    public static List<WebElement> getListOfElementsByXpath(String locator) {
        List<WebElement> elementList = new ArrayList<>();
        elementList = driver.findElements(By.xpath(locator));
        return elementList;
    }

    public static List<WebElement> getListOfElementsFromWebElement(List<WebElement> elements) {
        List<WebElement> elementList = new ArrayList<>();
        elementList = elements;
        return elementList;
    }

    public static List<String> getListOfString(List<WebElement> elements) {
        List<String> elementList = new ArrayList<>();
        for (WebElement element : elements) {
            elementList.add(element.getText());
        }
        return elementList;
    }

    // Broken link

    // Customer Made Helper Methods for Amex.com
    public void brokenLink() throws IOException {
        //Step:1-->Get the list of all the links and images
        List<WebElement> linksList = driver.findElements(By.tagName("a"));
        linksList.addAll(driver.findElements(By.tagName("img")));

        System.out.println("Total number of links and images--------->>> " + linksList.size());

        //Step:2-->Iterate linksList: exclude all links/images which does not have any href attribute
        List<WebElement> activeLinks = new ArrayList<WebElement>();
        for (int i = 0; i < linksList.size(); i++) {
            System.out.println(linksList.get(i).getAttribute("href"));
            if (linksList.get(i).getAttribute("href") != null && (!linksList.get(i).getAttribute("href").contains("javascript") && (!linksList.get(i).getAttribute("href").contains("mailto")))) {
                activeLinks.add(linksList.get(i));
            }
        }
        System.out.println("Total number of active links and images-------->>> " + activeLinks.size());

        //Step:3--> Check the href url, with http connection api
        for (int j = 0; j < activeLinks.size(); j++) {
            HttpURLConnection connection = (HttpURLConnection) new URL(activeLinks.get(j).getAttribute("href")).openConnection();
            connection.connect();
            String response = connection.getResponseMessage();
            connection.disconnect();
            System.out.println(activeLinks.get(j).getAttribute("href") + "--------->>> " + response);
        }
    }
    public void assertEqualByXpath(String loc, String expValue) {
        String act = driver.findElement(By.xpath(loc)).getText();
        // act is coming from Domain -- the one developer build and release
        String exp = expValue; // exp is coming from Requirement or Mock-up
        Assert.assertEquals(act, exp);
    }

    public static void verifyText(WebElement element, String expValue, String message) {
        String actual = element.getText();
        Assert.assertEquals(actual, expValue, message);
    }
    public static void sliderHandling(String locator) {
        driver.switchTo().frame(0); //need to switch to this frame before clicking the slider
        WebElement slider = driver.findElement(By.xpath(locator));
        Actions move = new Actions(driver);
        Action action = (Action) move.dragAndDropBy(slider, 30, 0).build();
        action.perform();
    }
    public static String convertToString(String st) {
        String splitString = "";
        splitString = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(st), ' ');
        return splitString;
    }



    // Drag and drop
    // https://www.browserstack.com/guide/drag-and-drop-in-selenium
    public static void dragAndDrop(String fromLocator, String toLocator) {
        //WebElement on which drag and drop operation needs to be performed
        WebElement fromElement = driver.findElement(By.xpath(fromLocator));

        //WebElement to which the above object is dropped
        WebElement toElement = driver.findElement(By.xpath(toLocator));

        //Creating object of Actions class to build composite actions
        Actions builder = new Actions(driver);

        //Building a drag and drop action
        Action dragAndDrop = builder.clickAndHold(fromElement)
                .moveToElement(toElement)
                .release(toElement)
                .build();

        //Performing the drag and drop action
        dragAndDrop.perform();
    }


    // Links
    // Partial Links

    public static void clickOnLink(String locator) {
        driver.findElement(By.linkText(locator)).click();
    }

    public static void clickOnPartialLink(String locator) {
        driver.findElement(By.partialLinkText(locator)).click();
    }

    // CheckBox
    // enable or disable button

    public static boolean isEnableOrDisable(WebElement element) {
        return element.isEnabled();
    }

    public static boolean isDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    public static boolean isSelected(WebElement element) {
        return element.isSelected();
    }


    // Date picker
    // https://www.browserstack.com/guide/datepicker-in-selenium



    // Captcha automation: https://www.browserstack.com/guide/how-to-handle-captcha-in-selenium

}
