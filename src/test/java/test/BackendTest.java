package test;

import data.pojo.request.BookAuthorizationPojo;
import data.pojo.request.CreateBookingRequestPojo;
import data.pojo.request.UpdateBookingPojo;
import data.pojo.response.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class BackendTest {
    private static String token;


    @BeforeAll
    @DisplayName("Авторизация")
    static void setUp() {
        token = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(new BookAuthorizationPojo())
                .post("https://restful-booker.herokuapp.com/auth")
                .then()
                .statusCode(200)
                .extract().jsonPath().getString("token");

        System.out.println("Токен получен: " + token);
    }


    @Test
    @DisplayName("Создание бронирования")
    void createBookingApiTest() {
        CreateBookingIdResponsePojo createBookingResponse = RestAssured.given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(new CreateBookingRequestPojo())
                .when()
                .post("https://restful-booker.herokuapp.com/booking")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().body().as(CreateBookingIdResponsePojo.class);

        Assertions.assertEquals("Jim", createBookingResponse.getBooking().
                getFirstname(), "Имя не совпадает");
        Assertions.assertEquals("Brown", createBookingResponse.getBooking()
                .getLastname(), "Фамилия не совпадает");
        Assertions.assertEquals(111, createBookingResponse.getBooking().
                getTotalprice(), "Цена не совпадает");
        Assertions.assertEquals(true, createBookingResponse.getBooking().
                getDepositpaid(), "Депозит не оплачен");
        Assertions.assertEquals("2018-01-01", createBookingResponse.getBooking().
                getBookingdates().getCheckin(), "Дата заезда неверна");
        Assertions.assertEquals("2019-01-01", createBookingResponse.getBooking().
                getBookingdates().getCheckout(), "Дата выезда неверна");
        Assertions.assertEquals("Breakfast", createBookingResponse.getBooking().
                getAdditionalneeds(), "Хотелки не совпадают");

        System.out.println(createBookingResponse);
    }


    @Test
    @DisplayName("Обновление данных бронирования")
    void updateBookingApiTest() {
        CreateBookingIdResponsePojo createResponse = RestAssured.given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(new CreateBookingRequestPojo())
                .when()
                .post("https://restful-booker.herokuapp.com/booking")
                .then()
                .statusCode(200)
                .extract().as(CreateBookingIdResponsePojo.class);

        int bookingId = createResponse.getBookingid();

        UpdateBookingResponsePojo updateResponse = RestAssured.given()
                .log().all()
                .contentType(ContentType.JSON)
                .cookie("token", token)
                .body(new UpdateBookingPojo())
                .when()
                .put("https://restful-booker.herokuapp.com/booking/" + bookingId)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(UpdateBookingResponsePojo.class);

        Assertions.assertEquals("James", updateResponse.getFirstname()
                , "Имя не обновилось");
        Assertions.assertEquals("2018-02-02", updateResponse.
                getBookingdates().getCheckin(), "Дата заезда не обновилась");
        Assertions.assertEquals("2019-02-02", updateResponse.
                getBookingdates().getCheckout(), "Дата выезда не обновилась");

        System.out.println(updateResponse);
    }


    @Test
    @DisplayName("Удаление бронирования")
    void deleteBookingApiTest() {
        CreateBookingIdResponsePojo createResponse = RestAssured.given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(new CreateBookingRequestPojo())
                .when()
                .post("https://restful-booker.herokuapp.com/booking")
                .then()
                .statusCode(200)
                .extract().as(CreateBookingIdResponsePojo.class);

        int bookingId = createResponse.getBookingid();

        RestAssured.given()
                .log().all()
                .contentType(ContentType.JSON)
                .cookie("token", token)
                .when()
                .delete("https://restful-booker.herokuapp.com/booking/" + bookingId)
                .then()
                .log().status()
                .log().body()
                .statusCode(201);

        System.out.println("Бронь с ID " + bookingId + " успешно удалена");
    }


    @Test
    @DisplayName("Запрос данных бронирования")
    void getBookingApiTest() {
        CreateBookingIdResponsePojo createResponse = RestAssured.given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(new CreateBookingRequestPojo())
                .when()
                .post("https://restful-booker.herokuapp.com/booking")
                .then()
                .statusCode(200)
                .extract().as(CreateBookingIdResponsePojo.class);

        int bookingId = createResponse.getBookingid();

        GetBookIdResponsePojo getResponse = RestAssured.given()
                .log().all()
                .contentType(ContentType.JSON)
                .when()
                .get("https://restful-booker.herokuapp.com/booking/" + bookingId)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(GetBookIdResponsePojo.class);

        Assertions.assertEquals("Jim", getResponse.getFirstname());
        Assertions.assertEquals("Brown", getResponse.getLastname());
        Assertions.assertEquals(111, getResponse.getTotalprice());
        Assertions.assertEquals(true, getResponse.getDepositpaid());
        Assertions.assertEquals("2018-01-01", getResponse.getBookingdates().getCheckin());
        Assertions.assertEquals("2019-01-01", getResponse.getBookingdates().getCheckout());
        Assertions.assertEquals("Breakfast", getResponse.getAdditionalneeds());

        System.out.println(getResponse);
    }
}