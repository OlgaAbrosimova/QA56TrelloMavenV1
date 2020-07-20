package ru.stqa.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HelpSectionPageHelper extends PageBase {
    @FindBy(xpath = "//div[@class='atlaskit-portal']//iframe")
    WebElement frameHelp;

    @FindBy(xpath = "//a[contains(text(),'Getting Started Guide')]")
    WebElement gettingStartedGuideMenu;

    public HelpSectionPageHelper(WebDriver driver) {
        super(driver);
    }

    public HelpSectionPageHelper waitUntilPageIsLoaded(){
        waitUntilFrameIsLoadedAndSwitchToIt(frameHelp, 30);
        return this;
    }

    public HelpSectionPageHelper chooseGettingStartedGuideMenu(){
        log4j.info("-- Class HelpSectionPageHelper, method chooseGettingStartedGuideMenu() was started");
        log4j.info("Wait until 'Getting started guide' menu item is clickable");
        waitUntilElementIsClickable(gettingStartedGuideMenu,15);
        log4j.info("Click 'Getting started guide' menu item");
        gettingStartedGuideMenu.click();
        return this;
    }
}
