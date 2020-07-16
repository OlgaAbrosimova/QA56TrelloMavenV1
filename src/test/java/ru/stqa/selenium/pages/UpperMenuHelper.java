package ru.stqa.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UpperMenuHelper extends PageBase {
    @FindBy(xpath = "//button[@data-test-id = 'header-member-menu-button']")
    WebElement upperRight;

    @FindBy(xpath = "//a[@data-test-id = 'header-member-menu-profile']")
    WebElement profilevisabilityMenuItem;

    @FindBy(xpath = "//span[contains(text(),'Activity')]/..")
    WebElement activityMenuItem;

    @FindBy(xpath = "(//span[contains(text(),'Activity')]/..)[2]")
    WebElement activityMenuItemFromCurrentBoard;

    @FindBy(xpath = "//button/span[contains(text(),'Help')]")
    WebElement helpMenuItem;


    public UpperMenuHelper(WebDriver driver) {
        super(driver);
    }

    public UpperMenuHelper waitUntilPageIsLoaded(){
        waitUntilElementIsClickable(profilevisabilityMenuItem,20);
        //waitUntilElementIsClickable(activityMenuItem,20);
        waitUntilElementIsClickable(helpMenuItem,20);
        return this;
    }

    public UpperMenuHelper openProfileVisabilityScreen(){
        profilevisabilityMenuItem.click();
        return this;
    }

    public UpperMenuHelper openMenuPage(){
        waitUntilElementIsClickable(upperRight,20);
        upperRight.click();
        return this;
    }

    public UpperMenuHelper openActivityPage() {
        activityMenuItemFromCurrentBoard.click();
        return this;
    }

    public UpperMenuHelper openHelpMenu(){
        helpMenuItem.click();
        return this;
    }
}
