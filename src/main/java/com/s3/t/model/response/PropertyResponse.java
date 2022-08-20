package com.s3.t.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class PropertyResponse implements Serializable {
    private final Long id;
    private final Integer ambient;
    private final String description;
    private final String direction;
    private final Double price;
    private final String firtName;
    private final String lastName;
    private final String telephone;
    private final List<ImageResponse> imgList;
    private final LocationResponse location;
}
