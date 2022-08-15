package com.s3.t.config.seeder;

import com.s3.t.model.entity.Role;
import com.s3.t.repository.RoleRepository;
import com.s3.t.util.RolesEnum;
import lombok.AllArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@Service
public class AppSeeder {

    private final RoleRepository roleRepository;

    @EventListener
    public void seed(ContextRefreshedEvent event){
        List<Role> roles = roleRepository.findAll();
        if(roles.isEmpty()){
            createRoles();
        }
    }
    private void createRoles() {
        createRole(1L, RolesEnum.ADMIN);
        createRole(2L, RolesEnum.USER);
    }

    private void createRole(long id, RolesEnum rolesEnum) {
        Role role = new Role();
        role.setId(id);
        role.setName(rolesEnum.getFullRoleName());
        role.setDescription(rolesEnum.getName());
        role.setTimestamp(new Timestamp(System.currentTimeMillis()));
        roleRepository.save(role);
    }
}
