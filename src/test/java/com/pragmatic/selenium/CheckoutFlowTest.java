package com.pragmatic.selenium;

import net.bytebuddy.asm.Advice;
import net.datafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutFlowTest {

        WebDriver webDriver;


@BeforeMethod
        public void setUpMethod() {
            webDriver = new ChromeDriver();
            webDriver.get("https://www.saucedemo.com/");
        }


@AfterMethod
        public void afterMethod()
        {
            if(webDriver!=null) {
                webDriver.quit();
            }
        }

@Test
public void checkoutFlow()
{

//set fake values
    Faker faker = new Faker();

    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String zipcode =faker.address().zipCode();

// login
    webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
    webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
    webDriver.findElement(By.id("login-button")).click();

// Get the price
    String firstItemPrice=webDriver.findElement(By.xpath("(//div[@class='inventory_item_price'])[1]")).getText();
    //Remove currency symbol
    String numericPrice = firstItemPrice.replaceAll("[^\\d.]", "");
    double value= Double.parseDouble(numericPrice);
    System.out.println(value);


//add products
    //webDriver.findElement(By.cssSelector("#add-to-cart-sauce-labs-backpack")).click();
    webDriver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
//Go to shopping cart
    webDriver.findElement(By.cssSelector("[data-test='shopping-cart-link']")).click();
//verify the page title as 'Your Cart'
    String PageName= webDriver.findElement(By.cssSelector("[data-test='title']")).getText();
    System.out.println("Heading of the cart page:" + PageName);
    Assert.assertEquals(PageName,"Your Cart");

//proceed to checkout
    webDriver.findElement(By.id("checkout")).click();
//verify that page is navigate to the checkout page(to read page header)
    String PageHeader=webDriver.findElement(By.cssSelector("[data-test='title']")).getText();
    System.out.println("Heading of the checkout page:"+ PageHeader);
    Assert.assertEquals(PageHeader,"Checkout: Your Information");

// fill checkout details
    webDriver.findElement(By.id("first-name")).sendKeys(firstName);
    webDriver.findElement(By.id("last-name")).sendKeys(lastName);
    webDriver.findElement(By.id("postal-code")).sendKeys(zipcode);
    webDriver.findElement(By.id("continue")).click();

//overview page
    String PageTitle=webDriver.findElement(By.cssSelector("[data-test='title']")).getText();
    System.out.println("Heading of the checkout overview page:"+ PageTitle);
    Assert.assertEquals(PageTitle,"Checkout: Overview");

// Verify the actual price and expected price are match
       //Get the price
    String ItemSubTotal= webDriver.findElement(By.cssSelector("[data-test='subtotal-label']")).getText();
    String ItemTax= webDriver.findElement(By.cssSelector("[data-test='tax-label']")).getText();
    String ItemTotal= webDriver.findElement(By.cssSelector("[data-test='total-label']")).getText();

    String ItemSPrice = ItemSubTotal.replaceAll("[^\\d.]", "");
    double ItemSTotal= Double.parseDouble(ItemSPrice);

    String TaxPrice = ItemTax.replaceAll("[^\\d.]", "");
    double Tax= Double.parseDouble(TaxPrice);


    String TPrice = ItemTotal.replaceAll("[^\\d.]", "");
    double ITotal= Double.parseDouble(TPrice);


    double expectedTotal= value+Tax;
    double roundedValue= Math.round(expectedTotal*100.0)/100.0;
    System.out.println("Expected full total with tax:"+roundedValue);

//check item subtotal & Total match with expected
    Assert.assertEquals(ITotal,roundedValue,"Total doesn't match");


//to finish the process
    webDriver.findElement(By.id("finish")).click();

//Verify checkout complete heading
    String Heading=webDriver.findElement(By.cssSelector("[data-test='title']")).getText();
    System.out.println("Heading of the order completion page:"+ Heading);
    Assert.assertEquals(Heading,"Checkout: Complete!");
//Verify thank you for your order message
    Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test='complete-header']")).getText(),"Thank you for your order!");
// Verify back button is visible
    WebElement backButton= webDriver.findElement(By.id("back-to-products"));
    Assert.assertTrue(backButton.isDisplayed(),"back button not visible");

//Verify back button  will navigate to product page
    webDriver.findElement(By.id("back-to-products")).click();
    Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test='title']")).getText(), "Products");
}




}
