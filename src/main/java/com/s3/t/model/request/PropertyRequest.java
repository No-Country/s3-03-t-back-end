package com.s3.t.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class PropertyRequest implements Serializable {

    //private final Long id;
    @NotBlank(message = "ambient cannot be empty.")
    private final Integer ambient;
    private final String description;
    @NotBlank(message = "Direction cannot be empty.")
    private final String direction;
    @NotBlank(message = "Price cannot be empty.")
    private final Double price;
}
