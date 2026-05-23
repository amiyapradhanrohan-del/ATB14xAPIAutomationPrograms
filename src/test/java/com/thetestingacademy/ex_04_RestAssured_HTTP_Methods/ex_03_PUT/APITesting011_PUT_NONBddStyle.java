package com.thetestingacademy.ex_04_RestAssured_HTTP_Methods.ex_03_PUT;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITesting011_PUT_NONBddStyle {
    // PUT
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

        Faker faker = new Faker(); //To print a random name we can use this Faker library
        String name = faker.name().fullName();
        System.out.println(name);


        String payload = "{\n" +
                "    \"firstname\" : \""+name+"\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

//------------------------ Given -------------------------
            r = RestAssured.given();
            r.baseUri("https://restful-booker.herokuapp.com");
            r.basePath("/booking/" + bookingid);

            r.contentType(ContentType.JSON); //header
            r.cookies("token", token);
            //r.auth().digest()
        // r.auth().basic()
            r.body(payload).log().all();

            // --------------------- When ------------------------

        response = r.when().log().all().put(); // here the API got hit

        // ------------- Then -------------------

        vr = response.then().log().all();
        vr.statusCode(200);




    }

}
