package com.pragmatic.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;


public class HelloSeleniumTest {

@BeforeMethod
public void setupbroswer() {
    WebDriver webDriver = new ChromeDriver();
    webDriver.get("https://www.saucedemo.com/");
}

@AfterMethod
public void aftertest(){
    WebDriver webDriver = new ChromeDriver();
    webDriver.quit();
}

@Test
    public void testHelloSelenium(){
    WebDriver webDriver = new ChromeDriver();
    webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
webDriver.findElement(By.id("login-button")).click();
Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test='title']")).getText(),"Products");

}

@Test
public void testblankcredentials(){
    WebDriver webDriver =new ChromeDriver();
    webDriver.findElement(By.id("user-name")).sendKeys("");
    webDriver.findElement(By.id("password")).sendKeys("");
    webDriver.findElement(By.id("login-button")).click();
    Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test='error']")).getText(),"Epic sadface: Username is required");
}

@Test
    public void testblankpasswordvalidation(){
    WebDriver webDriver =new ChromeDriver();
    webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
    webDriver.findElement(By.id("password")).sendKeys("");
    webDriver.findElement(By.id("login-button")).click();
    Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test='error']")).getText(),"Epic sadface: Password is required");
}
    @Test
    public void testblankusernamevalidation(){
        WebDriver webDriver =new ChromeDriver();
        webDriver.findElement(By.id("user-name")).clear();
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test='error']")).getText(),"Epic sadface: Username is required");

    }

    @Test
    public void testinvalidusename(){
        WebDriver webDriver =new ChromeDriver();
        webDriver.findElement(By.id("user-name")).sendKeys("abcd");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test='error']")).getText(),"Epic sadface: Username and password do not match any user in this service");

    }

    @Test
    public void testinvalidpassword(){
        WebDriver webDriver =new ChromeDriver();
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("sbcd");
        webDriver.findElement(By.id("login-button")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test='error']")).getText(),"Epic sadface: Username and password do not match any user in this service");

    }
}