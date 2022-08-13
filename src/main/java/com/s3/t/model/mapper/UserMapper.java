package com.s3.t.model.mapper;

import com.s3.t.model.entity.User;
import com.s3.t.model.request.UserRegisterRequest;
import com.s3.t.model.response.UserRegisterResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User entityToDto(UserRegisterRequest request) {
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

    public UserRegisterResponse dtoToEntity(User user) {
        UserRegisterResponse response = new UserRegisterResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        return response;
    }
}
