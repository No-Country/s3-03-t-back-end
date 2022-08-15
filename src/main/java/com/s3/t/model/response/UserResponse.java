package com.s3.t.model.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter @Setter

@Data
public class UserResponse implements Serializable {
    private  Long id  ;
    private  String email;
    private String token  ;

    public UserResponse(Long id, String email, String token) {
        this.id = id;
        this.email = email;
        this.token = token;
    }

    public UserResponse() {

    }
}
