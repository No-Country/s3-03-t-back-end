package com.s3.t.controller;

import com.s3.t.model.request.LocationRequest;
import com.s3.t.model.response.LocationResponse;
import com.s3.t.service.abstraction.LocationService;
import com.sun.xml.bind.v2.TODO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/location")
@Api(value = "Location Controller", description = "Crud Location")
public class LocationController {

    private final LocationService service;

    @ApiOperation(value = "Create method", notes = "Returns a location" )
    @PostMapping("/save")
    public ResponseEntity<LocationResponse> save(@Valid @RequestBody LocationRequest request){
        LocationResponse response = service.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @ApiOperation(value = "Get by id method", notes = "Returns a Location and properties" )
    @GetMapping("/{id}")
    public ResponseEntity<LocationResponse> getBy(@PathVariable Long id){
        LocationResponse response = service.getBy(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // Devuelve la localidad con sus propiedades, filtra por localidad o provincia.
    @ApiOperation(value = "Filter method", notes = "Returns a Location and properties" )
    @GetMapping("/filter")
    public ResponseEntity<List<LocationResponse>> findByLocationOrProvince(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String province
    ){
        List<LocationResponse> locationResponses = service.findByLocationOrProvince(location,province);
        return ResponseEntity.status(HttpStatus.OK).body(locationResponses);
    }
    @ApiOperation(value = "Deleted method", notes = "Returns 204 no content" )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleted(@PathVariable Long id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocationResponse> update(@PathVariable Long id, @RequestBody LocationRequest request){
        LocationResponse response = service.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }










}
