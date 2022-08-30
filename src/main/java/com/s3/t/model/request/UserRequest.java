package com.s3.t.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRequest {

    @NotBlank(message = "First Name Required")
    private String firstName ;

    @NotBlank(message = "Last name Required")
    private String lastName ;

    @NotBlank(message = "Email cannot be empty.")
    @Email
    private String email;

    @NotBlank(message = "Password cannot be empty.")
    @Size(min = 8, max = 250, message = "Password should have at least 8 characters")
    private String password;

    @NotBlank(message = "Telephone is required")
    private String telephone;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    //@NotBlank(message = "Birth is required")
    private LocalDate birthDate;

    @NotBlank(message = "Dni cannot be empty.")
    private String dni;

    //private MultipartFile image;
}
