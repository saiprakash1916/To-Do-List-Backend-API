package com.To_Do_List.service.impl;

import com.To_Do_List.dto.UserRequestDTO;
import com.To_Do_List.dto.UserResponseDTO;
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

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        User user = new User();
        user.setUsername(userRequestDTO.getUsername());
        user.setEmail(userRequestDTO.getEmail());

        User savedUser = userRepository.save(user);
        return mapToResponseDTO(savedUser);
    }

    @Override
    public UserResponseDTO getUserByIdResponse(Long userId) {
        User user = getUserById(userId);
        return mapToResponseDTO(user);
    }

    private UserResponseDTO mapToResponseDTO(User user){
        UserResponseDTO userResponseDTO = new UserResponseDTO();

        userResponseDTO.setId(user.getId());
        userResponseDTO.setUsername(user.getUsername());
        userResponseDTO.setEmail(userResponseDTO.getEmail());

        return userResponseDTO;
    }
}
