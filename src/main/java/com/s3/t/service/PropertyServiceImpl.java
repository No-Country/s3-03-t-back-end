package com.s3.t.service;

import com.s3.t.model.request.PropertyRequest;
import com.s3.t.model.response.PropertyResponse;
import com.s3.t.service.abstraction.PropertyService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Override
    public PropertyResponse add(ArrayList<MultipartFile>multipartFiles, PropertyRequest request) {


        return null;
    }
}
