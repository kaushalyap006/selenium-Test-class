package com.pragmatic.selenium;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.openqa.selenium.By;

public class CrossBrowserTest {
WebDriver webDriver;




@Test
public void testGoogleChromeDriver()
{
    ChromeDriver webDriver = new ChromeDriver();
    webDriver.get("https://www.saucedemo.com/");
    webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
    webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
    webDriver.findElement(By.id("login-button")).click();
    Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test='title']")).getText(),"Products");
    webDriver.quit();
}


@Test
    public void testFireFoxDriver()
    {
        webDriver = new FirefoxDriver();
        webDriver.get("https://www.saucedemo.com/");
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test='title']")).getText(),"Products");
        webDriver.quit();
    }



@Test
    public void testEdgeDriver()
    {
        webDriver = new EdgeDriver();
        webDriver.get("https://www.saucedemo.com/");
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test='title']")).getText(),"Products");
        webDriver.quit();
    }

}