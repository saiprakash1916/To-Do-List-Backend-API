package com.To_Do_List.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserRequestDTO {
    @NotBlank
    private String username;

    @Email
    @NotBlank
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRequestDTO(){}

    public UserRequestDTO(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
