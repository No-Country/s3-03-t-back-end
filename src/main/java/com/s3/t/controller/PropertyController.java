package com.s3.t.controller;


import com.s3.t.model.request.PropertyRequest;
import com.s3.t.model.response.PropertyResponse;
import com.s3.t.service.abstraction.PropertyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/property")
@Api(value = "Property Controller", description = "property functionalities")
@RequiredArgsConstructor
public class PropertyController {
    public final PropertyService propertyService;

    @ApiOperation(value = "Registration property", notes = "Returns proeprty created" )
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public PropertyResponse upload(
            @RequestPart(value="postimages",required=false) List<MultipartFile> postImage,
            @RequestPart (value="property", required=true) PropertyRequest request){
        return propertyService.add(postImage,request);
    }
    @GetMapping
    public List<PropertyResponse> getAllProperty(){
        return propertyService.getAll();
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
}
