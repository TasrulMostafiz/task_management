package com.example.task_management.dto;

import com.example.task_management.database.entity.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class UserDTO {
    private String firstname;
    private String lastname;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
}
