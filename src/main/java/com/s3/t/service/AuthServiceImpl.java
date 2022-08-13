package com.s3.t.service;

import com.s3.t.config.JwtToken;
import com.s3.t.model.entity.User;
import com.s3.t.model.mapper.UserMapper;
import com.s3.t.model.request.UserRegisterRequest;
import com.s3.t.model.response.UserRegisterResponse;
import com.s3.t.repository.UserRepository;
import com.s3.t.service.abstraction.AuthService;
import com.s3.t.service.abstraction.RoleService;
import com.s3.t.util.RolesEnum;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class AuthServiceImpl implements AuthService, UserDetailsService {

    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    private final JwtToken jwtToken;
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
    public UserRegisterResponse register(UserRegisterRequest request) {
        if(userRepository.findByEmail(request.getEmail()) != null){
            throw new RuntimeException("Email already Exist");
        }
        User user = userMapper.entityToDto(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(roleService.findBy(RolesEnum.USER.getFullRoleName()));
        User userCreate = userRepository.save(user);
        UserRegisterResponse response = userMapper.dtoToEntity(userCreate);
        response.setToken(jwtToken.generateAccessToken(userCreate));
        return response;
    }
}
