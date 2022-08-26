package com.s3.t.service.abstraction;

import com.s3.t.model.entity.User;
import com.s3.t.model.response.UserResponse;

public interface UserService {

    User getInfoUser() ;
    UserResponse getById(Long id);
}
