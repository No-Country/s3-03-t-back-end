package com.s3.t.model.mapper;

import com.s3.t.model.entity.Image;
import com.s3.t.model.entity.Property;
import com.s3.t.model.entity.User;
import com.s3.t.model.request.PropertyRequest;
import com.s3.t.model.response.LocationResponse;
import com.s3.t.model.response.PropertyResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class PropertyMapper {
    @Autowired
    public  ImageMapper imageMapper;
    public Property propertyToRequest(PropertyRequest request, User user) {

        return Property.builder()
                .contracts(null)
                .ambient(request.getAmbient())
                .description(request.getDescription())
                .direction(request.getDirection())
                .user(user)
                .postImages(new ArrayList<>())
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
                    .location(LocationResponse.builder()
                            .id(p.getLocation().getId())
                            .country(p.getLocation().getCountry())
                            .location(p.getLocation().getLocation())
                            .province(p.getLocation().getProvince())
                            .build())
                    .imgList(p.getPostImages().stream()
                            .map( i -> imageMapper.imageToDto(i) )
                            .collect(Collectors.toList()))
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
