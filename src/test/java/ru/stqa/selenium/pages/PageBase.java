package ru.stqa.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class PageBase {
    WebDriver driver;

    public PageBase(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitle(){
        return driver.getTitle();
    }

    public void waitUntilElementIsClickable(By locator, int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions
                    .elementToBeClickable(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilElementIsClickable(WebElement element, int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions
                    .elementToBeClickable(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void waitUntilAttributeValueIs(By locator, String attribute, String value, int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions
                    .attributeToBe(locator, attribute, value));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilAttributeValueIs(WebElement element, String attribute, String value, int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions
                    .attributeToBe(element, attribute, value));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilTextValueIs(WebElement element, String text, int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions
                    .textToBePresentInElement(element, text));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilElementIsVisible(By locator, int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions
                    .visibilityOfElementLocated(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilElementIsVisible(WebElement element, int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions
                    .visibilityOf(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilElementIsNotVisible(By locator, int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions
                    .invisibilityOfElementLocated(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilAllElementsAreVisible(By locator, int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions
                    .visibilityOfAllElementsLocatedBy(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilAllElementsAreVisible(List<WebElement> elementList, int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions
                    .visibilityOfAllElements(elementList));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilFrameIsLoadedAndSwitchToIt(WebElement frame, int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions
                    .frameToBeAvailableAndSwitchToIt(frame));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilNumberOfWindows(int number, int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions
                    .numberOfWindowsToBe(number));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getAnotherWindowHandle(String mainHandle) {
        Set<String> setHandles = driver.getWindowHandles();
        String anotherHandle = "";
        for (String handle : setHandles) {
            if (!handle.equals(mainHandle)) anotherHandle = handle;

        }
        return anotherHandle;
    }

    public void switchToWindow(String handle) {
        driver.switchTo().window(handle);
    }

    public void fillField(WebElement textField, String value) {
        textField.click();
        textField.clear();
        textField.sendKeys(value);
    }

}
