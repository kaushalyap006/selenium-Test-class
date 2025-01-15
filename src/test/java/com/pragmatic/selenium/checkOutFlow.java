package com.pragmatic.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

    public class checkOutFlow
    {
       WebDriver webDriver;

@BeforeSuite
    public void setWebDriver()
    {
        webDriver = new ChromeDriver();
        webDriver.get("https://www.saucedemo.com/");
    }

@AfterSuite
    public void afterTest()
    {
        if(webDriver!=null) {
            webDriver.quit();
        }
    }


//Login user
@Test(dataProvider = "login-credentials",dataProviderClass = DataProviderSauceLabs.class)
   public void loginUser(String username,String password)
   {
   //Enter credentials & click on login CTA
       webDriver.findElement(By.id("user-name")).sendKeys(username);
       webDriver.findElement(By.id("password")).sendKeys(password);
       webDriver.findElement(By.id("login-button")).click();
   // Verify that the Products page is loaded
       Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test='title']")).getText(), "Products");
   }



//Add to Cart via product Page
@Test(dependsOnMethods = {"loginUser"})
   public void addToCartFromProductPage()
   {
   // Click the "Add to Cart" button for the item
       webDriver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

   //Verify that shopping cart badge change to 1
       String CartBadge= webDriver.findElement(By.className("shopping_cart_badge")).getText();
       System.out.println("No of items added via product page:"+ CartBadge);
       Assert.assertEquals(CartBadge,"1");

   //Verify that 'Add to cart' button name change to Remove
       Assert.assertEquals(webDriver.findElement(By.id("remove-sauce-labs-backpack")).getText(),"Remove");
   }


//Add to cart via item Description page
@Test(dependsOnMethods = {"addToCartFromProductPage"})
   public void AddToCartViaItemPage()
   {

   // Click on Sauce Labs Bike Light (product page)
       webDriver.findElement(By.cssSelector("[data-test='item-0-title-link']")).click();

   //Verify 'back to product' heading to make sure page navigate to Item description page
       String Heading= webDriver.findElement(By.cssSelector("[data-test='back-to-products']")).getText();
       Assert.assertEquals(Heading,"Back to products");
       System.out.println("Heading of the item Description page:" +Heading);

   //Click on add to cart button
       webDriver.findElement(By.xpath("//button[@id='add-to-cart']")).click();

   //verify whether to add another item to the cart
       String NoOfItem= webDriver.findElement(By.cssSelector("[data-test='shopping-cart-badge']")).getText();
       System.out.println("Number of items :" + NoOfItem);
       Assert.assertEquals(NoOfItem,"2");

   //Verify that add to cart button change to Remove
       Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test='remove']")).getText(),"Remove");
   }



   //navigate to the checkout page
@Test(dependsOnMethods = {"AddToCartViaItemPage"})
   public void TestAddedItemsInCheckoutPage()
   {

   // Navigate to the checkout page
       webDriver.findElement(By.cssSelector("[data-test='shopping-cart-link']")).click();

   //verify the page title as 'Your Cart'
       String PageName= webDriver.findElement(By.cssSelector("[data-test='title']")).getText();
       System.out.println("Heading of the cart page:" + PageName);
       Assert.assertEquals(PageName,"Your Cart");


   //get the item name & price (item added via product page)
       String FirstItemName= webDriver.findElement(By.xpath("//*[@id='item_4_title_link']")).getText();
       System.out.println("Item name added via product page:"+ FirstItemName);
       String FirstItemPrice=webDriver.findElement(By.xpath("(//div[@class='inventory_item_price'])[1]")).getText();
       System.out.println("Item price:"+ FirstItemPrice);

   //Verify that item name is correct
       Assert.assertEquals(FirstItemName,"Sauce Labs Backpack");
   //Verify that item price is correct
       Assert.assertEquals(FirstItemPrice,"$29.99");


   //Get the item name & price(  Item added via Description Page)
       String SecondItemName= webDriver.findElement(By.xpath("//*[@id='item_0_title_link']")).getText();
       String SecondItemPrice=webDriver.findElement(By.xpath("(//div[@class='inventory_item_price'])[2]")).getText();
       System.out.println("Item name added via item description page:"+ SecondItemName);
       System.out.println("Item price:"+ SecondItemPrice);

   //Verify that item name is correct
       Assert.assertEquals(SecondItemName,"Sauce Labs Bike Light");
   //Verify that item price is correct
       Assert.assertEquals(SecondItemPrice,"$9.99");
   }


//Test to navigate checkout page
@Test(dependsOnMethods = {"TestAddedItemsInCheckoutPage"})
   public void TestCheckout()
   {
   //Click on checkout button
        webDriver.findElement(By.id("checkout")).click();
   //verify that page is navigate to the checkout page(to read page header)
        String PageHeader=webDriver.findElement(By.cssSelector("[data-test='title']")).getText();
        System.out.println("Heading of the checkout page:"+ PageHeader);
        Assert.assertEquals(PageHeader,"Checkout: Your Information");
    }


//to navigate back to 'your cart' page by clicking cancel CTA
@Test(dependsOnMethods = {"TestCheckout"})
    public void TestCancelButton(){
    //click on cancel button and navigate to your cart
        webDriver.findElement(By.id("cancel")).click();

    //verify the page title as 'Your Cart'
        Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test='title']")).getText(),"Your Cart");
    }



   //To navigate to the checkout: overview page
@Test(dependsOnMethods = {"TestCancelButton"})
    public void TestToNavigateOverviewPage(){
    //click on check out button in your cart page
        webDriver.findElement(By.id("checkout")).click();
    //Enter first name/lastname/postalcode
        webDriver.findElement(By.id("first-name")).sendKeys("Kaushalya");
        webDriver.findElement(By.id("last-name")).sendKeys("Perera");
        webDriver.findElement(By.id("postal-code")).sendKeys("307669");
    //click on the continue button
        webDriver.findElement(By.id("continue")).click();

    //verify that user has navigated to the checkout overview page
        String PageTitle=webDriver.findElement(By.cssSelector("[data-test='title']")).getText();
        System.out.println("Heading of the checkout overview page:"+ PageTitle);
        Assert.assertEquals(PageTitle,"Checkout: Overview");

    }



//Test checkout:overview page
@Test(dependsOnMethods = {"TestToNavigateOverviewPage"})
    public void TestOverviewPage()
    {
    //Verify that added items are displayed
        Assert.assertEquals(webDriver.findElement(By.xpath("(//div[@class='inventory_item_name'])[1]")).getText(),"Sauce Labs Backpack");
        Assert.assertEquals(webDriver.findElement(By.xpath("(//div[@class='inventory_item_price'])[1]")).getText(),"$29.99");

        Assert.assertEquals(webDriver.findElement(By.xpath("(//div[@class='inventory_item_name'])[2]")).getText(),"Sauce Labs Bike Light");
        Assert.assertEquals(webDriver.findElement(By.xpath("(//div[@class='inventory_item_price'])[2]")).getText(),"$9.99");

    //Verify order ID

        String OrderId=webDriver.findElement(By.cssSelector("[data-test='payment-info-value']")).getText();
        Assert.assertEquals(OrderId,"SauceCard #31337");
        System.out.println("Order id:"+OrderId);
    //Verify shipping information
        String ShippingInfo=webDriver.findElement(By.cssSelector("[data-test='shipping-info-value']")).getText();
        Assert.assertEquals(ShippingInfo,"Free Pony Express Delivery!");
        System.out.println("Shipping method:" +ShippingInfo);

    // Verify Item Total
        String ItemTotal= webDriver.findElement(By.cssSelector("[data-test='subtotal-label']")).getText();
        Assert.assertEquals(ItemTotal,"Item total: $39.98");

    }


//Test order completion page
@Test(dependsOnMethods = {"TestOverviewPage"})
    public void TestOrderCompletion()
    {
    //Click on finish button in Overview page
         webDriver.findElement(By.id("finish")).click();
    //Verify checkout complete heading
         String Heading=webDriver.findElement(By.cssSelector("[data-test='title']")).getText();
         System.out.println("Heading of the order completion page:"+ Heading);
         Assert.assertEquals(Heading,"Checkout: Complete!");
    //Verify thank you for your order message
         Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test='complete-header']")).getText(),"Thank you for your order!");
    //Verify back home button & will navigate to product page
         webDriver.findElement(By.id("back-to-products")).click();
         Assert.assertEquals(webDriver.findElement(By.cssSelector("[data-test='title']")).getText(), "Products");

   }
   }