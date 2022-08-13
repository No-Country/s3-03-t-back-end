package com.s3.t.service.abstraction;

import com.s3.t.model.request.UserRegisterRequest;
import com.s3.t.model.response.UserRegisterResponse;

public interface AuthService {

    UserRegisterResponse register(UserRegisterRequest request);
}
