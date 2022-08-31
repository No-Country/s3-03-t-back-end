package com.s3.t.service.abstraction;

import com.s3.t.model.entity.User;
import com.s3.t.model.request.PropertyRequest;
import com.s3.t.model.request.UserRequest;
import com.s3.t.model.response.UserResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    User getInfoUser() ;
    UserResponse getById(Long id);
    void delete(Long id);
    List<UserResponse> getAll();
}
