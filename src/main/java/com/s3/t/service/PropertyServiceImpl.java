package com.s3.t.service;

import com.s3.t.model.entity.Property;
import com.s3.t.model.entity.User;
import com.s3.t.model.mapper.PropertyMapper;
import com.s3.t.model.request.PropertyRequest;
import com.s3.t.model.response.PropertyResponse;
import com.s3.t.repository.PropertyRepository;
import com.s3.t.repository.UserRepository;
import com.s3.t.service.abstraction.ImageService;
import com.s3.t.service.abstraction.PropertyService;
import com.s3.t.service.abstraction.UserService;
import lombok.AllArgsConstructor;
import nonapi.io.github.classgraph.json.JSONUtils;
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
    private final ImageService imageService;
    private final PropertyMapper propertyMapper;
    @Override
    public PropertyResponse add(ArrayList<MultipartFile>multipartFiles, PropertyRequest request) {
        User user = userService.getInfoUser();
        Property p=propertyMapper.propertyToRequest(request, user);
        return propertyMapper.responseToProperty(propertyRepository.save(p));
  /*      ArrayList<ImagePost> postImages = fileUploadService.uploadImagePostToDB(postImage);*/

    }
}
