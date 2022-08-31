package com.s3.t.controller;

import com.s3.t.model.entity.User;
import com.s3.t.model.request.PropertyRequest;
import com.s3.t.model.request.UserRequest;
import com.s3.t.model.response.UserResponse;
import com.s3.t.service.abstraction.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable Long id){
        UserResponse response = userService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponse>> getAll(){
        List<UserResponse> response = userService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }




}
