package com.pragmatic.selenium.testng;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestNgExampleOne {

    @Test
    @Parameters({"browser","headless"})
    public void testMethod1( String browser, String headless)
    {
        System.out.println("Browser=" +browser+",headless=" +headless);
        System.out.println("TestNgExampleOne.testMethod1");
    }

    @Test
    public void testMethod2() {
        System.out.println("TestNgExampleOne.testMethod2");
    }

    @Test
    public void testMethod3() {
        System.out.println("TestNgExampleOne.testMethod3");
    }
}
