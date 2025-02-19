package com.pragmatic.selenium.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class SauceLoginSteps {
    private WebDriver webDriver;


    @Before
    public void before() {
        webDriver = new ChromeDriver();
    }

    @After
    public void after() {
        webDriver.quit();
    }

    @Given("user is in the login page")
    public void userIsInTheLoginPage() {
        webDriver.get("https://www.saucedemo.com/");
    }

    @When("user provide valid credentials")
    public void userProvideValidCredentials() {
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();
    }

    @Then("the user should be redirected to the product inventory page")
    public void theUserShouldBeRedirectedToTheProductInventoryPage() {
        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test='title']")).getText(), "Products");

    }

    @Given("user has accessed the login page")
    public void userHasAccessedTheLoginPage() {
        webDriver.get("https://www.saucedemo.com/");
    }

    @When("user provide invalid credentials {string}, {string}")
    public void userProvideInvalidCredentials(String arg0, String arg1) {
        webDriver.findElement(By.id("user-name")).sendKeys("username");
        webDriver.findElement(By.id("password")).sendKeys("password");
        webDriver.findElement(By.id("login-button")).click();
    }

    @Then("the user should be see error message{string}")
    public void theUserShouldBeSeeErrorMessage(String expectedErrorMessage) {
        Assert.assertEquals(webDriver.findElement(By.cssSelector("h3[data-test='error']")).getText(), expectedErrorMessage);
    }
}
