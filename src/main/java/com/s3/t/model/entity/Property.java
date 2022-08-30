package com.s3.t.model.entity;

import com.s3.t.util.PropertyStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @Builder
@Entity
@Table(name = "property")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "ambient cannot be empty.")
    private Integer ambient;

    private String description;

    @NotBlank(message = "Direction cannot be empty.")
    private String direction;

    private Boolean softDeleted;

    @ManyToOne(fetch = FetchType.LAZY)
    private Location location;

    @NotNull(message = "Price cannot be empty.")
    private Double price;

    @CreationTimestamp
    private Timestamp timestamp;

    @Column(name="status", nullable = false, length = 8 )
    @Enumerated(value = EnumType.STRING)
    private PropertyStatus status;
    @NotNull(message = "Pet cannot be empty.")
    private Boolean pet;
    @NotNull(message = "Bath cannot be empty.")
    private Boolean bath; //baño
    @NotNull(message = "Furnished cannot be empty.")
    private Boolean furnished; //amoblado
    @NotNull(message = "Smoker cannot be empty.")
    private Boolean smoker; //fumador
    @NotNull(message = "SquareMeter cannot be empty.")
    private Integer squareMeter;// fumador

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "property")
    private List<Contract> contracts;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name="postImages")
    private List<Image> postImages;


}
