package com.s3.t.model.request;

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

}
