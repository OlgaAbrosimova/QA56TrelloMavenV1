package ru.stqa.selenium.tests;

import com.google.common.io.Files;
import org.openqa.selenium.*;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.selenium.SuiteConfiguration;
import ru.stqa.selenium.factory.WebDriverPool;
import ru.stqa.selenium.pages.*;
import util.LogLog4j;

import java.io.File;
import java.io.IOException;
import java.net.URL;



public class TestBase {
    protected static URL gridHubUrl = null;
    protected static String baseUrl;
    protected static Capabilities capabilities;
    protected EventFiringWebDriver driver;
    public static final String BOARD_TITLE = "QA Haifa56";
    public static final String LOGIN = "gftov01@gmail.com";
    public static final String PASSWORD = "Victor100578@";
    public static final String USERNAME ="olgaabrosimova1";
    public static LogLog4j log4j = new LogLog4j();
    HomePageHelper homePage;

    public static class MyListener extends AbstractWebDriverEventListener{
        @Override
        public void beforeFindBy(By by, WebElement element, WebDriver driver) {
            log4j.info("Found element: " + by);
        }

        @Override
        public void afterFindBy(By by, WebElement element, WebDriver driver) {
            log4j.info("Element " + by + "was found ");
        }

        @Override
        public void onException(Throwable throwable, WebDriver driver) {
            String screenName = "screen-" + System.currentTimeMillis() + ".png";
            createSnapshot(screenName,driver);
            log4j.error("Error: "+ throwable + " See file " + screenName);
        }
    }

    public static void createSnapshot(String name, WebDriver driver){
        File tmp = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        File screen = new File(name);
        try {
            Files.copy(tmp,screen);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @BeforeSuite(alwaysRun = true)
    public void initTestSuite() throws IOException {
        SuiteConfiguration config = new SuiteConfiguration();
        baseUrl = config.getProperty("site.url");
        if (config.hasProperty("grid.url") && !"".equals(config.getProperty("grid.url"))) {
            gridHubUrl = new URL(config.getProperty("grid.url"));
        }
        capabilities = config.getCapabilities();
    }


    @BeforeMethod(alwaysRun = true)
    public void initWbDriver()  {
        //---- Enter to the application ---
        //driver = new ChromeDriver();
        driver = new EventFiringWebDriver(WebDriverPool.DEFAULT.getDriver(gridHubUrl, capabilities));
        driver.register(new MyListener());
        driver.get(baseUrl);
        homePage = PageFactory.initElements(driver,HomePageHelper.class);
        homePage.waitUntilPageIsLoaded();


    }

    @AfterMethod(alwaysRun = true)
    public void tearDownForTest(ITestResult result){
        if (result.getStatus() == ITestResult.FAILURE){
            String screenName = "screen-" + System.currentTimeMillis() + ".png";
            createSnapshot(screenName,driver);
            log4j.error("Test failure,  "+ "see file " + screenName);
        }
        driver.quit();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        WebDriverPool.DEFAULT.dismissAll();
    }


}
