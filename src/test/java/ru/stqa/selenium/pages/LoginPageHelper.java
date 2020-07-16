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
        waitUntilElementIsClickable(logInIcon,10);
        logInIcon.click();
        waitUntilElementIsClickable(loginButton,10);
        return this;
    }

    public LoginPageHelper enterLoginAtlassianAndClickLogin(String login) {
        fillField(userField,login);
        waitUntilAttributeValueIs(loginButton,"value","Log in with Atlassian",25);
        loginButton.click();
        waitUntilElementIsClickable(loginSubmitButton,15);
        return this;
    }

    public LoginPageHelper enterPasswordAtlassionAndClickLogin(String password) {
        waitUntilElementIsVisible(passwordField,15);
        fillField(passwordField,password);
        loginSubmitButton.click();
        return this;
    }

    public LoginPageHelper loginAsAtlassian(String login, String password){
        this.enterLoginAtlassianAndClickLogin(login);
        this.enterPasswordAtlassionAndClickLogin(password);
        return this;
    }

    public LoginPageHelper pressLoginButton() {
        waitUntilElementIsClickable(loginButton,20);
        loginButton.click();
        return this;
    }

    public LoginPageHelper waitErrorMessage() {
        waitUntilElementIsVisible(noLoginNoPasswordError,10);
        return this;
    }

    public String getErrorMessage(){
        return noLoginNoPasswordError.getText();
    }

    public LoginPageHelper enterLoginNormal(String login){
        userField.sendKeys(login);
        userField.sendKeys(Keys.ENTER);
        return this;
    }

    public LoginPageHelper clickLoginButtonNormal() {
        waitUntilElementIsClickable(loginButton,15);
        driver.manage().window().maximize(); // не работает без этого!
        loginButton.click();
        return this;
    }

    public LoginPageHelper waitErrorMessageLoginIncorrect() {
        waitUntilElementIsVisible(By.xpath("(//*[@class= 'error-message'])[1]"),30);
        WebElement errorMessage = driver.findElement(By.xpath("(//*[@class= 'error-message'])[1]"));
        System.out.println("Error message: " + errorMessage.getText());
        return this;
    }

    public String getErrorMessageloginIncorrect() {
        WebElement errorMessage = driver.findElement(By.xpath("(//*[@class= 'error-message'])[1]"));
        return errorMessage.getText();
    }

    public LoginPageHelper waitErrorMessagePasswordIncorrect() {
        WebElement errorMessageIncorrectPassword;
        waitUntilElementIsVisible(By.xpath("//div[@id='login-error']/span"),15);
        return this;
    }

    public String getIncorrectPassswordMessage(){
        WebElement errorMessageIncorrectPassword = driver.findElement(By.xpath("//div[@id='login-error']/span"));
        return errorMessageIncorrectPassword.getText();
    }

    public LoginPageHelper enterPasswordNormal(String password) {
        fillField(passwordField,password);
        return this;
    }
}
