package com.s3.t.model.request;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@Data
public class UserRegister implements Serializable {
    @NotBlank(message = "First Name Required")
    private final String firstName;
    @NotBlank(message = "Last name Required")
    private final String lastName;
    @Email
    private final String email;
    @NotBlank(message = "DNI is requerid")
    private final String dni;
    @NotBlank(message = "Password is requerid")
    @Size(min = 8, max = 250, message = "password should have at least 8 characters")
    private final String password;
    @NotBlank(message = "Address is required")
    private final String Address;
    @NotBlank(message = "Telephone is required")
    private final String telephone;
    @NotBlank(message = "Birth is required")
    private final String birth;
    
    

    public UserRegister(String firstName, String lastName,
                        String email, String dni, 
                        String password,
                        String address,
                        String telephone, String birth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dni = dni;
        this.password = password;
        Address = address;
        this.telephone = telephone;
        this.birth = birth;
    }
}
