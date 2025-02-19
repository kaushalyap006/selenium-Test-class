package com.pragmatic.selenium.test;

import com.pragmatic.selenium.TestData.TestData;
import com.pragmatic.selenium.pages.ProductDetails;
import com.pragmatic.selenium.pages.ProductPage;
import com.pragmatic.selenium.pages.SauceLoginPage;
import io.cucumber.java.bs.A;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ProductTest {
    WebDriver webDriver;

    // Create a Logger instance
    private static final Logger logger = LogManager.getLogger(ProductTest.class);


    @BeforeMethod
    public void beforeMethod() {
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("https://www.saucedemo.com/");
        testLoginWithValidCredentials();
    }


    @AfterMethod
    public void afterMethod() {
        webDriver.quit();
    }


    private void testLoginWithValidCredentials() {
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.id("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();
    }


    //Verify page heading
    @Test
    public void checkPageHeading() {
        ProductPage productpage = new ProductPage(webDriver);
        String actualHeading = productpage.headingCheck();
        logger.debug("Actual title Name: " + actualHeading);
        Assert.assertEquals(actualHeading, "Products");
    }


    // Verify the product details page when clicking on a product.



        @Test(dataProvider = "test_product_details", dataProviderClass = TestData.class)
        public void testProductDetails(String expectedProductName, String expectedProductPrice) {

            // Initialize the product page object
            ProductPage productPage = new ProductPage(webDriver);
            // Log the start of the test
            logger.info("Starting test for Product Details");

            // Get the list of product details
            List<ProductDetails> productDetailsList = productPage.productDetails();

            // Ensure at least one product detail is present
            Assert.assertFalse(productDetailsList.isEmpty(), "No product details found!");

            for (ProductDetails actualProduct : productDetailsList) {

                // Assume the expected details correspond to the first product in the list for this example
                // Retrieve actual product details
                String actualProductName = actualProduct.getName();
                String actualProductPrice = actualProduct.getPrice();
                //String actualProductImage = actualProduct.getImage();

                // Log retrieved values
                //logger.debug("Retrieved - Name: " + actualProductName + ", Price: " + actualProductPrice);
                logger.debug("Expected - Name: " + expectedProductName + ", Price: " + expectedProductPrice);

                // Assertions to check product name, price, and image
                Assert.assertEquals(actualProductName, expectedProductName, "Product name doesn't match");
                Assert.assertEquals(actualProductPrice, expectedProductPrice, "Product price doesn't match");
                //Assert.assertEquals(actualProductImage, expectedProductImage, "Product image doesn't match");
            }
        }

        @Test
    public void shoppingCartItem() {
        ProductPage productPage = new ProductPage(webDriver);
        //productPage.backPack().
        String noItems = productPage.getNoOfItems();
        Assert.assertEquals(noItems, "5", "item count doesn't match");

    }

    @Test
    public void testButtonName() {
        ProductPage productPage = new ProductPage(webDriver);
        String btnName = productPage.btnNameChange();
        Assert.assertEquals(btnName, "Remove", "button name doesn't match");
    }

}
