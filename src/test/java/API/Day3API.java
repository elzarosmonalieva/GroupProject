package API;

import Utilities.CashWiseAuthorization;
import Utilities.Config;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import entities.CustomResponse;
import entities.RequestBody;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day3API {
    String token = CashWiseAuthorization.getToken();
    int lastSellerId;

    @Test
    public void addSellers(){
        RequestBody body = new RequestBody();
        Faker faker = new Faker();
        for (int i = 0; i <5 ; i++) {
            body.setCompany_name(faker.company().name());
            body.setSeller_name(faker.name().name());
            body.setEmail(faker.internet().emailAddress());
            body.setPhone_number(faker.phoneNumber().phoneNumber());
            body.setAddress(faker.address().streetAddress());
            Response response1 = RestAssured.given()
                    .auth()
                    .oauth2(token)
                    .contentType(ContentType.JSON)
                    .body(body)
                    .post(Config.getProp("cashwise") + "/api/myaccount/sellers");
            System.out.println(response1.prettyPrint());
        }
    }
    @Test
    public void getAllSellerTest() throws JsonProcessingException {
        String token = CashWiseAuthorization.getToken();
        HashMap<String, Object> params = new HashMap();
        params.put("isArchived", false);
        params.put("page", 1);
        params.put("size", 20);

        Response response = RestAssured.given()
                .auth()
                .oauth2(token)
                .params(params)
                .get(Config.getProp("cashwise") + "/api/myaccount/sellers");
        System.out.println(response.prettyPrint());

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        CustomResponse customResponse = mapper.readValue(response.asString(), CustomResponse.class);
        lastSellerId = customResponse.getResponses().get(customResponse.getResponses().size() - 1).getSeller_id();
        System.out.println(lastSellerId);


        List<Integer> sellerIds = new ArrayList<>();
        for (int i = 0; i < customResponse.getResponses().size(); i++) {
            Assert.assertNotNull(customResponse.getResponses().get(i).getSeller_id());
            sellerIds.add(customResponse.getResponses().get(i).getSeller_id());
        }
//        for (CustomResponse seller : customResponse.getResponses()) {
//            sellerIds.add(seller.getSeller_id());
//        }
        System.out.println("Seller IDs: " + sellerIds);
    }
        @Test
        public void deleteSeller(){
            Response response2 = RestAssured.given()
                    .auth()
                    .oauth2(token)
                    .delete(Config.getProp("cashwise") + "/api/myaccount/sellers" +"/6038");
            System.out.println(response2.statusCode());
            System.out.println(response2.prettyPrint());
            if(response2.statusCode()!=200){
                System.out.println("seller was not deleted");
            }else{
                System.out.println("seller was deleted succefully");
            }

        }
}
