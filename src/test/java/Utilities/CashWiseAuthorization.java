package Utilities;

import entities.RequestBody;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class CashWiseAuthorization {
    public  static String getToken(){
            RequestBody requestBody = new RequestBody();
            requestBody.setEmail(Config.getProp("email"));
            requestBody.setPassword(Config.getProp("password"));
            Response response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .post(Config.getProp("cashwise") + "/api/myaccount/auth/login");
return response.jsonPath().getString("jwt_token");
    }
}
