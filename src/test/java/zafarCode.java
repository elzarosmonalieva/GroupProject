import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

public class zafarCode {
   /* public static void main(String[] args) {
        String token ="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJleHAiOjE3MzQ4OTI4NjgsImlhdCI6MTczMjMwMDg2OCwidXNlcm5hbWUiOiJhZGFtczEyM0BtYWlsLmNvbSJ9.nHp9HZUebcsQhJQ_Z9R-wM8fb3vbckaDyko1qV0F7SAdP8XUr4xCjTBWnCburMC15Pl7VO-BQuNdVpDGzZzR-Q";
        String url = "https://backend.cashwise.us/api/myaccount/sellers/all";
        HashMap<String, Object> params = new HashMap();
             params.put("isArchived", false);
             params.put("page", 1);
             params.put("size", 10);



        Response response = RestAssured.given().auth().oauth2(token).params(params).get(url);
        System.out.println(response.prettyPrint());
    }
    @Test
    public void regres(){

        String url ="https://reqres.in/api/users/7";
        Response response = RestAssured.get(url);
        String expectedSupportText = "Tired of writing endless social media content? Let Content Caddy generate it for you.";
        int statusCode = 200;
        String actualSupportText = response.jsonPath().get("support.text");

        Assert.assertEquals(expectedSupportText, actualSupportText);
        Assert.assertEquals(statusCode, response.statusCode());

    }*/
    @Test
    public void getAllSellerTest(){
        String token ="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJleHAiOjE3MzQ4OTI4NjgsImlhdCI6MTczMjMwMDg2OCwidXNlcm5hbWUiOiJhZGFtczEyM0BtYWlsLmNvbSJ9.nHp9HZUebcsQhJQ_Z9R-wM8fb3vbckaDyko1qV0F7SAdP8XUr4xCjTBWnCburMC15Pl7VO-BQuNdVpDGzZzR-Q";
        String url = "https://backend.cashwise.us/api/myaccount/sellers";
        HashMap<String, Object> params = new HashMap();
        params.put("isArchived", false);
        params.put("page", 1);
        params.put("size", 10);
        Response response = RestAssured.given().auth().oauth2(token).params(params).get(url);
        System.out.println(response.prettyPrint());
        Assert.assertEquals(response.statusCode(), 200);
        int sellerId = response.jsonPath().get("responses[0].seller_id");
        Response response1 = RestAssured.given().auth().oauth2(token).get(url+"/"+sellerId);
        Assert.assertEquals(response1.statusCode(), 200);
        int actualSellerId =(response1.jsonPath().get("seller_id"));
        Assert.assertTrue(sellerId == actualSellerId);

        Response response2 = RestAssured.given().auth().oauth2(token).delete(url+"/"+sellerId);
        Assert.assertEquals(response1.statusCode(), 200);
        String sellerId2 = RestAssured.given().auth().oauth2(token).params(params).get(url).jsonPath().get("responses[0].seller_id");
        Assert.assertEquals(sellerId2, sellerId);
    }
}
