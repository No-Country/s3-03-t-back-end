package com.s3.t.service;

import com.s3.t.model.entity.Image;
import com.s3.t.model.entity.Location;
import com.s3.t.model.entity.Property;
import com.s3.t.model.entity.User;
import com.s3.t.model.mapper.PropertyMapper;
import com.s3.t.model.request.PropertyRequest;
import com.s3.t.model.response.LocationResponse;
import com.s3.t.model.response.PropertyResponse;
import com.s3.t.repository.ImageRepository;
import com.s3.t.repository.LocationRepository;
import com.s3.t.repository.PropertyRepository;
import com.s3.t.service.abstraction.ImageService;
import com.s3.t.service.abstraction.LocationService;
import com.s3.t.service.abstraction.PropertyService;
import com.s3.t.service.abstraction.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Service
@AllArgsConstructor
public class PropertyServiceImpl implements PropertyService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyServiceImpl.class);
    private final PropertyRepository propertyRepository;
    private final UserService userService;
    private final ImageService imageService;
    private final PropertyMapper propertyMapper;
    private final ImageRepository imageRepository;
    private final LocationRepository locationRepository;
    @Override
    @Transactional
    public PropertyResponse add(List<MultipartFile> multipartFiles, PropertyRequest request) {
        User user = userService.getInfoUser();
        Location location=locationRepository.findById(request.getLocation().getId()).orElseThrow();

        Property p=propertyMapper.propertyToRequest(request, user);
        p.setLocation(location);
        if (chechListFile(multipartFiles)){
            LOGGER.warn("Las lista de archivos esta vacia");
            p.setPostImages(new ArrayList<>());

        }else {
            LOGGER.error("La lista no esta vacia");
            //generacion de List de imagenes
            p.setPostImages(imageService.imagesPost(multipartFiles));
            for (int i = 0; i <p.getPostImages().size()-1 ; i++) {
                propertyRepository.save(p);
            }
        }
        return propertyMapper.responseToProperty( propertyRepository.save(p));
    }
    private boolean chechListFile(List<MultipartFile>multipartFiles){
        int c=0;
        for (MultipartFile m: multipartFiles ) {
            if (m.getOriginalFilename().isEmpty()){
                c++;
            }
        }
        if(c!=multipartFiles.size()){
            return false;
        }
     return true;
    }
}
