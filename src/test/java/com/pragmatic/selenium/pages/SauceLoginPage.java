package com.pragmatic.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SauceLoginPage {

    private final WebDriver webDriver;

    @FindBy(id = "user-name")
    WebElement txtUsername;

    @FindBy(id = "password")
    WebElement txtPassword;

    @FindBy(id = "login-button")
    WebElement btnLogin;

    @FindBy(xpath = "//h3[@data-test='error']")
    WebElement errMessage;


    public SauceLoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        //initializing the element
        PageFactory.initElements(webDriver, this);
    }


    public SauceLoginPage typeUsername(String username) {
        txtUsername.sendKeys(username);
        return this;
    }


    public SauceLoginPage typePassword(String password) {
        txtPassword.sendKeys(password);
        return this;
    }


    public void clickLogin() {
        btnLogin.click();
    }


    public String getError() {
        return errMessage.getText();

    }
}
