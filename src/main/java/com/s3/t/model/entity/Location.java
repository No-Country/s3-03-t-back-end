package com.s3.t.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "City cannot be empty.")
    private String city;

    @NotBlank(message = "Province cannot be empty.")
    private String province;

    @NotBlank(message = "Country cannot be empty.")
    private String country;

    @OneToMany(mappedBy = "location")
    private List<Property> propertyList;


}
