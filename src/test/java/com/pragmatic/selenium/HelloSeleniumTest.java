package com.pragmatic.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;


public class HelloSeleniumTest {
WebDriver webDriver;

@BeforeMethod
public void setup_broswer() {
    webDriver = new ChromeDriver();
    webDriver.get("https://www.saucedemo.com/");
}

@AfterMethod
public void after_test(){
    webDriver.quit();
}

@Test
    public void test_Hello_Selenium(){
    webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
webDriver.findElement(By.id("login-button")).click();
Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test='title']")).getText(),"Products");

}

@Test
public void test_blank_credentials(){

    webDriver.findElement(By.id("user-name")).clear();
    webDriver.findElement(By.id("password")).clear();
    webDriver.findElement(By.id("login-button")).click();
    Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test='error']")).getText(),"Epic sadface: Username is required");
}

@Test
    public void test_blank_password_validation(){

    webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
    webDriver.findElement(By.id("login-button")).click();
    Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test='error']")).getText(),"Epic sadface: Password is required");
}
    @Test
    public void test_blank_username_validation(){

        webDriver.findElement(By.id("user-name")).clear();
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test='error']")).getText(),"Epic sadface: Username is required");

    }

    @Test
    public void test_invalid_usename(){

        webDriver.findElement(By.id("user-name")).sendKeys("abcd");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test='error']")).getText(),"Epic sadface: Username and password do not match any user in this service");

    }

    @Test
    public void test_invalid_password(){

        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("sbcd");
        webDriver.findElement(By.id("login-button")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test='error']")).getText(),"Epic sadface: Username and password do not match any user in this service");

    }
    @Test
    public void test_invalid_usernameandpassword(){

        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("sbcd");
        webDriver.findElement(By.id("login-button")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test='error']")).getText(),"Epic sadface: Username and password do not match any user in this service");

    }
}