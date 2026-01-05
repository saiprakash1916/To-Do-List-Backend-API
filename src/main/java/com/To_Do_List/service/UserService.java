package com.To_Do_List.service;

import com.To_Do_List.dto.UserRequestDTO;
import com.To_Do_List.dto.UserResponseDTO;
import com.To_Do_List.entity.User;

public interface UserService {

    User getUserById(Long userId);

    boolean existingUserId(Long userId);

    UserResponseDTO createUser(UserRequestDTO userRequestDTO);

    UserResponseDTO getUserByIdResponse(Long userId);
}
