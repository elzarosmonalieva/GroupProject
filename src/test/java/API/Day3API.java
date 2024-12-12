package API;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.CustomResponse;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

public class Day3API {
    @Test
    public void getAllSellerTest() throws JsonProcessingException {
        String token ="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJleHAiOjE3MzQ4OTI4NjgsImlhdCI6MTczMjMwMDg2OCwidXNlcm5hbWUiOiJhZGFtczEyM0BtYWlsLmNvbSJ9.nHp9HZUebcsQhJQ_Z9R-wM8fb3vbckaDyko1qV0F7SAdP8XUr4xCjTBWnCburMC15Pl7VO-BQuNdVpDGzZzR-Q";
        String url = "https://backend.cashwise.us/api/myaccount/sellers";
        HashMap<String, Object> params = new HashMap();
        params.put("isArchived", false);
        params.put("page", 1);
        params.put("size", 10);
        Response response = RestAssured.given().auth().oauth2(token).params(params).get(url);
        System.out.println(response.prettyPrint());

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        CustomResponse customResponse = mapper.readValue(response.asString(), CustomResponse.class);
        int lastIndex = customResponse.getResponses().get(customResponse.getResponses().size()-1).getSeller_id();
        System.out.println(lastIndex);


            // to get seller id of every single seller and store it in newly created List
        // before adding make sure check those seller ids not null one by one
        //


    }
}
