package com.s3.t.service.abstraction;

import com.s3.t.model.entity.User;
import com.s3.t.model.request.UserRequest;
import com.s3.t.model.response.UserResponse;

import java.util.List;

public interface UserService {

    User getInfoUser() ;
    UserResponse findByEmail(String email);
    List<UserResponse> findAllUser();
    void deleteById(Long id);
    UserResponse update(Long id,User user);
}
