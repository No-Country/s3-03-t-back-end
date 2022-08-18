package com.s3.t.service.abstraction;

import com.s3.t.model.request.PropertyRequest;
import com.s3.t.model.response.PropertyResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

public interface PropertyService {
    PropertyResponse add(ArrayList<MultipartFile> multipartFiles, PropertyRequest request);
}
