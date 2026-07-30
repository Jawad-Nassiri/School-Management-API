package com.example.demo.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StudentProfileRequestDTO(
        @NotBlank(message = "Bio must not be empty")
        String bio,

        @NotNull(message = "Student ID must not be null")
        Integer studentId
) {
}
