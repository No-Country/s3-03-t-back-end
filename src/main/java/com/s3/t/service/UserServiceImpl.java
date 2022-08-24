package com.s3.t.service;

import com.s3.t.model.entity.User;
import com.s3.t.model.mapper.UserMapper;
import com.s3.t.model.request.UserRequest;
import com.s3.t.model.response.UserResponse;
import com.s3.t.repository.UserRepository;
import com.s3.t.service.abstraction.UserService;
import com.s3.t.util.Mensaje;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper mapper ;
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

    @Override
    public UserResponse findByEmail(String email) {
     Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));
        return mapper.dtoToEntityUser(user.get());
    }

    @Override
    public List<UserResponse> findAllUser() {
        List<User> user = userRepository.findAll();
        if (user.isEmpty())
            throw new RuntimeException("is Empty");
        return  mapper.allUsers(user);
    }

    @Override
    public void deleteById(Long id) {
         userRepository.deleteById(id);
    }

    @Override
    public UserResponse update(Long id,User user){
        Optional<User> us= userRepository.findById(id);
          mapper.dtoToEntityUser(user);
         userRepository.save(user);
        return mapper.dtoToEntityUser(user)  ;
    }
}
