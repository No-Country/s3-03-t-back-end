package com.s3.t.service;

import com.s3.t.model.entity.Role;
import com.s3.t.repository.RoleRepository;
import com.s3.t.service.abstraction.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    @Override
    public Role findBy(String fullRoleName) {
        return roleRepository.findByName(fullRoleName);
    }
}
