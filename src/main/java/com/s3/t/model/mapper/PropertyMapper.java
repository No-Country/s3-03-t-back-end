package com.s3.t.model.mapper;

import com.s3.t.model.entity.Property;
import com.s3.t.model.entity.User;
import com.s3.t.model.request.PropertyRequest;
import com.s3.t.model.response.PropertyResponse;

import org.springframework.stereotype.Component;

@Component
public class PropertyMapper {

    public Property propertyToRequest(PropertyRequest request, User user) {
        return Property.builder()
                .contracts(null)
                .ambient(request.getAmbient())
                .description(request.getDescription())
                .direction(request.getDirection())
                .user(user)
                .postImages(null)
                .price(request.getPrice())
                .build();
    }
    public PropertyResponse responseToProperty(Property p){
            return PropertyResponse.builder()
                    .id(p.getId())
                    .firtName(p.getUser().getFirstName())
                    .lastName(p.getUser().getLastName())
                    .telephone(p.getUser().getTelephone())
                    .ambient(p.getAmbient())
                    .description(p.getDescription())
                    .direction(p.getDirection())
                    .price(p.getPrice())
                    .build();
        }

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
