package com.s3.t.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.s3.t.util.EnumAntecedent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "antecedent")
public class Antecedent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "description cannot be empty.")
    private String description;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate date;

    @NotBlank(message = "Antecedent type cannot be empty.")
    private EnumAntecedent antecedentType;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;


}
