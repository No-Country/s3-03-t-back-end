package com.s3.t.model.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class PropertyRequest implements Serializable {
    private Long id;
    @NotBlank(message = "ambient cannot be empty.")
    private  Integer ambient;
    private String description;
    @NotBlank(message = "Direction cannot be empty.")
    private  String direction;
    @NotBlank(message = "Price cannot be empty.")
    private  Double price;

}
