package com.s3.t.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;


@AllArgsConstructor
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

    @NotBlank(message = "Email cannot be empty.")
    @Email
    private String email;

    @NotBlank(message = "Password cannot be empty.")
    @Size(min = 8, max = 30, message = "Password should have at least 8 characters")
    private String password;

    @NotBlank(message = "Telephone is required")
    private String telephone;

    @JsonFormat(pattern="yyyy-MM-dd")
    @NotBlank(message = "Birth is required")
    private LocalDate birthDate;

    @NotBlank(message = "Dni cannot be empty.")
    private String dni;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Antecedent> antecedents;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Property> properties;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Score> scores;



}
