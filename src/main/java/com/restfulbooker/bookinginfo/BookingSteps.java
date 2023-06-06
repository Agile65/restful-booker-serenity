package com.restfulbooker.bookinginfo;

import com.restfulbooker.constants.EndPoints;
import com.restfulbooker.model.BookingPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class BookingSteps {

    @Step("Creating token for booking")
    public ValidatableResponse ceateToken(String username,String password){
        BookingPojo bookingPojo=BookingPojo.getBookingPojo(username,password);
        return SerenityRest.given().log().all()
                .header("Content-Type","application/json")
                .header("Connection","keep-alive")
                .when()
                .body(bookingPojo)
                .post(EndPoints.CREATE_TOKEN)
                .then().log().all().statusCode(200);
    }

    @Step("Create booking with firstname : {0}, lastname : {1}, totalprice : {2}, depositpaid : {3}, additionalneeds : {4}")
    public ValidatableResponse createBooking(String firstname,String lastname,int totalprice,boolean depositpaid,String checkin,String checkout,String additionalneeds){
        BookingPojo bookingPojo=BookingPojo.getBookingPojo1(firstname, lastname, totalprice, depositpaid, checkin, checkout, additionalneeds);
        return SerenityRest.given().log().all()
                .header("Content-Type","application/json")
                .header("Connection","keep-alive")
                .when()
                .body(bookingPojo)
                .post(EndPoints.ALL_BOOKING)
                .then().log().all().statusCode(200);
    }

    @Step("Getting the booking information with the firstname : {0}")
    public ValidatableResponse getBookingByFirstName(int id){
        return SerenityRest.given().log().all()
                .pathParam("id",id)
                .when()
                .header("Connection","keep-alive")
                .get(EndPoints.GET_SINGLE_BOOKING_BY_ID)
                .then().log().all()
                .statusCode(200);
    }

    @Step("Updating the booking information id : {0}, firstname : {1}, lastname : {2}, totalprice : {3}, depositpaid : {4}, additionalneeds : {5}")
    public ValidatableResponse updateBooking(int id,String firstname,String lastname,int totalprice,boolean depositpaid,String checkin,String checkout,String additionalneeds){
        BookingPojo bookingPojo=BookingPojo.getBookingPojo1("Mithus", lastname, totalprice, depositpaid, checkin, checkout, additionalneeds);

        return SerenityRest.given().log().all()
                .header("Content-Type","application/json")
                .header("Connection","keep-alive")
                .header("Cookie","token=abc123")
                .header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .pathParam("id",id)
                .when()
                .body(bookingPojo)
                .put(EndPoints.UPDATE_BOOKING_BY_ID)
                .then().log().all()
                .statusCode(200);
    }

    @Step("Deleting the booking information with bookingId : {0}")
    public ValidatableResponse deleteBooking(int id){

        return SerenityRest.given().log().all()
                .header("Content-Type","application/json")
                .header("Connection","keep-alive")
                .header("Cookie","token=abc123")
                .header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .pathParam("id",id)
                .when()
                .delete(EndPoints.DELETE_BOOKING_BY_ID)
                .then().log().all()
                .statusCode(201);
    }

    @Step("Getting booking information with bookingId : {0}")
    public ValidatableResponse getBookingById(int id){

        return SerenityRest.given().log().all()
                .header("Content-Type","application/json")
                .header("Connection","keep-alive")
                .header("Cookie","token=abc123")
                .header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .pathParam("id",id)
                .when()
                .get(EndPoints.GET_SINGLE_BOOKING_BY_ID)
                .then().log().all()
                .statusCode(404);
    }
}
