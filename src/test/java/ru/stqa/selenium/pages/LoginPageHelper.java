package ru.stqa.selenium.pages;

import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class LoginPageHelper extends PageBase{
    @FindBy(linkText = "Log In")
    WebElement logInIcon;

    @FindBy(id = "login")
    WebElement loginButton;

    @FindBy(id = "user")
    WebElement userField;

    @FindBy(id = "login-submit")
    WebElement loginSubmitButton;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(css = "#error>p")
    WebElement noLoginNoPasswordError;

    public LoginPageHelper(WebDriver driver){
        super(driver);
    }

    public LoginPageHelper openLoginPage(){
        log4j.info("-- Class LoginPageHelper, method openLoginPage() was started");
        log4j.info("Waiting until link 'Log In' is clickable");
        waitUntilElementIsClickable(logInIcon,10);
        log4j.info("Click link 'Log In'");
        logInIcon.click();
        log4j.info("Waiting until button 'Log in' is clickable");
        waitUntilElementIsClickable(loginButton,10);
        return this;
    }

    public LoginPageHelper enterLoginAtlassianAndClickLogin(String login) {
        log4j.info("-- Class LoginPageHelper, method enterLoginAtlassianAndClickLogin() was started");
        log4j.info("User field filling, value " + login);
        userField.sendKeys(login);
        log4j.info("Wait until text on the button is 'Log in with Atlassian'");
        waitUntilAttributeValueIs(loginButton,"value","Log in with Atlassian",25);
        log4j.info("Click on the login button");
        loginButton.click();
        log4j.info("Wait until submit button is clickable");
        waitUntilElementIsClickable(loginSubmitButton,15);
        return this;
    }

    public LoginPageHelper enterPasswordAtlassionAndClickLogin(String password) {
        log4j.info("--Class LoginPageHelper, method enterPasswordAtlassionAndClickLogin() was started");
        log4j.info("Password field is filling, value " + password);
        waitUntilElementIsVisible(passwordField,15);
        passwordField.sendKeys(password);
        log4j.info("Submit button was clicked");
        loginSubmitButton.click();
        return this;
    }

    public LoginPageHelper loginAsAtlassian(String login, String password){
        log4j.info("--Class LoginPageHelper, method loginAsAtlassian() was started");
        log4j.info("Enter login Atlassian and click login "  + login);
        this.enterLoginAtlassianAndClickLogin(login);
        log4j.info("Enter password Atlassian and click login "  + password);
        this.enterPasswordAtlassionAndClickLogin(password);
        return this;
    }

    public LoginPageHelper pressLoginButton() {
        log4j.info("--Class LoginPageHelper, method pressLoginButton() was started");
        log4j.info("Wait until button 'Log in' is clickable");
        waitUntilElementIsClickable(loginButton,20);
        log4j.info("Click button 'Log in'");
        loginButton.click();
        return this;
    }

    public LoginPageHelper waitErrorMessage() {
        log4j.info("--Class LoginPageHelper, method waitErrorMessage() was started");
        log4j.info("Wait until error message is visible");
        waitUntilElementIsVisible(noLoginNoPasswordError,10);
        return this;
    }

    public String getErrorMessage(){
        log4j.info("-- Class LoginPageHelper, method getErrorMessage() was started");
        log4j.info("Getting text of error message");
        return noLoginNoPasswordError.getText();
    }

    public LoginPageHelper enterLoginNormal(String login){
        log4j.info("-- Class LoginPageHelper, method enterLoginNormal("+login +") was started");
        WebElement loginField = driver.findElement((By.id("user")));
        log4j.info("Login field filling, value " + login);
        loginField.sendKeys(login);
        log4j.info(" Click 'Enter'");
        loginField.sendKeys(Keys.ENTER);
        return this;
    }

    public LoginPageHelper clickLoginButtonNormal() {
        log4j.info("-- Class LoginPageHelper, method clickLoginButtonNormal() was started");
        log4j.info("Wait until button 'Log in' is clickable");
        waitUntilElementIsClickable(loginButton,15);
        driver.manage().window().maximize(); // не работает без этого!
        log4j.info("Click button 'Log in'");
        loginButton.click();
        return this;
    }

    public LoginPageHelper waitErrorMessageLoginIncorrect() {
        log4j.info("-- Class LoginPageHelper, method waitErrorMessageLoginIncorrect() was started");
        log4j.info("Wait until error message is visible");
        waitUntilElementIsVisible(By.xpath("(//*[@class= 'error-message'])[1]"),30);
        WebElement errorMessage = driver.findElement(By.xpath("(//*[@class= 'error-message'])[1]"));
        System.out.println("Error message: " + errorMessage.getText());
        return this;
    }

    public String getErrorMessageloginIncorrect() {
        log4j.info("-- Class LoginPageHelper, method getErrorMessageloginIncorrect() was started");
        log4j.info("Getting text of error message");
        WebElement errorMessage = driver.findElement(By.xpath("(//*[@class= 'error-message'])[1]"));
        return errorMessage.getText();
    }

    public LoginPageHelper waitErrorMessagePasswordIncorrect() {
        log4j.info("-- Class LoginPageHelper, method waitErrorMessagePasswordIncorrect() was started");
        log4j.info("Wait until error message is visible");
        WebElement errorMessageIncorrectPassword;
        waitUntilElementIsVisible(By.xpath("//div[@id='login-error']/span"),15);
        return this;
    }

    public String getIncorrectPassswordMessage(){
        log4j.info("-- Class LoginPageHelper, method getIncorrectPassswordMessage() was started");
        log4j.info("Getting text of error message");
        WebElement errorMessageIncorrectPassword = driver.findElement(By.xpath("//div[@id='login-error']/span"));
        return errorMessageIncorrectPassword.getText();
    }

    public LoginPageHelper enterPasswordNormal(String password) {
        log4j.info("-- Class LoginPageHelper, method enterPasswordNormal("+password +") was started");
        log4j.info("Password field filling, value " + password);
        fillField(passwordField,password);
        return this;
    }
}
