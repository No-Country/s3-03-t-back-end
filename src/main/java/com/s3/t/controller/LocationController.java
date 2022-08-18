package com.s3.t.controller;

import com.s3.t.model.request.LocationRequest;
import com.s3.t.model.response.LocationResponse;
import com.s3.t.service.abstraction.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;

@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/location")
public class LocationController {

    private final LocationService service;

    @PostMapping("/save")
    public ResponseEntity<LocationResponse> save(@Valid @RequestBody LocationRequest request){
        LocationResponse response = service.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationResponse> getBy(@PathVariable Long id){
        LocationResponse response = service.getBy(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
