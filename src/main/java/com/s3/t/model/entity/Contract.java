package com.s3.t.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.s3.t.util.EnumContract;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "contract")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate startDate;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate expirationDate;

    private EnumContract conditions; // Agrego la s porque es palabra reservada

    @ManyToOne(fetch = FetchType.LAZY)
    private Property property;
}
