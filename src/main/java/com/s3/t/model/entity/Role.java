package com.s3.t.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name="roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @CreationTimestamp
    private Timestamp timestamp;


    public Role(Long id, String name, String description, Timestamp timestamp) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.timestamp = timestamp;
    }

}
