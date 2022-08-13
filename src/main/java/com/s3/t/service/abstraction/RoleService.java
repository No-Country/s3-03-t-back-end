package com.s3.t.service.abstraction;

import com.s3.t.model.entity.Role;

public interface RoleService {

    Role findBy(String fullRoleName);
}
