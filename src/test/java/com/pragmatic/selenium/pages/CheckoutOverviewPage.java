package com.pragmatic.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutOverviewPage {
    WebDriver webDriver;


    @FindBy(xpath = "//button[contains(@data-test,'cancel')]")
    WebElement cancelButton;

    @FindBy(xpath = "//button[contains(@data-test,'finish')]")
    WebElement finishButton;

    By byPageHeading = By.cssSelector("[data-test='title']");
    By byTaxLabel = By.cssSelector("[data-test='tax-label']");
    By byActualTotalValueText = By.cssSelector("[data-test='total-label']");
    By byItemTotalText = By.cssSelector("[data-test='subtotal-label']");
    By byInventoryItemPrice = By.cssSelector("[data-test='inventory-item-price']");
    By byInventoryItemName = By.cssSelector("[data-test='inventory-item-name']");


    public CheckoutOverviewPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);

    }


    public String getInventoryItemName() {
        return webDriver.findElement(byInventoryItemName).getText();
    }


    public String getInventoryItemPrice() {
        return webDriver.findElement(byInventoryItemPrice).getText().trim().replaceAll("[^\\d.]", "");
    }


    public String getItemTotalText() {
        return webDriver.findElement(byItemTotalText).getText().trim().replaceAll("[^\\d.]", "");
    }


    public String getActualTotalValueText() {
        return webDriver.findElement(byActualTotalValueText).getText().trim().replaceAll("[^\\d.]", "");
    }


    public String getTaxText() {
        return webDriver.findElement(byTaxLabel).getText().trim().replaceAll("[^\\d.]", "");
    }


    public String getPageHeading() {
        return webDriver.findElement(byPageHeading).getText();
    }


    // Cancel Button
    public boolean checkCancelButtonIsEnabled() {
        return cancelButton.isEnabled();
    }

    public String getCancelButtonText() {
        return cancelButton.getText();
    }

    public void clickCancelButton() {
        cancelButton.click();
    }


    // Finish Button
    public boolean checkFinishButtonIsEnabled() {
        return finishButton.isEnabled();
    }

    public String getFinishButtonText() {
        return finishButton.getText();
    }

    public void clickFinishButton() {
        finishButton.click();
    }


}
