package com.s3.t.service;

import com.s3.t.model.entity.User;
import com.s3.t.model.mapper.UserMapper;
import com.s3.t.model.request.AuthRequest;
import com.s3.t.model.request.UserRequest;
import com.s3.t.model.response.AuthResponse;
import com.s3.t.model.response.UserResponse;
import com.s3.t.repository.UserRepository;
import com.s3.t.service.abstraction.AuthService;
import com.s3.t.service.abstraction.RoleService;
import com.s3.t.util.RolesEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService, UserDetailsService {

    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtToken;

    @Autowired
    public AuthenticationManager authenticationManager;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return  getUser(username);
    }

    private User getUser(String username) {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new RuntimeException("The email entered is not correct.");
        }
        if(!user.isEnabled()){
            throw new RuntimeException("The user is deleted.");
        }
        return user;
    }

    @Override
    public UserResponse register(UserRequest request) {
        if(userRepository.findByEmail(request.getEmail()) != null){
            throw new RuntimeException("Email already Exist");
        }
        User user = userMapper.entityToDto(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(roleService.findBy(RolesEnum.USER.getFullRoleName()));
        User userCreate = userRepository.save(user);
        UserResponse response = userMapper.dtoToEntity(userCreate);
        response.setToken(jwtToken.generateToken(userCreate));
        return response;
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        User user = getUser(request.getEmail());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        return new AuthResponse(jwtToken.generateToken(user), user.getEmail(), user.getRole().getName(),user.getFirstName(),user.getLastName());
    }

}
