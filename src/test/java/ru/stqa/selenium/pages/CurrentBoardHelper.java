package ru.stqa.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class CurrentBoardHelper extends PageBase{
    @FindBy(xpath = "//span[@class='placeholder']")
    WebElement addListOption;

    @FindBy(xpath = "//input[@placeholder='Enter list title...']")
    WebElement addTitleField;

    @FindBy(xpath="//input[@type='submit']")
    WebElement addListButton;

    @FindBy(xpath = "//a[@class='icon-lg icon-close dark-hover js-cancel-edit']")
    WebElement cancelEditList;

    @FindBy(xpath = "//div[@class = 'list js-list-content']")
    List<WebElement> listLists;


    private String boardName;

    public CurrentBoardHelper(WebDriver driver, String boardName) {
        super(driver);
        this.boardName = boardName;
        PageFactory.initElements(driver,this);
    }
    public CurrentBoardHelper openCurrentBoard() {
        System.out.println("From openCurrentBoard: " + this.boardName);
        WebElement ourBoard = driver
                .findElement(By.xpath(boardLocator()));
        ourBoard.click();
        return this;
    }

    public CurrentBoardHelper waitUntilPageIsLoaded(){
        waitUntilElementIsVisible(By.xpath(boardTitleLocator()),15);
        waitUntilElementIsClickable(addListOption,15);
        return this;
    }

    public int getListsQuantity(){
        return listLists.size();
    }

    public CurrentBoardHelper createNewList(String title){
        this.pressCreateNewListButton();
        this.enterTitle(title);
        this.submitAddingList();
        this.cancelFromEditMode();
        return this;
    }
    public CurrentBoardHelper pressCreateNewListButton() {
        addListOption.click();
        waitUntilElementIsVisible(addTitleField,10);
        return this;
    }

    public CurrentBoardHelper enterTitle(String title) {
        addTitleField.click();
        addTitleField.sendKeys(title);
        waitUntilElementIsClickable(addListButton,10);
        return this;
    }

    public CurrentBoardHelper submitAddingList() {
        addListButton.click();
        return this;
    }

    public CurrentBoardHelper cancelFromEditMode() {
        cancelEditList.click();
        return this;
    }

    public boolean existsList() {
        Boolean existsList = false;
        if (driver.findElement(By
                .xpath("//span[@class='placeholder']")).getText().contains("another"))
        {
            existsList = true;
        }
        return existsList;
    }

    public int receiveQuantityOfCards() {
        return driver.findElements(By.cssSelector("a.list-card")).size();
    }

    public CurrentBoardHelper pressAddCardButton(){
        //--- Define two possible buttons for adding new card and click on the displayed one---
        WebElement addCardButton = driver
                .findElement(By.cssSelector("span.js-add-a-card"));
        WebElement addAnotherCardButton = driver
                .findElement(By.cssSelector("span.js-add-another-card"));
        if (addCardButton.isDisplayed()) {
            addCardButton.click();
        }
        else addAnotherCardButton.click();
        return this;
    }

    public CurrentBoardHelper enterTextToCard(String test_card) {
        waitUntilElementIsVisible(By.cssSelector("textarea.list-card-composer-textarea"),30);
        WebElement textCurrentCard = driver.findElement(By.cssSelector("textarea.list-card-composer-textarea"));
        textCurrentCard.click();
        textCurrentCard.sendKeys(test_card);
        return this;
    }

    public CurrentBoardHelper submitAddingCard() {
        WebElement submitCardButton = driver.findElement(By.xpath("//input[@type='submit'][@value = 'Add Card']"));
        submitCardButton.click();
        return this;
    }

    public CurrentBoardHelper cancelEditCardMode() {
        waitUntilElementIsVisible(By.cssSelector("div.card-composer a.icon-close"),10);
        WebElement cancelEditCardButton = driver.findElement(By.cssSelector("div.card-composer a.icon-close"));
        cancelEditCardButton.click();
        waitUntilElementIsNotVisible(By.cssSelector("textarea.list-card-composer-textarea"),15);
        //waitUntilElementIsNotVisible(By.cssSelector("div.card-composer a.icon-close"),15);
        return this;
    }

    private String boardLocator() {
        System.out.println("From boardLocator: " + this.boardName);
        return "//div[@title = '" + boardName + "']/../..";
    }

    private String boardTitleLocator(){
        return "//span[contains(text(),'" + boardName + "')]";
    }
}
