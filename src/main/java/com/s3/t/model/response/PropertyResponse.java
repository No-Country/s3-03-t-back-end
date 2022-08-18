package com.s3.t.model.response;

import lombok.*;

import java.io.Serializable;

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
}
