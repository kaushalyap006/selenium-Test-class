package com.pragmatic.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SauceLoginPage {

    private final WebDriver webDriver;
    By byUsename = By.id("user-name");
    By byPassword = By.id("password");
    By byLoginbutton = By.id("login-button");
    By byError = By.cssSelector("h3[data-test='error']");

    public SauceLoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }


    public SauceLoginPage typeUsername(String username) {
        webDriver.findElement(byUsename).sendKeys(username);
        return this;
    }


    public SauceLoginPage typePassword(String password) {
        webDriver.findElement((byPassword)).sendKeys(password);
        return this;
    }


    public void clickLogin() {
        webDriver.findElement((byLoginbutton)).click();
    }


    public String getError(){
        return webDriver.findElement(byError).getText();
    }
}
