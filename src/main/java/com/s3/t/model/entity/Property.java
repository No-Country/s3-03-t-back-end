package com.s3.t.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "property")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer ambient;
    private String description;
    private String direction;
    @ManyToOne(fetch = FetchType.LAZY)
    private Location location;
    private Double price;

    public Property(String description, String direction, Location location, double price) {
        this.description = description;
        this.direction = direction;
        this.location = location;
        this.price = price;
    }

}
