package com.s3.t.service;

import com.s3.t.exception.EntityNotFoundException;
import com.s3.t.model.entity.User;
import com.s3.t.model.mapper.UserMapper;
import com.s3.t.model.response.UserResponse;
import com.s3.t.repository.UserRepository;
import com.s3.t.service.abstraction.ImageService;
import com.s3.t.service.abstraction.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyServiceImpl.class);
    private final UserRepository userRepository;

    private ImageService imageService;

    private final UserMapper userMapper;

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
    public UserResponse getById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty() || user.get().isSoftDeleted()) {
            throw new EntityNotFoundException("User not found");
        }

        return userMapper.dtoToEntityUser(user.get());
    }

    @Override
    public void delete(Long id) {
        User user = getUser(id);
        user.setSoftDeleted(true);
        userRepository.save(user);
    }

    @Override
    public List<UserResponse> getAll() {
        List<User> users = userRepository.findAll();
        List<UserResponse> responses = new ArrayList<>();
        users.forEach(user -> {
            if(!user.isSoftDeleted()){
                responses.add(userMapper.dtoToEntityUser(user));
            }
        });

        return responses;
    }

    private User getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty() || user.get().isSoftDeleted()){
            throw new EntityNotFoundException("Location not found or has been deleted");
        }
        return user.get();
    }


}
