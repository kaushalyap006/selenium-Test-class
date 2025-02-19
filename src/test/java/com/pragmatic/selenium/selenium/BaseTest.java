package com.pragmatic.selenium.selenium;

import net.datafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import javax.swing.text.html.Option;
import java.lang.reflect.Method;
import java.time.Duration;

public class BaseTest {
    WebDriver webDriver;
    Faker faker = new Faker();

    @BeforeMethod
    @Parameters({"browser", "headless"})
    public void setup_browser(Method method, String browser, String headless) {
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
        webDriver.get("https://www.saucedemo.com/");

// login method is called if test method is neither testInvalidUserLogin or testValidUser
        if (!method.getName().equals("testInvalidUserLogin") && !method.getName().equals("testValidUser")) {
            login("standard_user", "secret_sauce");
        }
    }

    @AfterMethod
    public void after_Test() {
        webDriver.quit();
    }

    public WebElement waitForElement(By locator){
        //time out duration 10s
        Duration duration=Duration.ofSeconds(10);
        // Create a WebDriverWait instance with default timeout
        WebDriverWait wait=new WebDriverWait(webDriver,duration);
        //wait until element is visible
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void login(String username, String password) {
        webDriver.findElement(By.id("user-name")).sendKeys(username);
        webDriver.findElement(By.id("password")).sendKeys(password);
        webDriver.findElement(By.id("login-button")).click();
    }

    public String generateFirstName() {
        return faker.name().firstName();
    }

    public String generateLastName() {
        return faker.name().lastName();
    }

    public String generatePostalCode() {
        return faker.address().postcode();
    }

}