package com.restfulbooker.bookinginfo;

import com.restfulbooker.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class BookingCRUDTestWithSteps extends TestBase {

    static String username="admin";
    static String password="password123";
    static String firstname="Max";
    static String lastname="White";
    static int totalprice= 100;
    static boolean depositpaid= true;
    static String checkin="2023-08-01";
    static String checkout="2023-08-10";
    static String additionalneeds="Vegetarian Meal";
    static int id;

    @Steps
    BookingSteps bookingSteps;

    @Title("This will create a new token")
    @Test
    public void test001(){
        bookingSteps.ceateToken(username,password);
    }

    @Title("This will create a new booking")
    @Test
    public void test002(){
        ValidatableResponse response=bookingSteps.createBooking(firstname,lastname,totalprice,depositpaid,checkin,checkout,additionalneeds);
        id=response.extract().path("bookingid");
    }

    @Title("This will verify if the booking is added")
    @Test
    public void test003(){
        ValidatableResponse response=bookingSteps.getBookingByFirstName(id);
        String bookingName=response.extract().path("firstname");
        Assert.assertTrue(bookingName.contains(firstname));
    }

    @Title("This will update the booking information and checking information updated")
    @Test
    public void test004(){
        ValidatableResponse response=bookingSteps.updateBooking(id,firstname,lastname,totalprice,depositpaid,checkin,checkout,additionalneeds);
        String updatedBookingName=response.extract().path("firstname");
        Assert.assertTrue(updatedBookingName.contains("Mithus"));
    }

    @Title("This will delete the booking and verify the booking is deleted")
    @Test
    public void test005(){
        bookingSteps.deleteBooking(id);
        bookingSteps.getBookingById(id);
    }

}
