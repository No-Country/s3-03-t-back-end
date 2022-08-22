package com.s3.t.controller;

import com.s3.t.model.request.PropertyRequest;
import com.s3.t.model.response.PropertyResponse;
import com.s3.t.service.PropertyServiceImpl;
import com.s3.t.service.abstraction.PropertyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ResponseHeader;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/property")
@Api(value = "Property Controller", description = "property functionalities")
@RequiredArgsConstructor
public class PropertyController {
    public final PropertyService propertyService;
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyServiceImpl.class);
    @ApiOperation(value = "Registration property", notes = "Returns proeprty created" )
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public PropertyResponse upload(
            @RequestPart(value="postimages",required=false) List<MultipartFile> postImage,
            @RequestPart (value="property", required=true) PropertyRequest request){
        return propertyService.add(postImage,request);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PropertyResponse> getAll(){
        return propertyService.getAll();
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PropertyResponse findId(@PathVariable Long id){
           return propertyService.getById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody PropertyRequest request){
        propertyService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        propertyService.delete(id);
    }

 /*  @ResponseStatus(value=HttpStatus.NOT_FOUND)
   @ExceptionHandler(Exception.class)
    public void nullPointerHandler(){

        throw new RuntimeException("No se encontro el id");
    }*/
}
