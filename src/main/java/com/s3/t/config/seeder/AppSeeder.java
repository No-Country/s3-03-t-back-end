package com.s3.t.config.seeder;

import com.s3.t.model.entity.Location;
import com.s3.t.model.entity.Role;
import com.s3.t.repository.LocationRepository;
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

    private static final List<String> LOCATIONS = List.of("Escobar","Alberti","Azul","Berisso","La Plata","Caballito","Flores","Constitución","Palermo","Nuñez");
    private static final String PROVINCE = "Buenos Aires";
    private static final String COUNTRY = "Argentina";

    private final LocationRepository locationRepository;
    private final RoleRepository roleRepository;

    @EventListener
    public void seed(ContextRefreshedEvent event){
        List<Role> roles = roleRepository.findAll();
        if(roles.isEmpty()){
            createRoles();
        }

        createLocations();
    }

    private void createLocations() {
        if(locationRepository.count() < 10){
            for (int i = 0; i < 10; i++){
                Location l = new Location();
                l.setId((long) i);
                l.setLocation(LOCATIONS.get(i));
                l.setProvince(PROVINCE);
                l.setCountry(COUNTRY);
                l.setSoftDeleted(false);
                locationRepository.save(l);
            }
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
