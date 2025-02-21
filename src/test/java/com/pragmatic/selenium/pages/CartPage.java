package com.pragmatic.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class CartPage {
    private final WebDriver webDriver;

    @FindBy(css = ("[data-test-'title']"))
    WebElement pageHeading;
    By byPageHeading = By.cssSelector("[data-test-'title']");

    public CartPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public String getPageHeading() {
        return pageHeading.getText();
    }

    //Private WebDriver webDriver;

}
