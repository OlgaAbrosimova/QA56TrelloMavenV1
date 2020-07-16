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

    @BeforeMethod
    public void initTests(){
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver,BoardsPageHelper.class);
        loginPage.openLoginPage();
    }

    @Test
    public void loginTestPositive()  {
        loginPage.enterLoginAtlassianAndClickLogin(LOGIN)
                 .enterPasswordAtlassionAndClickLogin(PASSWORD);
        boardsPage.waitUntilPageIsLoaded();
        Assert.assertEquals(boardsPage.getButtonBoardsText(),"Boards","Text on the boardIcon is not 'Boards'");
    }

    @Test
    public void loginNegativeNoLoginNoPassword()  {
        loginPage.pressLoginButton()
                 .waitErrorMessage();
        Assert.assertEquals("Missing email",loginPage.getErrorMessage(),"The text of the message is not 'Missing email'");
    }

    @Test(dataProviderClass = DataProviders.class,dataProvider = "dataProviderFirst")
    public void NegativeLoginIncorrect(String login,String password,String message) {
        loginPage.enterLoginNormal(login)
                 .enterPasswordNormal(password)
                 .clickLoginButtonNormal()
                 .waitErrorMessageLoginIncorrect();
        Assert.assertEquals(loginPage.getErrorMessageloginIncorrect(),message,"Error message is not correct");
    }

    @Test(dataProviderClass = DataProviders.class,dataProvider = "dataProviderSecond")
    public void NegativeLoginIncorrectDP2(String login,String password,String message) {
        loginPage.enterLoginNormal(login)
                 .enterPasswordNormal(password)
                 .clickLoginButtonNormal()
                 .waitErrorMessageLoginIncorrect();
        Assert.assertEquals(loginPage.getErrorMessageloginIncorrect(),message,"Error message is not correct");
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "DPnegativePasswordIncorrect")
    public void NegativePasswordIncorrect(String login, String password)  {
        loginPage.loginAsAtlassian(login,password)
                 .waitErrorMessagePasswordIncorrect();
        //---Print error message ---
        WebElement errorMessage = driver.findElement(By.xpath("//div[@id='login-error']/span"));
        System.out.println("Error message: " + errorMessage.getText());
        Assert.assertTrue(loginPage.getIncorrectPassswordMessage().contains("Incorrect email address and / or password."),
                "There is no error message or the text of the message is not correct");
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "dataProviderThird")
    public void NegativeLoginIncorrectDP3(String login,String password) {
        loginPage.enterLoginNormal(login)
                 .enterPasswordNormal(password)
                 .clickLoginButtonNormal()
                 .waitErrorMessageLoginIncorrect();
        Assert.assertEquals(loginPage.getErrorMessageloginIncorrect(),"There isn't an account for this email","Error message is not correct");
    }
}
