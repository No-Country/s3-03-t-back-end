package com.s3.t.service.abstraction;

import com.s3.t.model.request.LocationRequest;
import com.s3.t.model.response.LocationResponse;

public interface LocationService {
    LocationResponse save(LocationRequest request);
    LocationResponse getBy(Long id);
}
