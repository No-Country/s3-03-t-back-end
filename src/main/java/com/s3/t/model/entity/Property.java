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
@Getter @Setter
@Entity
@Table(name = "property")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "ambient cannot be empty.")
    private Integer ambient;

    private String description;

    @NotBlank(message = "Direction cannot be empty.")
    private String direction;

    @ManyToOne(fetch = FetchType.LAZY)
    private Location location;

    @NotBlank(message = "Price cannot be empty.")
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "property")
    private List<Contract> contracts;


}
