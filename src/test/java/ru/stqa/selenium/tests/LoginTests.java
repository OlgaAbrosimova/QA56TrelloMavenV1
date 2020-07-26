package ru.stqa.selenium.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.selenium.pages.*;
import util.DataProviders;


public class LoginTests extends TestBase{
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;

    @BeforeMethod(alwaysRun = true)
    public void initTests(){
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver,BoardsPageHelper.class);
        loginPage.openLoginPage();
    }

    @Test(groups = {"smoke","regression","login"})
    public void loginTestPositive()  {
        log4j.startTestCase("loginTestPositive");
        log4j.info("Login/password were entering: " + LOGIN + ", " + PASSWORD);
        loginPage.enterLoginAtlassianAndClickLogin(LOGIN)
                 .enterPasswordAtlassionAndClickLogin(PASSWORD);
        log4j.info("Boards screen is loading");
        boardsPage.waitUntilPageIsLoaded();
        log4j.info("Test result verification (assert): Text on the boardIcon is 'Boards'");

        Assert.assertEquals(boardsPage.getButtonBoardsText(),"Boards","Text on the boardIcon is not 'Boards'");
        log4j.endTestCase();
    }

    @Test
    public void loginNegativeNoLoginNoPassword()  {
        log4j.startTestCase("loginNegativeNoLoginNoPassword");
        loginPage.pressLoginButton()
                 .waitErrorMessage();
        Assert.assertEquals("Missing email",loginPage.getErrorMessage(),"The text of the message is not 'Missing email'");
        log4j.endTestCase();
    }

    @Test(groups = {"smoke"},dataProviderClass = DataProviders.class,dataProvider = "dataProviderFirst")
    public void NegativeLoginIncorrect(String login,String password,String message) {
        log4j.startTestCase("NegativeLoginIncorrect: " + login +", "+ password + ", " + message);
        log4j.info("Login to the system: " + login + ", "+ password);
        loginPage.enterLoginNormal(login)
                 .enterPasswordNormal(password)
                 .clickLoginButtonNormal()
                 .waitErrorMessageLoginIncorrect();
        Assert.assertEquals(loginPage.getErrorMessageloginIncorrect(),message,"Error message is not correct");
        log4j.endTestCase();
    }

    @Test(dataProviderClass = DataProviders.class,dataProvider = "dataProviderSecond")
    public void NegativeLoginIncorrectDP2(String login,String password,String message) {
        log4j.startTestCase("NegativeLoginIncorrectDP2: " + login +", "+ password + ", " + message);
        log4j.info("Login to the system: " + login + ", "+ password);
        loginPage.enterLoginNormal(login)
                 .enterPasswordNormal(password)
                 .clickLoginButtonNormal()
                 .waitErrorMessageLoginIncorrect();
        Assert.assertEquals(loginPage.getErrorMessageloginIncorrect(),message,"Error message is not correct");
        log4j.endTestCase();
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "DPnegativePasswordIncorrect")
    public void NegativePasswordIncorrect(String login, String password)  {
        log4j.startTestCase("NegativePasswordIncorrect: " + login +", "+ password);
        log4j.info("Login to the system: " + login + ", "+ password);
        loginPage.loginAsAtlassian(login,password)
                 .waitErrorMessagePasswordIncorrect();
        //---Print error message ---
        WebElement errorMessage = driver.findElement(By.xpath("//div[@id='login-error']/span"));
        System.out.println("Error message: " + errorMessage.getText());
        Assert.assertTrue(loginPage.getIncorrectPassswordMessage().contains("Incorrect email address and / or password."),
                "There is no error message or the text of the message is not correct");
        log4j.endTestCase();
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "dataProviderThird")
    public void NegativeLoginIncorrectDP3(String login,String password) {
        log4j.startTestCase("NegativeLoginIncorrectDP3: " + login +", "+ password);
        log4j.info("Login to the system: " + login + ", "+ password);
        loginPage.enterLoginNormal(login)
                 .enterPasswordNormal(password)
                 .clickLoginButtonNormal()
                 .waitErrorMessageLoginIncorrect();
        Assert.assertEquals(loginPage.getErrorMessageloginIncorrect(),"There isn't an account for this email","Error message is not correct");
        log4j.endTestCase();
    }
}
