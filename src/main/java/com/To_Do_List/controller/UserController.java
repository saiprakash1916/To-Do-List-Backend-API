package com.To_Do_List.controller;

import com.To_Do_List.dto.UserRequestDTO;
import com.To_Do_List.dto.UserResponseDTO;
import com.To_Do_List.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO createUser(@Valid @RequestBody UserRequestDTO userRequestDTO){
        System.out.println("User controller hit");
        return userService.createUser(userRequestDTO);
    }

    @GetMapping("/{userId}")
    public UserResponseDTO getUserById(@PathVariable Long userId){
        return userService.getUserByIdResponse(userId);
    }
}
