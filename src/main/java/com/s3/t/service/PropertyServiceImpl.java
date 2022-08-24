package com.s3.t.service;

import com.amazonaws.services.identitymanagement.model.EntityAlreadyExistsException;
import com.s3.t.exception.InvalidPropertyException;
import com.s3.t.model.entity.Location;
import com.s3.t.model.entity.Property;
import com.s3.t.model.entity.State;
import com.s3.t.model.entity.User;
import com.s3.t.model.mapper.PropertyMapper;
import com.s3.t.model.request.PropertyRequest;
import com.s3.t.model.request.PropertyState;
import com.s3.t.model.response.PropertyResponse;
import com.s3.t.repository.LocationRepository;
import com.s3.t.repository.PropertyRepository;
import com.s3.t.repository.StateRepository;
import com.s3.t.service.abstraction.ImageService;
import com.s3.t.service.abstraction.PropertyService;
import com.s3.t.service.abstraction.UserService;
import com.s3.t.util.PropertyStatus;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PropertyServiceImpl implements PropertyService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyServiceImpl.class);
    private final PropertyRepository propertyRepository;
    private final UserService userService;
    private final ImageService imageService;
    private final PropertyMapper propertyMapper;
    private final LocationRepository locationRepository;
    private final StateRepository stateRepository;
    @Override
    @Transactional
    public PropertyResponse add(List<MultipartFile> multipartFiles, PropertyRequest request) {
       try {
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
       }catch (InvalidPropertyException e){
           throw new InvalidPropertyException("Error creating a property: "+e.getMessage());
       }
    }

    @Override
    public List<PropertyResponse> getAll() {
        return propertyRepository.findAll().stream()
                .map(propertyMapper::responseToProperty)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PropertyResponse getById(Long id) {
      try {
          Property p = getProperty(id);
          return propertyMapper.responseToProperty(p);
      }catch (InvalidPropertyException e){
          throw new EntityAlreadyExistsException("Error trying to update a property" + e.getMessage());
      }

    }
    // TODO:Buscar x id
    public Property getProperty(Long id){
           return propertyRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public void update(Long id, PropertyRequest request) {
     try {
         Property p = getProperty(id);
         propertyRepository.save(propertyMapper.updateToProperty(p,request));
     }catch (InvalidPropertyException e){
         throw new InvalidPropertyException("Error trying to update a property: "+e.getMessage());
     }


    }
    @Override
    @Transactional
    public void delete(Long id) {
    try {
        Property p = getProperty(id);
        p.setSoftDeleted(true);
        propertyRepository.save(p);
    }catch (RuntimeException e){
        throw new InvalidPropertyException("Error delete Property: "+e.getMessage());
     }
    }
    @Override
    @Transactional
    public void patch(Long id, PropertyState request) {

        try{
            Property p = getProperty(id);
            User user = userService.getInfoUser();
            State j=stateRepository.findByName(request.getState());
            if (user.getId()==p.getUser().getId()){
                p.setStatus(PropertyStatus.valueOf(request.state.toUpperCase()));
                propertyRepository.save(p);
            }
        }catch (InvalidPropertyException e){
            throw new RuntimeException("Error trying to update bad state:"+ e.getMessage());
        }
    }


    private boolean chechListFile(List<MultipartFile>multipartFiles) {
      if(multipartFiles.isEmpty()){
          throw new RuntimeException("You must enter at least one file: ");
      }
        int c=0;
        for (MultipartFile m: multipartFiles ) {
            if (m.getOriginalFilename().isEmpty()||m.getOriginalFilename()==null){
                c++;
            }
        }
        if(c!=multipartFiles.size()){
            return false;
        }
     return true;
    }
}
