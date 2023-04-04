package Config.common;

import Config.reports.ExtentManager;
import Config.reports.ExtentTestManager;
import Config.utilities.ReadPropertiesFrom;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;
import org.testng.annotations.Optional;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class WebTestBase {
    public static WebDriver driver;

    String username = System.getenv("BROWSERSTACK_USERNAME");
    String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
    String buildName = System.getenv("BROWSERSTACK_BUILD_NAME");
    String local = System.getenv("BROWSERSTACK_LOCAL");
    String LocalIdentifier = System.getenv("BROWSERSTACK_LOCAL_IDENTIFIER");

    //public static final String BROWSERSTACK_USERNAME = "larreneseaver_qRxJGs";
    //public static final String BROWSERSTACK_ACCESS_KEY = "1A7k89upqYxedGNbVqzb";
    static Properties readProperties= ReadPropertiesFrom.loadProperties("src/main/resources/configuration.properties");

    public static final String SAUCE_LABS_USERNAME = "oauth-benjillali.hasna-9e56f";
    public static final String SAUCE_LABS_ACCESS_KEY = "fa425eff-48e7-464a-95e6-e5062595dea2";

    /**
     * **************************************************
     * ********** Start Of Reporting Utilities **********
     * **************************************************
     * **************************************************
     */
    //ExtentReport
    public static ExtentReports extent;
    public static ExtentTest logger;


    @BeforeSuite
    public void extentSetup(ITestContext context) {
        ExtentManager.setOutputDirectory(context);
        extent = ExtentManager.getInstance();
    }

    @BeforeMethod
    public void startExtent(Method method) {
        String className = method.getDeclaringClass().getSimpleName();
        String methodName = method.getName().toLowerCase();
        ExtentTestManager.startTest(method.getName());
        ExtentTestManager.getTest().assignCategory(className);
    }

    protected String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        return sw.toString();
    }

    @AfterMethod
    public void afterEachTestMethod(ITestResult result) throws Exception {
        ExtentTestManager.getTest().getTest().setStartedTime(getTime(result.getStartMillis()));
        ExtentTestManager.getTest().getTest().setEndedTime(getTime(result.getEndMillis()));
        for (String group : result.getMethod().getGroups()) {
            ExtentTestManager.getTest().assignCategory(group);
        }
        if (result.getStatus() == 1) {
            ExtentTestManager.getTest().log(LogStatus.PASS, "Test Passed");
        } else if (result.getStatus() == 2) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, getStackTrace(result.getThrowable()));
            String screenshotPath = captureScreenShotWithPath(driver, result.getName());

        } else if (result.getStatus() == 3) {
            ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
        }
        ExtentTestManager.endTest();
        extent.flush();

        // driver.close();
        //driver.quit();
        // ending test
        //endTest(logger) : It ends the current test and prepares to create HTML report
        extent.endTest(logger);
    }

    @AfterSuite
    public void generateReport() {
        extent.close();
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }


    public static String convertToString(String st) {
        String splitString = "";
        splitString = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(st), ' ');
        return splitString;
    }


    @BeforeTest
    public void setUpAutomation() {
        getLog("********************************* Automation Start *********************************");
    }


    @Parameters({"userCloudEnv", "cloudEnvName", "os", "osVersion", "browserName", "browserVersion", "url"})
    @BeforeMethod
    public void setUpBrowser(@Optional() boolean useCloudEnv, @Optional() String cloudEnvName, @Optional() String os, @Optional() String osVersion, @Optional() String browserName, @Optional() String browserVersion, @Optional() String url) throws MalformedURLException {
        if (useCloudEnv) {
            getCloudDriver(cloudEnvName, os, osVersion, browserName, browserVersion);
        } else {
            getLocalDriver(browserName);
        }
        driverSetUp(url);
    }

    public void getLocalDriver(String browserName) {
        if (Objects.equals(browserName, "chrome")) {
            setUpChromeBrowserWithWebDriverManager();
        } else if (Objects.equals(browserName, "firefox")) {
            setUpFirefoxBrowserWithWebDriverManager();
        } else if (Objects.equals(browserName, "internetExplorer")) {
            setUpInternetExplorerBrowserWithWebDriverManager();
        } else if (Objects.equals(browserName, "edge")) {
            setUpEdgeBrowserWithWebDriverManager();
        } else if (Objects.equals(browserName, "safari")) {
            setUpSafariBrowserWithWebDriverManager();
        }
    }

    public void getCloudDriver(String envName, String os, String osVersion, String browserName, String browserVersion) throws MalformedURLException {
        // Add the following capabilities to run your test script
        String buildName = System.getenv("BROWSERSTACK_BUILD_NAME");
        String local = System.getenv("BROWSERSTACK_LOCAL");
        String LocalIdentifier = System.getenv("BROWSERSTACK_LOCAL_IDENTIFIER");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browserName);
        HashMap<String, Object> options = new HashMap<>();
        options.put("os", os);
        options.put("osVersion", osVersion);

        options.put("seleniumVersion", "4.0.0");

        if (envName.equalsIgnoreCase("browserStack")) {
            capabilities.setCapability("bstack:options", options);
            //driver = new RemoteWebDriver(new URL("https://" + BROWSERSTACK_USERNAME + ":" + BROWSERSTACK_ACCESS_KEY + "@hub.browserstack.com/wd/hub"), capabilities);
            driver = new RemoteWebDriver(new URL("https://" + readProperties.getProperty("BROWSER_STACK_USER_NAME") + ":" + readProperties.getProperty("BROWSERSTACK_ACCESS_KEY") + "@hub.browserstack.com/wd/hub"), capabilities);
        } else if (envName.equalsIgnoreCase("sauceLabs")) {
            ChromeOptions browserOptions = new ChromeOptions();
            browserOptions.setPlatformName(os);
            browserOptions.setBrowserVersion(browserVersion);
            options.put("build", "selenium-build-KLW8W");
            browserOptions.setCapability("name", "<your test name>");
            browserOptions.setCapability("sauce:options", options);
            capabilities.setCapability("osVersion", osVersion);
            URL url = new URL("https://" + SAUCE_LABS_USERNAME + ":" + SAUCE_LABS_ACCESS_KEY + "@ondemand.us-west-1.saucelabs.com:443/wd/hub");
            driver = new RemoteWebDriver(url, browserOptions);
        }
    }

    public void setUpChromeBrowserWithWebDriverManager() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(chromeOptions);
    }

    public void setUpFirefoxBrowserWithWebDriverManager() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--remote-allow-origins=*");
        driver = new FirefoxDriver(firefoxOptions);
    }

    public void setUpInternetExplorerBrowserWithWebDriverManager() {
        WebDriverManager.iedriver().setup();
        driver = new InternetExplorerDriver();
    }

    public void setUpSafariBrowserWithWebDriverManager() {
        WebDriverManager.safaridriver().setup();
        driver = new SafariDriver();
    }

    public void setUpEdgeBrowserWithWebDriverManager() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
    }


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    public void driverSetUp(String url) {
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
        driver.manage().deleteAllCookies();
    }
    public static void getLog(String message){
        Reporter.log(message);
    }
    public static String captureScreenShotWithPath(WebDriver driver, String screenShotName) {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File file = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String fileName = System.getProperty("user.dir") + "/ScreenShots/" + screenShotName + "_" + dateName + ".png";
        try {
            FileUtils.copyFile(file, new File(fileName));
            getLog("ScreenShot Captured");
        } catch (IOException e) {
            getLog("Exception while taking ScreenShot " + e.getMessage());
        }
        return fileName;
    }
}


