package com.s3.t.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class LocationRequest {

    @NotBlank(message = "Location cannot be empty.")
    private String location;

    @NotBlank(message = "Province cannot be empty.")
    private String province;

    @NotBlank(message = "Country cannot be empty.")
    private String country;


}
