package com.pragmatic.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SauceLoginPage {

    private final WebDriver webDriver;
    //define element @FindBy..
    @FindBy(id = "user-name")
    WebElement txtUsername;

    @FindBy(id = "password")
    WebElement txtPassword;

    @FindBy(id = "login-button")
    WebElement btnLogin;

    @FindBy(xpath = "//h3[@data-test='error']")
    WebElement errMessage;

//    By byUsename = By.id("user-name");
//    By byPassword = By.id("password");
//    By byLoginbutton = By.id("login-button");
//    By byError = By.cssSelector("h3[data-test='error']");

    public SauceLoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        //initializing the element
        PageFactory.initElements(webDriver, this);
    }


    public SauceLoginPage typeUsername(String username) {
        // webDriver.findElement(byUsename).sendKeys(username);
        txtUsername.sendKeys(username);
        return this;
    }


    public SauceLoginPage typePassword(String password) {
        //webDriver.findElement((byPassword)).sendKeys(password);
        txtPassword.sendKeys(password);
        return this;
    }


    public void clickLogin() {
        // webDriver.findElement((byLoginbutton)).click();
        btnLogin.click();
    }


    public String getError() {
        //return webDriver.findElement(byError).getText();
        return errMessage.getText();

    }
}
