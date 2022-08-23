package com.s3.t.service.abstraction;

import com.s3.t.model.request.LocationRequest;
import com.s3.t.model.response.LocationResponse;

import java.util.List;

public interface LocationService {
    LocationResponse save(LocationRequest request);
    LocationResponse getBy(Long id);
    List<LocationResponse> findByLocationOrProvince(String location, String province);
    void delete(Long id);
    LocationResponse update(Long id, LocationRequest request);
}
