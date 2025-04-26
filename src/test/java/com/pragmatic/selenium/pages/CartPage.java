package com.pragmatic.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class CartPage {
    private final WebDriver webDriver;

    By bypageHeading = By.cssSelector("[data-test='title']");

    @FindBy(xpath = "//button[contains(@data-test, 'remove')]")
    WebElement removeButton;

    @FindBy(xpath = "//button[contains(@data-test,'continue-shopping')]")
    WebElement continueButton;

    @FindBy(xpath = "//button[contains(@data-test,'checkout')]")
    WebElement checkOutButton;


    public CartPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);

    }


    public String getPageHeading() {
        return webDriver.findElement(bypageHeading).getText();
    }

    public void getRemoveButton() {
        removeButton.isEnabled();
    }


    //Continue Shopping Button

    public String getContinueButtonText() {
        return continueButton.getText();
    }

    public boolean checkButtonIsEnabled() {
        return continueButton.isEnabled();
    }

    public void clickContinueButton() {
        continueButton.click();
    }

    public boolean checkContinueButtonIsEnable() {
        return continueButton.isEnabled();

    }


    //Checkout page

    public boolean VerifyCheckOutButtonIsEnabled() {
        return checkOutButton.isEnabled();
    }

    public String getCheckoutButtonText() {
        return checkOutButton.getText();
    }

    public void clickCheckOutButton() {
        checkOutButton.click();
    }
}
