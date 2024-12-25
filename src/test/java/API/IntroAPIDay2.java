package API;

import Utilities.Config;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import entities.CustomResponse;
import entities.RequestBody;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperDeserializationContext;
import io.restassured.mapper.ObjectMapperSerializationContext;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

public class IntroAPIDay2 {
    public static void main(String[] args) throws JsonProcessingException {

        HashMap<String, Object> params = new HashMap();
        params.put("isArchived", false);
        params.put("page", 1);
        params.put("size", 10);
        Response response1 = RestAssured.given().auth().oauth2(Config.getProp("token")).params(params).get(Config.getProp("cashwise")+"/api/myaccount/clients");
        Assert.assertEquals(response1.statusCode(), 200);
        String email = response1.jsonPath().get("responses[0].email");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        CustomResponse customResponse = mapper.readValue(response1.asString(), CustomResponse.class);
        System.out.println(customResponse.getClass());


    }
        @Test
public void createSeller(){
        String endpoint = "/api/myaccount/clients";

            RequestBody requestBody = new RequestBody();
            Faker faker = new Faker();
            requestBody.setAddress(String.valueOf(faker.address()));
            requestBody.setCompany_name(String.valueOf(faker.company()));
            requestBody.setPhone_number(String.valueOf(faker.phoneNumber()));
            requestBody.setSeller_name(String.valueOf(faker.name()));


        Response response = RestAssured.given()
                .auth()
                .oauth2(Config.getProp("token"))
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(Config.getProp("cashwise")+endpoint);
            System.out.println(response.prettyPrint());

        }
        @Test
    public void editSeller(){

            RequestBody requestBody = new RequestBody();
            Faker faker = new Faker();
            requestBody.setAddress(String.valueOf(faker.address()));
            requestBody.setCompany_name(String.valueOf(faker.company()));
            requestBody.setPhone_number(String.valueOf(faker.phoneNumber()));
            requestBody.setSeller_name(String.valueOf(faker.name()));


            String endpoint ="/api/myaccount/clients";;
            Response response = RestAssured.given()
                    .auth()
                    .oauth2(Config.getProp("token"))
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .post(Config.getProp("cashwise")+endpoint+"/5966" );


        }

    }

