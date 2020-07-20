package ru.stqa.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GettingStartedGuideHelper extends PageBase {
    @FindBy(xpath = "//a[@class = 'global-header-section-button'][contains(text(),'Go to Your Boards')]")
    WebElement goToYourBoardsUpperButton;

    public GettingStartedGuideHelper(WebDriver driver) {
        super(driver);
    }

    public GettingStartedGuideHelper switchToNewWindowAndWaitPageLoading(){
        log4j.info("-- Class GettingStartedGuideHelper, method switchToNewWindowAndWaitPageLoading() was started");
        log4j.info("Wait until number of windows is 2");
        waitUntilNumberOfWindows(2,30);
        log4j.info("Getting another window handle");
        String anotherHandle = getAnotherWindowHandle(driver.getWindowHandle());
        log4j.info("Switching to another window handle");
        switchToWindow(anotherHandle);
        log4j.info("Wait until button 'Go to Your Boards' is clickable");
        waitUntilElementIsClickable(goToYourBoardsUpperButton,20);
        return this;
    }




}
