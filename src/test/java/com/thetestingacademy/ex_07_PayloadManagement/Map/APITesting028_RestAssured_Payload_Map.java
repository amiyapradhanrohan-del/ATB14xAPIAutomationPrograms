package com.thetestingacademy.ex_07_PayloadManagement.Map;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.requestSpecification;

public class APITesting028_RestAssured_Payload_Map {

    RequestSpecification requestSpecification;
    ValidatableResponse validatableResponse;
    Response response;
    String token;
    Integer bookingId;

    @Description("Verify the POST Request")
    @Step("Verify that the post request is working fine")
    @Test
    public void test_POST() {

        //String Payload - We will not use this Payload
        /*
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
         */
        // We will Create a "Hashmap" from the above Payload
        // In the "Hashmap" ordered is not maintained
        // LinkedHashmap - can maintain the order also.
        // TreeMap is used for Sorting

        Map<String,Object> jsonBodyUsingMap = new LinkedHashMap<>();
        jsonBodyUsingMap.put("firstname","Pramod");
        jsonBodyUsingMap.put("lastname","Dutta");
        jsonBodyUsingMap.put("totalprice",123);
        jsonBodyUsingMap.put("depositpaid",false);


        Map<String,Object> bookingDatesMap = new LinkedHashMap<>();
        bookingDatesMap.put("checkin","2018-01-01");
        bookingDatesMap.put("checkout","2019-01-01");


        jsonBodyUsingMap.put("bookingdates",bookingDatesMap);
        jsonBodyUsingMap.put("additionalneeds","Breakfast");
        System.out.println(jsonBodyUsingMap);


        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(jsonBodyUsingMap).log().all();

        Response response = requestSpecification.when().post();

        // Get Validatable response to perform validation
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        // Validated by using Matchers
        validatableResponse.body("bookingid", Matchers.notNullValue());
        validatableResponse.body("booking.firstname", Matchers.equalTo("Pramod"));
        validatableResponse.body("booking.lastname", Matchers.equalTo("Dutta"));
        validatableResponse.body("booking.depositpaid", Matchers.equalTo(false));


    }
}