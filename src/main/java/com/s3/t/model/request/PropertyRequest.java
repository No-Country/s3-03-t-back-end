package com.s3.t.model.request;

import com.s3.t.model.entity.Location;
import lombok.*;
import javax.validation.constraints.NotBlank;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PropertyRequest  {
    @NotBlank(message = "ambient cannot be empty.")
    private  Integer ambient;
    private  String description;
    @NotBlank(message = "Direction cannot be empty.")
    private  String direction;
    @NotBlank(message = "Price cannot be empty.")
    private  Double price;
    @NotBlank(message = "Location cannot be empty.")
    private Location location;

    @NotBlank(message = "Pet cannot be empty.")
    private Boolean pet;
    @NotBlank(message = "Bath cannot be empty.")
    private Boolean bath;
    @NotBlank(message = "Furnished cannot be empty.")
    private Boolean furnished;
    @NotBlank(message = "Smoker cannot be empty.")
    private Boolean smoker;
    @NotBlank(message = "SquareMeter cannot be empty.")
    private Integer squareMeter;


}
