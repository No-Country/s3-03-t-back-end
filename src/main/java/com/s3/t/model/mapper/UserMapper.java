package com.s3.t.model.mapper;

import com.s3.t.model.entity.User;
import com.s3.t.model.request.UserRequest;
import com.s3.t.model.response.AuthResponse;
import com.s3.t.model.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class UserMapper {

    private final PropertyMapper propertyMapper;
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
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setPropertyResponseList(user.getProperties().stream().map(property ->
                propertyMapper.responseToProperty(property)).collect(Collectors.toList()));
        return response;
    }
}
