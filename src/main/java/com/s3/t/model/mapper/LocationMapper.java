package com.s3.t.model.mapper;

import com.s3.t.model.entity.Location;
import com.s3.t.model.request.LocationRequest;
import com.s3.t.model.response.LocationResponse;
import org.springframework.stereotype.Component;


@Component
public class LocationMapper {
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
}
