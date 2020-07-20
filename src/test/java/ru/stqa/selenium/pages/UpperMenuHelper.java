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
        log4j.info("-- Class UpperMenuHelper, method waitUntilPageIsLoaded() was started");
        log4j.info("Wait until menu item 'Profile and Visibility' is clickable");
        waitUntilElementIsClickable(profilevisabilityMenuItem,20);
        //waitUntilElementIsClickable(activityMenuItem,20);
        log4j.info("Wait until menu item 'Help' is clickable");
        waitUntilElementIsClickable(helpMenuItem,20);
        return this;
    }

    public UpperMenuHelper openProfileVisabilityScreen(){
        log4j.info("-- Class UpperMenuHelper, method openProfileVisabilityScreen() was started");
        log4j.info("Click menu item 'Profile and Visibility'");
        profilevisabilityMenuItem.click();
        return this;
    }

    public UpperMenuHelper openMenuPage(){
        log4j.info("-- Class UpperMenuHelper, method openMenuPage() was started");
        log4j.info("Wait until button 'header member menu' is clickable");
        waitUntilElementIsClickable(upperRight,20);
        log4j.info("Click button 'header member menu'");
        upperRight.click();
        return this;
    }

    public UpperMenuHelper openActivityPage() {
        log4j.info("-- Class UpperMenuHelper, method openActivityPage() was started");
        log4j.info("Click menu item 'Activity' from current board");
        activityMenuItemFromCurrentBoard.click();
        return this;
    }

    public UpperMenuHelper openHelpMenu(){
        log4j.info("-- Class UpperMenuHelper, method openHelpMenu() was started");
        log4j.info("Click menu item 'Help'");
        helpMenuItem.click();
        return this;
    }
}
