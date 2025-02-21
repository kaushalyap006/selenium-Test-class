package com.pragmatic.selenium.test;


import com.pragmatic.selenium.pages.CartPage;
import com.pragmatic.selenium.pages.SauceLoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartTest {
    WebDriver webDriver;
    private SauceLoginPage sauceLoginPage;

    @BeforeMethod
    public void beforeMethod(){
        webDriver=new ChromeDriver();
       // SauceLoginPage=new SauceLoginPage(webDriver);
        webDriver.manage().window().maximize();
        webDriver.get("https://www.saucedemo.com/");
    }

    @AfterMethod
    public void afterMethod(){
        webDriver.quit();
    }


    @Test
    public void getPageTitle(){
        CartPage cartpage=new CartPage(webDriver);

    }


}
