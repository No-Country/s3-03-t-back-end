package com.s3.t.service.abstraction;

import com.s3.t.model.request.PropertyRequest;
import com.s3.t.model.response.PropertyResponse;

public interface PropertyService {
    PropertyResponse upload(PropertyRequest request);
}
