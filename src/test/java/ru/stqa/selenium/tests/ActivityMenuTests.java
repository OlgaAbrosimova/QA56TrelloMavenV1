package ru.stqa.selenium.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.selenium.pages.*;
import util.DataProviders;


public class ActivityMenuTests extends TestBase {
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    CurrentBoardHelper qaHaifa56Page;
    UpperMenuHelper upperMenuPage;
    ActivityPageHelper activityPage;

    @BeforeMethod
    public void initTests() {
        loginPage = PageFactory.initElements(driver,LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver,BoardsPageHelper.class);
        qaHaifa56Page = new CurrentBoardHelper(driver,BOARD_TITLE);
        upperMenuPage = PageFactory.initElements(driver, UpperMenuHelper.class);
        activityPage = PageFactory.initElements(driver, ActivityPageHelper.class);

        loginPage.openLoginPage()
                 .loginAsAtlassian(LOGIN,PASSWORD);
        boardsPage.waitUntilPageIsLoaded();
        qaHaifa56Page.openCurrentBoard()
                     .waitUntilPageIsLoaded();
    }
    @Test
    public void addingNewListEventInActivity(){
        log4j.startTestCase("addingNewListEventInActivity");
        String listTitle = "Activity new";
        log4j.info("New list was creating: " + listTitle);
        qaHaifa56Page.createNewList(listTitle);
        log4j.info("Header member menu screen is loading");
        upperMenuPage.openMenuPage()
                     .waitUntilPageIsLoaded()
                     .openActivityPage();
        log4j.info("Wait until page 'Activity' is loaded");
        activityPage.waitUntilPageIsLoaded();

        Assert.assertTrue(activityPage.getLastActivityText().contains(" added list "+ listTitle + " to "),
                "The text in the last activity record doesn't correspond to event adding new list " + listTitle );
        log4j.endTestCase();
    }

    @Test(dataProviderClass = DataProviders.class,dataProvider = "DPaddingNewListEventInActivity")
    public void addingNewListEventInActivityDP1(String title){
        log4j.startTestCase("addingNewListEventInActivityDP1");
        log4j.info("New list was creating: " + title);
        qaHaifa56Page.createNewList(title);
        log4j.info("Header member menu screen is loading");
        upperMenuPage.openMenuPage()
                     .waitUntilPageIsLoaded()
                     .openActivityPage();
        log4j.info("Wait until page 'Activity' is loaded");
        activityPage.waitUntilPageIsLoaded();

        Assert.assertTrue(activityPage.getLastActivityText().contains(" added list "+ title + " to "),
                "The text in the last activity record doesn't correspond to event adding new list " + title );
        log4j.endTestCase();
    }
}
