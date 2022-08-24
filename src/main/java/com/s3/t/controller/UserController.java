package com.s3.t.controller;

import com.s3.t.model.entity.User;
import com.s3.t.model.response.UserResponse;
import com.s3.t.service.abstraction.UserService;
import com.s3.t.util.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;


@RestController

@RequestMapping("/api/v1/user")

public class UserController {

    @Autowired
    private UserService userService;
    //buscar por mail
    @GetMapping("/serch/{mail}")
    public ResponseEntity<?> findUserByEmail(@PathVariable String mail) {
        return new ResponseEntity(userService.findByEmail(mail), HttpStatus.ACCEPTED);
    }
    //borrar por id
    @DeleteMapping ("delete-user/{id}")
    public  ResponseEntity <?> deleteUser(@PathVariable Long id){
        userService.deleteById(id);
          return new ResponseEntity<>(new Mensaje("Deleted user"), HttpStatus.ACCEPTED);
    }

    //mostrar todos los usuarios
    @GetMapping("/find-all-user")
            public ResponseEntity <List<UserResponse>> allUser (){
        return new ResponseEntity(userService.findAllUser(),HttpStatus.ACCEPTED);
    }
    // actualizar usuario
    @PutMapping("/update-user/{id}")
    public ResponseEntity<UserResponse>updateUser(@PathVariable Long id,User user)
    {
        userService.update(id,user);


        return new ResponseEntity(new Mensaje("user update"),HttpStatus.ACCEPTED);
    }

}