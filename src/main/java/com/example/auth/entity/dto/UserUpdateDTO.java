package com.example.auth.entity.dto;

import com.example.auth.entity.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateDTO {
    private String email;
    private String firstName;
    private String lastName;
    private Role role;
}
