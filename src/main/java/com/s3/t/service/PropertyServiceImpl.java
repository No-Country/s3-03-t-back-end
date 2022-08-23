package com.s3.t.service;

import com.amazonaws.services.identitymanagement.model.EntityAlreadyExistsException;
import com.s3.t.exception.InvalidPropertyException;
import com.s3.t.model.entity.Location;
import com.s3.t.model.entity.Property;
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
          throw new EntityAlreadyExistsException("Error upgrade" + e.getMessage());
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
     }catch (RuntimeException e){
         throw new InvalidPropertyException("Error property update");
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
        throw new InvalidPropertyException("Error delete Property");
    }
    }

    @Override
    public void patch(Long id, PropertyState request) {
        Property p = getProperty(id);
        User user = userService.getInfoUser();
        try{
            if (user.getId()==p.getUser().getId()){
                stateRepository.findByName(request.getState());
               // propertyRepository.save(p);
            }
        }catch (InvalidPropertyException e){
            throw new RuntimeException("Usuario no autorizado");
        }
    }


    private boolean chechListFile(List<MultipartFile>multipartFiles) {
      if(multipartFiles.isEmpty()){
          throw new RuntimeException("Debe ingresar almenos un archivo");
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
