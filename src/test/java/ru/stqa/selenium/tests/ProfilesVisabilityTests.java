package ru.stqa.selenium.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.selenium.pages.*;


public class ProfilesVisabilityTests extends TestBase {
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    UpperMenuHelper upperMenuPage;
    ProfileVisabilityHelper profileVisabilityPage;

    @BeforeMethod
    public void initTests() throws InterruptedException {
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver, BoardsPageHelper.class);
        upperMenuPage = PageFactory.initElements(driver, UpperMenuHelper.class);
        profileVisabilityPage = PageFactory.initElements(driver, ProfileVisabilityHelper.class);

        loginPage.openLoginPage()
                 .loginAsAtlassian(LOGIN,PASSWORD);
        boardsPage.waitUntilPageIsLoaded();
        upperMenuPage.openMenuPage()
                     .waitUntilPageIsLoaded()
                     .openProfileVisabilityScreen();
        profileVisabilityPage.waitUntilPageIsLoaded();/*
        //---- Open Up_Right Menu ----
        WebElement upRightMenu = driver.findElement(By.xpath("//button[@data-test-id = 'header-member-menu-button']"));
        upRightMenu.click();
        waitUntilElementIsVisible(By.xpath("//a[@data-test-id = 'header-member-menu-profile']"),10);

        //---- Open ProfileVisability Menu ----
        WebElement profileVisabilityMenu = driver
                .findElement(By.xpath("//a[@data-test-id = 'header-member-menu-profile']"));
        profileVisabilityMenu.click();
        waitUntilAllElementsAreVisible(By.xpath("//button[@data-test-id = 'header-member-menu-button']"),20);
        waitUntilElementIsClickable(By.xpath("//button[contains(text(),'Save')]"),10);*/
    }

    @Test
    public void lettersIconTest(){
        /*
        //--- Receive Upper Right Menu element---
        WebElement upRightMenu = driver.findElement(By.xpath("//button[@data-test-id = 'header-member-menu-button']"));
        WebElement upRightMenuText = upRightMenu.findElement(By.xpath(".//span"));
        //--- Receive list of necessary icons ---
        List<WebElement> iconsList = driver.findElements(By.xpath(createLocatorIconlist(USERNAME)));
        int counter = 0;
        for(WebElement element: iconsList)
            if (element.getText().equals(upRightMenuText.getText())) counter++;*/
        log4j.startTestCase("lettersIconTest");
        log4j.info("Verify list icons with username: " + USERNAME);
        Assert.assertTrue(profileVisabilityPage.verifyListIcons(USERNAME), "The text on the upper right icon and on the icon on profile is not the same");
        log4j.endTestCase();
    }

    @Test
    public void userNameDisplayingTest(){
/*
        //--- Receive UserName after shtrudel without username value
        WebElement userNameAfterShtrudel = driver.findElement(By.xpath("//span[contains(text(),'@')]"));

        //--- Receive UserName from user name field
        WebElement userNameField = driver.findElement(By.xpath("//input[@name='username']"));
        System.out.println("userNameAfterShtrudel: " + userNameAfterShtrudel.getText());
        System.out.println("userNameField: " + userNameField.getAttribute("value"));*/
        log4j.startTestCase("userNameDisplayingTest");
        log4j.info("Getting text of user name after shtrudel");
        Assert.assertEquals(profileVisabilityPage.getUserNameAfterShtrudelText(), profileVisabilityPage.getUserNameText(),
                "The text of userName and userNameAfterShtrudel is not the same ");
        log4j.endTestCase();
    }


}
