package com.s3.t.service;

import com.s3.t.model.entity.Location;
import com.s3.t.model.mapper.LocationMapper;
import com.s3.t.model.request.LocationRequest;
import com.s3.t.model.response.LocationResponse;
import com.s3.t.repository.LocationRepository;
import com.s3.t.service.abstraction.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LocationServiceImpl implements LocationService {

    private final LocationMapper locationMapper;

    private final LocationRepository locationRepository;
    @Override
    public LocationResponse save(LocationRequest request) {
            Location location = locationMapper.entityToDto(request);
            Location locationCreated = locationRepository.save(location);
            LocationResponse response = locationMapper.dtoToEntity(locationCreated);

        return response ;
    }
}
