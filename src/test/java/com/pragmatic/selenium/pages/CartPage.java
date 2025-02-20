package com.pragmatic.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class CartPage {
    private final WebDriver webDriver;
    By byPageHeading = By.cssSelector("[data-test-'title']");

    public CartPage(WebDriver webDriver) {
        this.webDriver=webDriver;
    }

    public String getPageHeading(){
       return webDriver.findElement(byPageHeading).getText();
    }

    //Private WebDriver webDriver;

}
