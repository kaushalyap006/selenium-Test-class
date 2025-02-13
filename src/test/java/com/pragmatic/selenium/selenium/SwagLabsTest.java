package com.pragmatic.selenium.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;


public class SwagLabsTest extends BaseTest {
    //WebDriver webDriver;


    @Test(dataProvider = "Invalid_Credentials", dataProviderClass = DataProviderSauceLabs.class)
    public void testInvalidUserLogin(String username, String password, String expectedMessage) {
        webDriver.findElement(By.id("user-name")).sendKeys(username);
        webDriver.findElement(By.id("password")).sendKeys(password);
        Button button=new Button(webDriver.findElement(By.id("login-button")));
        button.click();
        //webDriver.findElement(By.id("login-button")).click();
        String errorMessage = webDriver.findElement(By.cssSelector("h3[data-test='error']")).getText();
        Assert.assertEquals(errorMessage, expectedMessage, "Error message is incorrect");
    }


    @Test(dataProvider = "valid_Credentials", dataProviderClass = DataProviderSauceLabs.class)
    public void testValidUser(String username, String password) {

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10), Duration.ofMillis(50));
        //Wait to visible username filed
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));

        webDriver.findElement(By.id("user-name")).sendKeys(username);
        webDriver.findElement(By.id("password")).sendKeys(password);
        webDriver.findElement(By.id("login-button")).click();

        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test='title']")).getText(), "Products", "not redirected");
    }


    @Test
    public void testAllProductDetails() {
        String[][] expectedProduct = {
                {"Sauce Labs Backpack", "$29.99", "inventory-item-sauce-labs-backpack-img"},
                {"Sauce Labs Bike Light", "$9.99", "inventory-item-sauce-labs-bike-light-img"},
                {"Sauce Labs Bolt T-Shirt", "$15.99", "inventory-item-sauce-labs-bolt-t-shirt-img"},
                {"Sauce Labs Fleece Jacket", "$49.99", "inventory-item-sauce-labs-fleece-jacket-img"},
                {"Sauce Labs Onesie", "$7.99", "inventory-item-sauce-labs-onesie-img"},
                {"Test.allTheThings() T-Shirt (Red)", "$15.99", "inventory-item-test.allthethings()-t-shirt-(red)-img"}

        };

        //Get all product elements
        List<WebElement> productElements = webDriver.findElements(By.cssSelector("[data-test= inventory-item]"));
        //verify product details
        for (int i = 0; i < productElements.size(); i++) {
            WebElement product = productElements.get(i);
            //Get actual product details
            String actualName = product.findElement(By.className("inventory_item_name")).getText();
            String actualPrice = product.findElement(By.className("inventory_item_price")).getText();
            //String actualImage = product.findElement(By.className("inventory_item_img")).getText();
            //Get the expected values
            String expectedName = expectedProduct[i][0];
            String expectedPrice = expectedProduct[i][1];
            //String expectedImage= expectedProduct[i][2];
            //verify item name, price, image
            Assert.assertEquals(actualName, expectedName, "product name mismatch");
            Assert.assertEquals(actualPrice, expectedPrice, "product price mismatch");
            //Assert.assertEquals(actualImage,expectedImage,"product image mismatch");
        }
    }


    // Test Case 2.2: Verify sorting functionality for:
    // - Price (low to high and high to low).
    // - Name (A to Z and Z to A).
    @Test
    public void sorting() {
        // Test various sorting options
        testSelectSortingOption("za", "[data-test='item-3-title-link']", "Test.allTheThings() T-Shirt (Red)");
        testSelectSortingOption("lohi", "[data-test='item-2-title-link']", "Sauce Labs Onesie");
        testSelectSortingOption("hilo", "[data-test='item-5-title-link']", "Sauce Labs Fleece Jacket");
        testSelectSortingOption("az", "[data-test='item-4-title-link']", "Sauce Labs Backpack");
    }

    public void testSelectSortingOption(String value, String itemSelector, String eTitle) {
        // Locate the dropdown element and click to open
        WebElement dropdown = webDriver.findElement(By.cssSelector("[data-test='product-sort-container']"));
        dropdown.click();

        // Select the sorting option based on the value provided
        Select select = new Select(dropdown);
        select.selectByValue(value);

        // Wait for the page to update with sorted results
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(itemSelector)));

        // Get the title of the first product after sorting
        String title = webDriver.findElement(By.cssSelector(itemSelector)).getText();

        // Assert that the title matches the expected title passed as parameter
        Assert.assertEquals(title, eTitle, "Product title does not match the expected one.");
       // System.out.println(title);
    }


    //Test Case 2.3: Verify the product details page when clicking on a product.
    @Test
    public void testProductsData() {

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10), Duration.ofMillis(50));

        webDriver.findElement(By.cssSelector("#add-to-cart-sauce-labs-backpack")).click();
        webDriver.findElement(By.cssSelector("#add-to-cart-sauce-labs-bike-light")).click();

        String btnName = webDriver.findElement(By.id("remove-sauce-labs-backpack")).getText();
        // Verify that 'Add to cart' button text change to 'Remove'
        Assert.assertEquals(btnName, "Remove");
        // Test Case 2.6: Verify the product count on the cart icon matches the number of added products.
        String ItemNo = webDriver.findElement(By.cssSelector("[data-test='shopping-cart-badge']")).getText();
        Assert.assertEquals(ItemNo, "2");
        //System.out.println(ItemNo);
        //Verify remove button functionality
        webDriver.findElement(By.cssSelector("#remove-sauce-labs-backpack")).click();
        String btName = webDriver.findElement(By.cssSelector("#add-to-cart-sauce-labs-backpack")).getText();
        Assert.assertEquals(btName, "Add to cart");
        String ItemN = webDriver.findElement(By.cssSelector("[data-test='shopping-cart-badge']")).getText();
        Assert.assertEquals(ItemN, "1");
    }


    //Test Case 3.1:Verify that the correct products are displayed in the cart after adding them from the product listing page.
    @Test
    public void cartPage() {

        clickShoppingCart();
        // Verify to redirect cart page
        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test='title']")).getText(), "Your Cart");
        // Get the product details on cart page
        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test='item-4-title-link']")).getText(), "Sauce Labs Backpack");
        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test='item-0-title-link']")).getText(), "Sauce Labs Bike Light");
        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test='item-1-title-link']")).getText(), "Sauce Labs Bolt T-Shirt");
        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test='item-5-title-link']")).getText(), "Sauce Labs Fleece Jacket");

        //Test Case 3.2: Verify that removing a product from the cart updates the cart count and removes it from the cart page.
        webDriver.findElement(By.cssSelector("[data-test='remove-sauce-labs-backpack']")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test='shopping-cart-badge']")).getText(), "3");

        //Test Case 3.3: Verify the "Continue Shopping" button navigates back to the product listing page.
        webDriver.findElement(By.cssSelector("[data-test='continue-shopping']")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test='title']")).getText(), "Products");
    }


    @Test
    public void checkOutInfoPage() {
        clickShoppingCart();
        //Test Case 3.4: Verify the "Checkout" button navigates to the checkout page.
        webDriver.findElement(By.cssSelector("[data-test='checkout']")).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test='title']")).getText(), "Checkout: Your Information");

        // Test Case 4.2: Verify entering valid information on the checkout information page allows proceeding to the next step.

        checkRequiredFiled("[data-test='continue']", " First Name is required", "[data-test='firstName']", generateFirstName());
        checkRequiredFiled("[data-test='continue']", " Last Name is required", "[data-test='lastName']", generateLastName());
        checkRequiredFiled("[data-test='continue']", " Postal Code is required", "[data-test='postalCode']", generatePostalCode());

        // After filling all fields, continue and verify the checkout overview page.
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test='title']")).getText(), "Checkout: Overview");
    }


    // check validations for first name/last name/ postal code
    private void checkRequiredFiled(String continueButton, String expectedErrorMessage, String fieldSelector, String fieldValue) {
        // click continue and verify error message
        webDriver.findElement(By.cssSelector(continueButton)).click();
        Assert.assertEquals(webDriver.findElement(By.cssSelector("h3[data-test='error']")).getText(), "Error:" + expectedErrorMessage);
        // Enter data into the field
        webDriver.findElement(By.cssSelector(fieldSelector)).sendKeys(fieldValue);
        // Close the error and click continue again
        webDriver.findElement(By.cssSelector("[data-test='error-button']")).click();
        webDriver.findElement(By.cssSelector(continueButton)).click();
    }


    public void clickShoppingCart() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        //add products to the cart
        webDriver.findElement(By.cssSelector("[data-test= 'add-to-cart-sauce-labs-backpack']")).click();
        webDriver.findElement(By.cssSelector("[data-test='add-to-cart-sauce-labs-bike-light']")).click();
        webDriver.findElement(By.cssSelector("[data-test='add-to-cart-sauce-labs-bolt-t-shirt']")).click();
        webDriver.findElement(By.cssSelector("[data-test='add-to-cart-sauce-labs-fleece-jacket']")).click();
        //click on shopping cart icon
        webDriver.findElement(By.cssSelector("[data-test='shopping-cart-link']")).click();
    }


    @Test
    public void orderSummary() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

        //get the price of the product
        double backPackPrice = getPriceOfProduct("Sauce Labs Backpack");
        double bikeLightPrice = getPriceOfProduct("Sauce Labs Bike Light");
        double boldTPrice = getPriceOfProduct("Sauce Labs Bolt T-Shirt");
        double fleeceJacketPrice = getPriceOfProduct("Sauce Labs Fleece Jacket");

       //System.out.println(boldTPrice + fleeceJacketPrice + backPackPrice + bikeLightPrice);
        clickShoppingCart();
        webDriver.findElement(By.cssSelector("[data-test='checkout']")).click();

        webDriver.findElement(By.cssSelector("[data-test='firstName']")).sendKeys(generateFirstName());
        webDriver.findElement(By.cssSelector("[data-test='lastName']")).sendKeys(generateLastName());
        webDriver.findElement(By.cssSelector("[data-test='postalCode']")).sendKeys(generatePostalCode());
        webDriver.findElement(By.cssSelector("[data-test='continue']")).click();

        //Get the price
        String ItemSubTotal = webDriver.findElement(By.cssSelector("[data-test='subtotal-label']")).getText();
        String ItemTax = webDriver.findElement(By.cssSelector("[data-test='tax-label']")).getText();
        String ItemTotal = webDriver.findElement(By.cssSelector("[data-test='total-label']")).getText();

        // Extract the numerical values from the text
        double itemSubTotal = extractAmount(ItemSubTotal);
       // System.out.println("item total:" + itemSubTotal);
        double tax = extractAmount(ItemTax);
        //System.out.println("Tax:" + tax);
        double total = extractAmount(ItemTotal);
        //System.out.println("full total" + total);

        // Calculate the expected total
        double expectedItemSubTotal = backPackPrice + bikeLightPrice + boldTPrice + fleeceJacketPrice;
        //System.out.println("Expected item total:" + expectedItemSubTotal);
        double expectedTotal = expectedItemSubTotal + tax;
        double roundedValue = Math.round(expectedTotal * 100.0) / 100.0;
        //System.out.println("Expected full total with tax:" + roundedValue);

        Assert.assertEquals(itemSubTotal, expectedItemSubTotal, "SubTotal doesnt match");
        Assert.assertEquals(total, roundedValue, "Total doesn't match");
    }


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

        /*
4Checkout Process

    Test Case 4.3: Verify the order summary page displays the correct list of items, prices, and total.
    Test Case 4.4: Verify the "Finish" button completes the order and displays the confirmation message.
    Test Case 4.5: Verify the "Cancel" button navigates back to the cart page.*/
}


































