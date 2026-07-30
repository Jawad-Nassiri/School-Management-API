package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StudentRequestDTO(
        @NotBlank(message = "Student name must not be empty")
        String name,

        @NotBlank(message = "Email must not be empty")
        @Email(message = "Email format is not valid")
        String email,

        @NotNull(message = "School ID must not be null")
        Integer schoolId
) {}
