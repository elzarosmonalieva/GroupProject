package Utilities;

import entities.RequestBody;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class CashWiseAuthorization {
    @Test
public  String getToken(){
            RequestBody requestBody = new RequestBody();
            requestBody.setUsername(Config.getProp("username"));
            requestBody.setPassword(Config.getProp("password"));
            Response response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .post(Config.getProp("cashwiseApiBaseUrl") + "/api/myaccount/auth/login");
        System.out.println(response.prettyPrint());
return response.jsonPath().get("jwt_token");



    }

}
