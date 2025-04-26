package com.pragmatic.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutInformationPage {
    WebDriver webDriver;


    By byPageHeading = By.cssSelector("[data-test='title']");
    By byErrorMessage = By.cssSelector("h3[data-test='error']");

    @FindBy(xpath = "//input[contains(@data-test,'firstName')]")
    WebElement FirstName;

    @FindBy(xpath = "//input[contains(@data-test,'lastName')]")
    WebElement LastName;

    @FindBy(xpath = "//input[contains(@data-test,'postalCode')]")
    WebElement PostalCode;

    @FindBy(xpath = "//input[@type='submit' and contains(@data-test, 'continue')]")
    WebElement ContinueButton;

    @FindBy(xpath = "//button[contains(@data-test, 'cancel')]")
    WebElement CancelButton;


    public CheckOutInformationPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);

    }


    public String getPageTitle() {
        return webDriver.findElement(byPageHeading).getText();
    }


    public CheckOutInformationPage typeFirstName(String firstName) {
        FirstName.sendKeys(firstName);
        return this;
    }


    public CheckOutInformationPage typeLastName(String lastName) {
        LastName.sendKeys(lastName);
        return this;
    }


    public CheckOutInformationPage typePostalCode(String postalCode) {
        PostalCode.sendKeys(postalCode);
        return this;
    }


    public String actualMessage() {
        return webDriver.findElement(byErrorMessage).getText();
    }


    // continue Button
    public boolean isContinueButtonEnabled() {
        return ContinueButton.isEnabled();
    }


    public String getContinueButtonText() {
        return ContinueButton.getText();
    }


    public void clickContinueButton() {
        ContinueButton.click();
    }


    // Cancel Button

    public boolean isCancelButtonEnabled() {
        return CancelButton.isEnabled();
    }

    public String getCancelButtonText() {
        return CancelButton.getText();
    }

    public void clickCancelButton() {
        CancelButton.click();
    }
}
