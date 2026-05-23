package com.thetestingacademy.ex_06_TestValidations;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class APITesting026_TestNG_Assertions {

    @Test
    public void test_Hard() {
        System.out.println("start");
        Assert.assertEquals("pramod", "Pramod");
        System.out.println("end");
    }
    //In "Hard" types of Assertion Next line will not be executed.

    @Test
    public void test_Soft() {
        System.out.println("start");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals("pramod","Pramod");
        System.out.println("end");
        softAssert.assertAll();
    }
}
//In "Soft" type of Assertion next line will be executed.
