package com.s3.t.model.mapper;

import com.s3.t.model.entity.Location;
import com.s3.t.model.request.LocationRequest;
import com.s3.t.model.response.LocationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class LocationMapper {

    private final PropertyMapper propertyMapper;
    public Location entityToDto(LocationRequest request) {
        Location location = new Location();
        location.setLocation(request.getLocation());
        location.setCountry(request.getCountry());
        location.setProvince(request.getProvince());
        location.setSoftDeleted(false);
        return location;
    }
    public LocationResponse dtoToEntity(Location location) {
        LocationResponse response = new LocationResponse();
        response.setLocation(location.getLocation());
        response.setCountry(location.getCountry());
        response.setId(location.getId());
        response.setProvince(location.getProvince());
        return response;

    }

    public LocationResponse dtoToEntityProperty(Location location) {
        LocationResponse response = new LocationResponse();
        response.setLocation(location.getLocation());
        response.setCountry(location.getCountry());
        response.setId(location.getId());
        response.setProvince(location.getProvince());
        response.setPropertyResponseList(location.getPropertyList()
                .stream().map(propertyMapper::dtoToEntity)
                .collect(Collectors.toList()));
        return response;
    }
}
