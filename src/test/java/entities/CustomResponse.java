package entities;


import lombok.Data;

import java.util.List;

@Data

public class CustomResponse {


    private List<CustomResponse> responses;
    private int seller_id;
    private String email;
    private String client_name;
}
