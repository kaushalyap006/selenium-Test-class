package com.pragmatic.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;


public class HelloSeleniumTest {


@Test
    public void testHelloSelenium(){
WebDriver webDriver =new ChromeDriver();
webDriver.get("https://www.saucedemo.com/");
webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
webDriver.findElement(By.id("password")).sendKeys("secret_sauce");

webDriver.findElement(By.id("login-button")).click();

//Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test='title']")).getText(),"Products");
    webDriver.quit();

}}