package com.pragmatic.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.openqa.selenium.By;

public class HeadlessTest {
    WebDriver webDriver;



    @Test
    public void testGoogleChromeHeadless()
    {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        webDriver =new ChromeDriver(options);
        webDriver.get("https://www.saucedemo.com/");
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test='title']")).getText(),"Products");
        webDriver.quit();
    }


    @Test
    public void testFirefoxBrowserHeadless()
    {
        FirefoxOptions options= new FirefoxOptions();
        options.addArguments("--headless");
        webDriver =new FirefoxDriver(options);
        webDriver.get("https://www.saucedemo.com/");
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test='title']")).getText(),"Products");
        webDriver.quit();
    }

    @Test
    public void testEdgeBrowserHeadless()
    {
        EdgeOptions options= new EdgeOptions();
        options.addArguments("--headless");
        webDriver =new EdgeDriver(options);
        webDriver.get("https://www.saucedemo.com/");
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test='title']")).getText(),"Products");
        webDriver.quit();
    }


}