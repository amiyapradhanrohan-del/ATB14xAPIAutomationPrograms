package com.thetestingacademy.ex_06_TestValidations;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;
public class APITesting027_RestAssured_TestNG_AssertJ_Assertions {


    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;
    String token;
    Integer bookingID;

    @Description("Verify the POST Request")
    @Step("Verify that the post request is working fine")
    @Test
    public void test_createBooking_POST() {

        //String Payload
        String payload = "{\n" +
                " \"firstname\" : \"Pramod\",\n" +
                " \"lastname\" : \"Brown\",\n" +
                " \"totalprice\" : 111,\n" +
                " \"depositpaid\" : true,\n" +
                " \"bookingdates\" : {\n" +
                " \"checkin\" : \"2018-01-01\",\n" +
                " \"checkout\" : \"2019-01-01\"\n" +
                "  },\n" +
                " \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(payload).log().all();

        Response response = requestSpecification.when().post();

        // Get Validatable response to perform validation
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        // Rest Assured -> import org.hamcrest.Matchers; %4-%5
        // Matchers.equalto()

        // Validated by using Matchers
        validatableResponse.body("bookingid", Matchers.notNullValue());
        validatableResponse.body("booking.firstname", Matchers.equalTo("Pramod"));
        validatableResponse.body("booking.lastname", Matchers.equalTo("Brown"));
        validatableResponse.body("booking.depositpaid", Matchers.equalTo(false));

        // Validated by using TestNG
        // TestNG - Extract the details of the firstname, bookingId, lastname from Response.

        bookingID = response.then().extract().path("bookingID");
        String firstname = response.then().extract().path("booking.firstname");
        String lastname = response.then().extract().path("booking.lastname");

        //Now we will verify - By using TestNG Assertion
        Assert.assertEquals(firstname,"Pramod");
        Assert.assertEquals(lastname, "Dutta");
        Assert.assertNotNull(bookingID);

        //Now we will verify by using AssertJ
        assertThat(bookingID).isPositive().isNotNull().isNotZero();
        assertThat(firstname).isNotEmpty().isNotBlank().isNotNull().isEqualTo("Pramod");
        assertThat(lastname).isNotEmpty().isNotBlank().isNotNull().isEqualTo("Dutta");


        // String s = ""; - This is Empty
        // String s2 = " "; - This is Blank


    }
}
