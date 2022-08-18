package com.s3.t.model.mapper;

import com.s3.t.model.entity.Property;
import com.s3.t.model.response.PropertyResponse;
import org.springframework.stereotype.Component;

@Component
public class PropertyMapper {
    public PropertyResponse dtoToEntity(Property property) {
        return PropertyResponse.builder()
                .id(property.getId())
                .ambient(property.getAmbient())
                .direction(property.getDirection())
                .price(property.getPrice())
                .description(property.getDescription())
                .build();

    }
}
