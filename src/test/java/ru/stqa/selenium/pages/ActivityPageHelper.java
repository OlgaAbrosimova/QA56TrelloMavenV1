package ru.stqa.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ActivityPageHelper extends PageBase {
    @FindBy(xpath = "//div[@class = 'phenom-desc']")
    List<WebElement> activityRecordsList;

    public ActivityPageHelper(WebDriver driver) {
        super(driver);
    }

    public ActivityPageHelper waitUntilPageIsLoaded(){
        log4j.info("-- Class ActivityPageHelper, method waitUntilPageIsLoaded() was started");
        log4j.info("Wait until activity records list are visible");
        waitUntilAllElementsAreVisible(activityRecordsList,30);
        return this;
    }

    public String getLastActivityText() {
        return activityRecordsList.get(0).getText();
    }
}
