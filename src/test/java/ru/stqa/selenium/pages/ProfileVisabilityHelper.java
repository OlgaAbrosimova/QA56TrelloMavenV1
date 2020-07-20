package ru.stqa.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProfileVisabilityHelper extends PageBase {
    @FindBy(xpath = "//button[contains(text(),'Save')]")
    WebElement saveButton;
    @FindBy(xpath = "//button[@data-test-id = 'header-member-menu-button']")
    WebElement upperRightMenu;
    @FindBy(xpath = "//span[contains(text(),'@')]")
    WebElement userNameAfterShtrudel;
    @FindBy(xpath = "//input[@name='username']")
    WebElement userNameField;

    public ProfileVisabilityHelper(WebDriver driver) {
        super(driver);
    }

    public ProfileVisabilityHelper waitUntilPageIsLoaded(){
        log4j.info("-- Class ProfileVisabilityHelper, method waitUntilPageIsLoaded() was started");
        log4j.info("Wait until button 'Save' is clickable");
        waitUntilElementIsClickable(saveButton,30);
        log4j.info("Wait until username after shtrudel is visible");
        waitUntilElementIsVisible(userNameAfterShtrudel,30);
        log4j.info("Wait until field username is visible");
        waitUntilElementIsVisible(userNameField,20);
        return this;
    }

    public String getUpperRightMenuText(){
        log4j.info("-- Class ProfileVisabilityHelper, method getUpperRightMenuText() was started");
        log4j.info("Getting text of button header member menu");
        return upperRightMenu.findElement(By.xpath(".//span")).getText();
    }

    public boolean verifyListIcons(String username){
        List<WebElement> iconsList = driver.findElements(By.xpath(createLocatorIconlist(username)));
        int counter = 0;
        for(WebElement element: iconsList)
            if (element.getText().equals(getUpperRightMenuText())) counter++;
        return counter==2;
    }

    public String getUserNameAfterShtrudelText(){
        log4j.info("-- Class ProfileVisabilityHelper, method getUserNameAfterShtrudelText() was started");
        log4j.info("Getting text of userName after shtrudel");
        return userNameAfterShtrudel.getText().replace("@","");
    }

    public String getUserNameText(){
        log4j.info("-- Class ProfileVisabilityHelper, method getUserNameText() was started");
        log4j.info("Getting value of field 'Username'");
        return userNameField.getAttribute("value");
    }

    private String createLocatorIconlist(String username) {
        return "//div[@title='" + username + " (" + username + ")']//span";
    }


}
