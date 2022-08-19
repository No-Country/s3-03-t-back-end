package com.s3.t.service;

import com.s3.t.model.entity.Image;
import com.s3.t.model.entity.Property;
import com.s3.t.model.entity.User;
import com.s3.t.model.mapper.PropertyMapper;
import com.s3.t.model.request.PropertyRequest;
import com.s3.t.model.response.PropertyResponse;
import com.s3.t.repository.ImageRepository;
import com.s3.t.repository.PropertyRepository;
import com.s3.t.service.abstraction.ImageService;
import com.s3.t.service.abstraction.PropertyService;
import com.s3.t.service.abstraction.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PropertyServiceImpl implements PropertyService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyServiceImpl.class);
    private final PropertyRepository propertyRepository;
    private final UserService userService;
    private final ImageService imageService;
    private final PropertyMapper propertyMapper;
    private ImageRepository imageRepository;
    @Override
    public PropertyResponse add(List<MultipartFile> multipartFiles, PropertyRequest request) {
        User user = userService.getInfoUser();
        Property p=propertyMapper.propertyToRequest(request, user);

        if (chechListFile(multipartFiles)){
            List<Image> l=new ArrayList<>();
            p.setPostImages(l);
            LOGGER.warn("Las lista de archivos esta vacia");
        }else {
            LOGGER.error("La lista no esta vacia");
            List<Image> propertyListImage=imageService.imagesPost(multipartFiles);

            for (Image img:propertyListImage ) {
               p.setPostImages(propertyListImage);
               propertyRepository.save(p);
            }

        }

        return propertyMapper.responseToProperty(propertyRepository.save(p));
  /*      ArrayList<ImagePost> postImages = fileUploadService.uploadImagePostToDB(postImage);*/

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
