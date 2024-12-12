package entities;


import lombok.Data;

import java.util.List;

@Data

public class CustomResponse {


    private List<CustomResponse> responses;

    public List<CustomResponse> getResponses() {
        return responses;
    }

    public void setResponses(List<CustomResponse> responses) {
        this.responses = responses;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }
    private int seller_id;

    public int getSeller_id(int size) {
        return seller_id;
    }

    public void setSeller_id(int seller_id) {
        this.seller_id = seller_id;
    }

    private String email;
    private String client_name;
}
