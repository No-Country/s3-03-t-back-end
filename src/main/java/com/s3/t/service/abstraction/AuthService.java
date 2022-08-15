package com.s3.t.service.abstraction;

import com.s3.t.model.request.AuthRequest;
import com.s3.t.model.request.UserRequest;
import com.s3.t.model.response.AuthResponse;
import com.s3.t.model.response.UserResponse;

public interface AuthService {

    UserResponse register(UserRequest request);

    AuthResponse login(AuthRequest request);
}
