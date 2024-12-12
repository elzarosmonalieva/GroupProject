package API;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.JSONObject;

import static org.hamcrest.Matchers.equalTo;

public class IntroToPostRequest {
    public static void main(String[] args) {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJleHAiOjE3MzQ4ODY1MDAsImlhdCI6MTczMjI5NDUwMCwidXNlcm5hbWUiOiJxd2VydHlAZ21haWwuY29tIn0.Qz549WkQN2vBMUOGMMYAikjzORzBEj40OnkfIpsK3xkKkfPZ6p1pCCMQKapSxx69Wa-Y_oIrn9O60E3wANUX6g";

        JSONObject requestBody = new JSONObject();
        requestBody.put("product_title", "manty");
        requestBody.put("product_price", 32);
        requestBody.put("service_type_id", 0);
        requestBody.put("category_id", 1996);
        requestBody.put("product_description", "Food");
        requestBody.put("date_of_payment", "2024-11-30");
        requestBody.put("remind_before_day", 1);
        requestBody.put("do_remind_every_month", "REPEAT_EVERY_MONTH");

       RestAssured.given()
               .contentType(ContentType.JSON)
               .accept(ContentType.JSON)
                .auth()
                .oauth2(token)
                .baseUri("https://backend.cashwise.us")
                .body(requestBody.toString())
                .when()
                .post("/API/myaccount/products")
                .then()
                .statusCode(201)
                .body("product_title", equalTo("manty"))
                .body("product_price", equalTo(32));
    }
}
//"product_title": "apples",
//  "product_price": 30,
//  "service_type_id": 0,
//  "category_id": 0,
//  "product_description": "sweet crispy apples",
//  "date_of_payment": "2024-11-28",
//  "remind_before_day": 0,
//  "do_remind_every_month": "REPEAT_EVERY_MONTH"
