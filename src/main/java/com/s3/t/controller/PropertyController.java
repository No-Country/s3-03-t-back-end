package com.s3.t.controller;

import com.s3.t.model.entity.Property;
import com.s3.t.model.request.PropertyRequest;
import com.s3.t.model.request.UserRequest;
import com.s3.t.model.response.PropertyResponse;
import com.s3.t.model.response.UserResponse;
import com.s3.t.service.abstraction.PropertyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/property")
@Api(value = "Property Controller", description = "property functionalities")
@RequiredArgsConstructor
public class PropertyController {
    public final PropertyService propertyService;

    @ApiOperation(value = "Registration property", notes = "Returns proeprty created" )
    @PostMapping("/upload")
    public ResponseEntity<PropertyResponse> upload(@Valid @RequestBody PropertyRequest request){
        PropertyResponse response = propertyService.upload(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
