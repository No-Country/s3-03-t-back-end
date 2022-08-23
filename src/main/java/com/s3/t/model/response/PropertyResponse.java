package com.s3.t.model.response;

import com.s3.t.util.PropertyStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
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
    private final String status;
    private final Boolean pet;
    private final Boolean bath;
    private final Boolean furnished;
    private final Boolean smoker;
    private final Integer squareMeter;
    private final List<ImageResponse> imgList;
    private final LocationResponse location;
}
