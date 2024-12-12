package API;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;

public class IntroToAPI {
    public static void main(String[] args) {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJleHAiOjE3MzQ4ODY1MDAsImlhdCI6MTczMjI5NDUwMCwidXNlcm5hbWUiOiJxd2VydHlAZ21haWwuY29tIn0.Qz549WkQN2vBMUOGMMYAikjzORzBEj40OnkfIpsK3xkKkfPZ6p1pCCMQKapSxx69Wa-Y_oIrn9O60E3wANUX6g";
        RestAssured.given()
                .auth()
                .oauth2(token)
                .baseUri("https://backend.cashwise.us/api")
                .when()
                .get("/myaccount/tags")
                .then()
                .statusCode(200);

        RestAssured.given()
                .auth()
                .oauth2(token)
                .baseUri("https://backend.cashwise.us/api")
                .when()
                .get("/myaccount/sellers/all")
                .then()
                .statusCode(200);

        RestAssured.given()
                .auth()
                .oauth2(token)
                .baseUri("https://backend.cashwise.us/api")
                .and()
                .queryParam("page", "1")
                .queryParam("size", "4")
                .when()
                .get("/myaccount/reminder/requests")
                .then()
                .statusCode(200);

        RestAssured.given()
                .auth()
                .oauth2(token)
                .baseUri("https://backend.cashwise.us/api")
                .when()
                .get("/myaccount/reminder/notifications")
                .then()
                .statusCode(200);


    }
}
