package com.s3.t.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @NotBlank(message = "First Name Required")
    private String firstName ;
    @NotBlank(message = "Last name Required")
    private String lastName ;
    @Email
    private String email;

    @NotBlank(message = "DNI is requerid")
    private String dni;

    @NotBlank(message = "Password is requerid")
    @Size(min=8,max=250, message = "password should have at least 8 characters")
    private String password;
    @NotBlank(message = "Address is required")
    private String  Address;
    @NotBlank(message = "Telephone is required")
    private String telephone;
    @NotBlank(message = "Birth is required")
    private String  birth;

  /*  private String antecedent;

    private String departament;

    */

    public User(Long id, String firstName, String lastName,
                String email, String dni, String password,
                String address, String telephone, String birth) {

        this.id = id;
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
