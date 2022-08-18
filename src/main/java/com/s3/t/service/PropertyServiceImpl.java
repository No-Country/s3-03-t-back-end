package com.s3.t.service;

import com.s3.t.model.entity.User;
import com.s3.t.model.request.PropertyRequest;
import com.s3.t.model.response.PropertyResponse;
import com.s3.t.repository.PropertyRepository;
import com.s3.t.repository.UserRepository;
import com.s3.t.service.abstraction.PropertyService;
import com.s3.t.service.abstraction.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class PropertyServiceImpl implements PropertyService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyServiceImpl.class);
    private final PropertyRepository propertyRepository;
    private final UserService userService;
    @Override
    public PropertyResponse add(ArrayList<MultipartFile>multipartFiles, PropertyRequest request) {
        User user = userService.getInfoUser();
        LOGGER.error(user.getEmail());
        System.out.printf(request.getDescription());
        return null;
    }
}
