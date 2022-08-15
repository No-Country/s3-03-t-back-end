package com.s3.t.model.mapper;

import com.s3.t.model.entity.User;
import com.s3.t.model.request.UserRegister;
import com.s3.t.model.response.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

public User  userEntityToDto(UserRegister request) {
    User user = new User ();
    user.setDni(request.getDni());
    user.setEmail(request.getEmail());
    user.setAddress(request.getAddress());
    user.setBirth(request.getBirth());
    user.setFirstName(request.getFirstName());
    user.setLastName(request.getLastName());
    return user;
}
public UserResponse dtoToUser(User entity){
  UserResponse response = new UserResponse();
response.setId(entity.getId());
response.setEmail(response.getEmail());
    return response;
}




}
