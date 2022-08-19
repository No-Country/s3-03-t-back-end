package com.s3.t.model.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LocationResponse {

    private Long id;

    private String location;

    private String province;

    private String country;

    private List<PropertyResponse> propertyResponseList;
}
