package com.s3.t.service.service;

import com.s3.t.model.request.UserRegister;
import com.s3.t.model.response.UserResponse;
import com.s3.t.repository.UserRepository;
import com.s3.t.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class userServiceImpl implements UserDetailsService, AuthService {
@Autowired
    UserRepository userRepository;

@Autowired



    public UserResponse register(UserRegister request ) throws Exception{
      if (userRepository.findByEmail(request.getEmail())!=null) {
            throw new Exception();
          }
   return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
