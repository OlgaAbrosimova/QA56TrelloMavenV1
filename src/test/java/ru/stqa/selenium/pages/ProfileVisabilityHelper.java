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

    public void waitUntilPageIsLoaded(){
    waitUntilElementIsClickable(saveButton,30);
    waitUntilElementIsVisible(userNameAfterShtrudel,30);
    waitUntilElementIsVisible(userNameField,20);
    }

    public String getUpperRightMenuText(){
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
        return userNameAfterShtrudel.getText().replace("@","");
    }

    public String getUserNameText(){
        return userNameField.getAttribute("value");
    }

    private String createLocatorIconlist(String username) {
        return "//div[@title='" + username + " (" + username + ")']//span";
    }


}
