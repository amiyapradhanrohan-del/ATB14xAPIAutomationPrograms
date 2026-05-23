package com.thetestingacademy.ex_04_RestAssured_HTTP_Methods.ex_05_DELETE;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITesting013_DELETE_NONBddStyle {
    // PATCH
    // Pre - Req.
    // token, booking id - A

    // public void get_token(){}
    // public void get_booking_id(){}

    RequestSpecification r;
    Response response;
    ValidatableResponse vr;

    @Test
    public void test_put_non_bdd(){
        String token = "0667c7d840e11b7";
        String bookingid = "425";


        //Payload is not required in DELETE API

//------------------------ Given -------------------------
            r = RestAssured.given();
            r.baseUri("https://restful-booker.herokuapp.com");
            r.basePath("/booking/" + bookingid);

            r.contentType(ContentType.JSON); //header
            r.cookies("token", token);
            //r.auth().digest()
        // r.auth().basic()
          //No body is required, only bookingid is required and the method name will be delete

            // --------------------- When ------------------------

        response = r.when().log().all().delete(); // here the API got hit

        // ------------- Then -------------------

        vr = response.then().log().all();
        vr.statusCode(200);




    }

}
