package com.s3.t.model.mapper;

import com.s3.t.model.entity.User;
import com.s3.t.model.request.UserRequest;
import com.s3.t.model.response.AuthResponse;
import com.s3.t.model.response.UserResponse;
import org.apache.catalina.LifecycleState;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class UserMapper {


    public User entityToDto(UserRequest request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setBirthDate(request.getBirthDate());
        user.setTelephone(request.getTelephone());
        user.setDni(request.getDni());
        user.setSoftDeleted(false);
        return user;
    }

    public AuthResponse dtoToEntity(User user) {
        AuthResponse response = new AuthResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setRole(user.getRole().getName());
        return response;

    }
    public UserResponse dtoToEntityUser(User user) {
        UserResponse response = new UserResponse() ;
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());

        return response;

    }
    public List<UserResponse> allUsers(List<User> user){
        List<UserResponse> respons  = new ArrayList<>();
        user.forEach(users -> {
            respons.add(dtoToEntityUser(users));
        });
        return respons;

    }

}
