package com.pragmatic.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class completePage {
    WebDriver webDriver;

    By byPageHeading = By.cssSelector("[data-test='title']");
    By byThankYouMessage = By.cssSelector("[data-test='complete-header']");
    By byCompleteText = By.cssSelector("[data-test='complete-text']");
    By byHomeButton = By.cssSelector("[data-test='back-to-products']");


    public completePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public String getCompletePageHeading() {
        return webDriver.findElement(byPageHeading).getText();
    }

    public String getThankYouMessage() {
        return webDriver.findElement(byThankYouMessage).getText();
    }

    public String getCompleteText() {
        return webDriver.findElement(byCompleteText).getText();
    }

    public String getHomeButtonText() {
        return webDriver.findElement(byHomeButton).getText();
    }

    public boolean checkHomeButtonIsEnable() {
        return webDriver.findElement(byHomeButton).isEnabled();
    }

    public void clickHomeButton() {
        webDriver.findElement(byHomeButton).click();
    }
}
