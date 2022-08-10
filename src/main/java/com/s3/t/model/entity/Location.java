package com.s3.t.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String province;
    private String country;

    @OneToMany(mappedBy = "location")
    private List<Property> propertyList;

    public Location(Long id, String city, String province, String country, List<Property> propertyList) {
        this.id = id;
        this.city = city;
        this.province = province;
        this.country = country;
        this.propertyList = new ArrayList<>();
    }
}
