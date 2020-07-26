package ru.stqa.selenium.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.selenium.pages.HomePageHelper;

public class HomePageTests extends TestBase {
    HomePageHelper homePage;

    @BeforeMethod(alwaysRun = true)
    public void initTests(){
        homePage = PageFactory.initElements(driver,HomePageHelper.class);
    }
    @Test(groups = {"regression"})
    public void verifyFooterByCoordinates(){
        homePage.scrollDown(0,10000);
        Assert.assertEquals("About", homePage.getAboutFooter());
    }
    @Test
    public void verifyFooterToViewElement(){
        homePage.scrollDownToViewFooter();
        Assert.assertEquals("About", homePage.getAboutFooter());
    }

}
