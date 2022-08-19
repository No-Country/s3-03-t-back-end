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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LocationServiceImpl implements LocationService {

    private final LocationMapper locationMapper;

    private final LocationRepository locationRepository;
    @Override
    public LocationResponse save(LocationRequest request) {

            Location location = locationMapper.entityToDto(request);
            Location l = locationRepository.findByLocation(request.getLocation());
            if(l != null){
                throw new EntityNotFoundException("there can be no duplicate locations");
            }else {
                Location locationCreated = locationRepository.save(location);
                return locationMapper.dtoToEntity(locationCreated);
            }
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
    @Override
    public List<LocationResponse> findByLocationOrProvince(String location, String province) {
        List<Location> locations = locationRepository.findByLocationOrProvince(location, province);
        List<LocationResponse> responses = new ArrayList<>();
        locations.forEach(loc -> {
            if(loc.getSoftDeleted()){
                throw new EntityNotFoundException("locality or province eliminated");
            }
            responses.add(locationMapper.dtoToEntityProperty(loc));
        });
        return responses;
    }

    @Override
    public void delete(Long id) {
        Location location = getLocation(id);
        location.setSoftDeleted(true);
        locationRepository.save(location);
    }

    @Override
    public LocationResponse update(Long id, LocationRequest request) {
        Location location = getLocation(id);
        locationMapper.locationUpdate(location, request);
        Location locationUpdate = locationRepository.save(location);
        return locationMapper.dtoToEntityProperty(locationUpdate);
    }

    private Location getLocation(Long id) {
        Optional<Location> location = locationRepository.findById(id);
        if(location.isEmpty() || location.get().getSoftDeleted()){
            throw new EntityNotFoundException("Location not found or has been deleted");
        }
        return location.get();
    }


}
