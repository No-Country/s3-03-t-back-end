package com.s3.t.model.entity;

import com.s3.t.util.EnumAntecedent;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter @Setter
@Entity
@Table(name = "antecedent")
public class Antecedent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private LocalDate date;
    private EnumAntecedent antecedentType;


}
