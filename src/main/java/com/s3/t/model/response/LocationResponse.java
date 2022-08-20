package com.s3.t.model.response;


import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LocationResponse {

    private Long id;

    private String location;

    private String province;

    private String country;

    private List<PropertyResponse> propertyResponseList;
}
