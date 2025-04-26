package com.pragmatic.selenium.base;

import com.pragmatic.selenium.pages.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.*;

public class BaseTest {
    public WebDriver webDriver;
    private static final Logger logger = LogManager.getLogger(BaseTest.class);


    @BeforeMethod
    @Parameters({"browser", "headless"})
    public void browserSetUp(@Optional("chrome") String browser, @Optional("false")String headless ) {


            // if headless param is true, then is selected browser is chrome
            if (browser.equalsIgnoreCase("chrome")) {
                ChromeOptions options = new ChromeOptions();

                if (headless.equalsIgnoreCase("true")) {
                    options.addArguments("--headless");
                }
                webDriver = new ChromeDriver(options);

                // if selected browser is firefox
            } else if (browser.equalsIgnoreCase("firefox")) {
                FirefoxOptions options = new FirefoxOptions();
                if (headless.equalsIgnoreCase("true")) {
                    options.addArguments("--headless");
                }
                webDriver = new FirefoxDriver(options);
            } else {
                throw new IllegalArgumentException("Browser not supported" + browser);
            }
        webDriver.manage().window().maximize();

        webDriver.get("https://www.saucedemo.com/");

    }


    @AfterMethod
    public void afterMethod() {
        webDriver.quit();
    }

    public void setLogin(){
        SauceLoginPage loginPage = new SauceLoginPage(webDriver);
        loginPage.typeUsername("standard_user").typePassword("secret_sauce").clickLogin();
    }

    public void toCartPageFlow(){
        CommonMethod cm=new CommonMethod(webDriver);
        setLogin();
        cm.setCart();
    }

    public void toCheckOutInfoFlow(){
        CommonMethod cm=new CommonMethod(webDriver);
        setLogin();
        cm.setCart();
        cm.setNavigationToCheckOut();
    }

    public void toCheckoutOverviewFlow(){
        CommonMethod cm=new CommonMethod(webDriver);
        setLogin();
        cm.setCart();
        cm.setNavigationToCheckOut();
        cm.setCheckOutPage();
    }

    public void toCompleteFlow(){
        CommonMethod cm=new CommonMethod(webDriver);
        setLogin();
        cm.setCart();
        cm.setNavigationToCheckOut();
        cm.setCheckOutPage();
        cm.setOrderCompletion();
    }
}

