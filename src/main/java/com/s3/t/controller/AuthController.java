package com.s3.t.controller;

import com.s3.t.model.request.AuthRequest;
import com.s3.t.model.request.UserRequest;
import com.s3.t.model.response.AuthResponse;
import com.s3.t.service.abstraction.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
@Api(value = "Authenticate Controller", description = "Authentication handler login/register")
public class AuthController {

    @Autowired
    private AuthService authService;
    @ApiOperation(value = "Registration method", notes = "Returns a registered user" )
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody UserRequest request){
        AuthResponse response = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @ApiOperation(value = "User login to the system", notes = "Returns a login user" )
    @PostMapping("/login")
    private ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request){
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/logout")
    public ResponseEntity<?> logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        System.out.println("Cerrado correcto");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
