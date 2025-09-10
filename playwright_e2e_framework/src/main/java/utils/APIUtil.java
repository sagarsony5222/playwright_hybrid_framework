package utils;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class APIUtil {

    public static Response get(String endpoint) {
        return given().when().get(endpoint).then().extract().response();
    }

    public static Response post(String endpoint, Object body) {
        return given()
                .header("Content-Type", "application/json")
                .body(body)
                .when().post(endpoint)
                .then().extract().response();
    }

    public static Response put(String endpoint, Object body) {
        return given()
                .header("Content-Type", "application/json")
                .body(body)
                .when().put(endpoint)
                .then().extract().response();
    }

    public static Response delete(String endpoint) {
        return given().when().delete(endpoint).then().extract().response();
    }
}
