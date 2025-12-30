package com.To_Do_List.service.impl;

import com.To_Do_List.entity.User;
import com.To_Do_List.exception.ResourceNotFoundException;
import com.To_Do_List.repository.UserRepository;
import com.To_Do_List.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
    }

    @Override
    public boolean existingUserId(Long userId) {
        return userRepository.existsById(userId);
    }
}
