package com.s3.t.service.abstraction;

import com.s3.t.model.request.PropertyRequest;
import com.s3.t.model.request.PropertyState;
import com.s3.t.model.response.PropertyResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PropertyService {
    PropertyResponse add(List<MultipartFile> multipartFiles, PropertyRequest request);
    List<PropertyResponse> getAll();
    PropertyResponse getById(Long id);
    void update(Long id,PropertyRequest request);
    void delete(Long id);
    void patch(Long id, PropertyState request);
}
