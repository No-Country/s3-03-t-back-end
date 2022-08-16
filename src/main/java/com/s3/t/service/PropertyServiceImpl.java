package com.s3.t.service;

import com.s3.t.model.request.PropertyRequest;
import com.s3.t.model.response.PropertyResponse;
import com.s3.t.service.abstraction.PropertyService;
import org.springframework.stereotype.Service;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Override
    public PropertyResponse upload(PropertyRequest request) {
        return null;
    }
}
