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

    public void waitUntilPageIsLoaded(){
        waitUntilFrameIsLoadedAndSwitchToIt(frameHelp, 30);

    }

    public void chooseGettingStartedGuideMenu(){
        waitUntilElementIsClickable(gettingStartedGuideMenu,15);
        gettingStartedGuideMenu.click();
    }
}
