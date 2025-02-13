package com.pragmatic.selenium.selenium;


import net.datafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.List;


public class CheckoutMultiProductTest {
    WebDriver webDriver;


    @BeforeMethod
    public void setUpMethod() {
        webDriver = new ChromeDriver();
        webDriver.get("https://www.saucedemo.com/");
        loginUser();
    }


    @AfterMethod
    public void afterMethod() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }


    //Add multiple products to the cart
    @Test
    public void MultipleCheckOutTest() {


        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String postcode = faker.address().postcode();

        //Capture the price
        double backpackPrice = getPriceOfProduct("Sauce Labs Backpack");
        System.out.println(backpackPrice);
        double onesiePrice = getPriceOfProduct("Sauce Labs Onesie");
        System.out.println(onesiePrice);

        //add products
        webDriver.findElement(By.cssSelector("#add-to-cart-sauce-labs-backpack")).click();
        webDriver.findElement(By.id("add-to-cart-sauce-labs-onesie")).click();


        //Go to shopping cart
        webDriver.findElement(By.cssSelector("[data-test='shopping-cart-link']")).click();

        //verify the page title as 'Your Cart'
        String PageName = webDriver.findElement(By.cssSelector("[data-test='title']")).getText();
        System.out.println("Heading of the cart page:" + PageName);
        Assert.assertEquals(PageName, "Your Cart");

        //proceed to checkout
        webDriver.findElement(By.id("checkout")).click();
        //verify that page is navigate to the checkout page(to read page header)
        String PageHeader = webDriver.findElement(By.cssSelector("[data-test='title']")).getText();
        System.out.println("Heading of the checkout page:" + PageHeader);
        Assert.assertEquals(PageHeader, "Checkout: Your Information");

        // fill checkout details

        webDriver.findElement(By.id("first-name")).sendKeys(firstName);
        webDriver.findElement(By.id("last-name")).sendKeys(lastName);
        webDriver.findElement(By.id("postal-code")).sendKeys(postcode);
        webDriver.findElement(By.id("continue")).click();

        //overview page
        String PageTitle = webDriver.findElement(By.cssSelector("[data-test='title']")).getText();
        System.out.println("Heading of the checkout overview page:" + PageTitle);
        Assert.assertEquals(PageTitle, "Checkout: Overview");

        //Get the price
        String ItemSubTotal = webDriver.findElement(By.cssSelector("[data-test='subtotal-label']")).getText();
        String ItemTax = webDriver.findElement(By.cssSelector("[data-test='tax-label']")).getText();
        String ItemTotal = webDriver.findElement(By.cssSelector("[data-test='total-label']")).getText();

        // Extract the numerical values from the text
        double itemSubTotal = extractAmount(ItemSubTotal);
        System.out.println("item total:" + itemSubTotal);
        double tax = extractAmount(ItemTax);
        System.out.println("Tax:" + tax);
        double total = extractAmount(ItemTotal);
        System.out.println("full total" + total);

        // Calculate the expected total
        double expectedItemSubTotal = backpackPrice + onesiePrice;
        System.out.println("Expected item total:" + expectedItemSubTotal);
        double expectedTotal = expectedItemSubTotal + tax;
        double roundedValue = Math.round(expectedTotal * 100.0) / 100.0;
        System.out.println("Expected full total with tax:" + roundedValue);

        //check item subtotal & Total match with expected
        Assert.assertEquals(itemSubTotal, expectedItemSubTotal, "SubTotal doesnt match");
        Assert.assertEquals(total, roundedValue, "Total doesn't match");
        //to finish the process
        webDriver.findElement(By.id("finish")).click();

        //Check confirmation page

        //Verify checkout complete heading
        String Heading = webDriver.findElement(By.cssSelector("[data-test='title']")).getText();
        System.out.println("Heading of the order completion page:" + Heading);
        Assert.assertEquals(Heading, "Checkout: Complete!");
        //Verify thank you for your order message
        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test='complete-header']")).getText(), "Thank you for your order!");

    }


    //Login user
    private void loginUser() {
        //Enter credentials & click on login CTA
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();
        // Verify that the Products page is loaded
        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test='title']")).getText(), "Products");
    }

    // Helper method to extract the price of a product from the product list
    private double getPriceOfProduct(String productName) {
        List<WebElement> productList = webDriver.findElements(By.cssSelector(".inventory_item"));
        for (WebElement product : productList) {
            String productText = product.findElement(By.cssSelector(".inventory_item_name")).getText();
            if (productText.equals(productName)) {
                String priceText = product.findElement(By.cssSelector(".inventory_item_price")).getText();
                return extractAmount(priceText);
            }
        }
        return 0.0;
    }

    // Helper method to extract numeric value from the text (removes non-numeric characters)
    private double extractAmount(String text) {
        return Double.parseDouble(text.replaceAll("[^\\d.]", ""));
    }


}

