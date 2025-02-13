package com.pragmatic.selenium.testng;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class TestNgExampleTwo {

    @Test
    @Parameters({"browser"})
    public void testMethod1(String browser) {
        System.out.println("Browser=" + browser);
        System.out.println("TestNgExampleTwo.testMethod1");
    }

    @Test
    public void testMethod2() {
        System.out.println("TestNgExampleTwo.testMethod2");
    }

    @Test
    public void testMethod3() {
        System.out.println("TestNgExampleTwo.testMethod3");
    }

}
