package com.s3.t.service;

import com.s3.t.model.entity.User;
import com.s3.t.repository.UserRepository;
import com.s3.t.service.abstraction.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public User getInfoUser() {
        Object userInstance = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            if (userInstance instanceof User) {
                String username = ((User) userInstance).getUsername();

            }
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found");
        }
        return userRepository.findByEmail(userInstance.toString());
    }
}
