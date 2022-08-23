package com.s3.t.service.abstraction;

import com.s3.t.model.request.AuthRequest;
import com.s3.t.model.request.UserRequest;
import com.s3.t.model.response.AuthResponse;
import com.s3.t.model.response.UserResponse;
import com.s3.t.service.AwsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface AuthService {
    AuthResponse register(UserRequest request);

    AuthResponse login(AuthRequest request);
}
