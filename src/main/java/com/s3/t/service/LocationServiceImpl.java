package com.s3.t.service;

import com.s3.t.exception.EntityNotFoundException;
import com.s3.t.model.entity.Location;
import com.s3.t.model.mapper.LocationMapper;
import com.s3.t.model.request.LocationRequest;
import com.s3.t.model.response.LocationResponse;
import com.s3.t.repository.LocationRepository;
import com.s3.t.service.abstraction.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LocationServiceImpl implements LocationService {

    private final LocationMapper locationMapper;

    private final LocationRepository locationRepository;
    @Override
    public LocationResponse save(LocationRequest request) {
            Location location = locationMapper.entityToDto(request);
            Location locationCreated = locationRepository.save(location);
            return locationMapper.dtoToEntity(locationCreated);
    }

    @Override
    public LocationResponse getBy(Long id) {
        Optional<Location> location = locationRepository.findById(id);
        if(location.isEmpty() || location.get().getSoftDeleted()){
            throw new EntityNotFoundException("Location not found or has been deleted");

        }
        Location l = location.get();
        return locationMapper.dtoToEntityProperty(l);
    }
}
