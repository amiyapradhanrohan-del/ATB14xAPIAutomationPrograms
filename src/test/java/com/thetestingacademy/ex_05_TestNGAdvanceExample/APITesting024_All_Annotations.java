package com.thetestingacademy.ex_05_TestNGAdvanceExample;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class APITesting024_All_Annotations {

    @BeforeTest
    void demo1()
    {
        System.out.println("BeforeMethod");
    }
    @Test
    void demo2()
    {
        System.out.println("Test");
    }
    @AfterTest
    void demo3()
    {
        System.out.println("AfterMethod");
    }
}
