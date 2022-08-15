package com.s3.t.model.mapper;

import com.s3.t.model.entity.User;
import com.s3.t.model.request.UserRequest;
import com.s3.t.model.response.UserResponse;
import org.springframework.stereotype.Component;



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

    public UserResponse dtoToEntity(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        return response;

    }

}
