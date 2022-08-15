package com.s3.t.service;

import com.s3.t.model.entity.User;
import com.s3.t.model.response.UserResponse;

import java.nio.file.NotLinkException;
import java.util.List;

public interface iUserService {

    User getInfoUser()throws NotLinkException;
    void delete (long id) throws  NoClassDefFoundError;
    UserResponse getById (Long id);
    List<UserResponse> getAllUsers();
}
