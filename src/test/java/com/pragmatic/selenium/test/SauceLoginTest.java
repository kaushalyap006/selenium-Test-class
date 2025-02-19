package com.pragmatic.selenium.test;

import com.pragmatic.selenium.TestData.TestData;
import com.pragmatic.selenium.pages.SauceLoginPage;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SauceLoginTest {

    private WebDriver webDriver;

    @BeforeMethod
    public void beforeMethod() {
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("https://www.saucedemo.com/");
    }

    @AfterMethod
    public void afterMethod() {
        webDriver.quit();
    }

    @Test
    public void testLoginWithValidCredentials() {
        SauceLoginPage loginPage = new SauceLoginPage(webDriver);
        loginPage.typeUsername("standard_user").typePassword("secret_sauce").clickLogin();

    }

    @Test(dataProvider = "user-credentials", dataProviderClass = TestData.class)
    public void testLoginWithBlankUsernameAndBlankPassword(String username, String password, String expectedError) {
        SauceLoginPage loginPage = new SauceLoginPage(webDriver);
        loginPage.typeUsername(username).typePassword(password).clickLogin();
        // loginPage.clickLogin();
        Assert.assertEquals(loginPage.getError(), expectedError);
    }


}
