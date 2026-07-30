package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;

public record CourseRequestDTO(
        @NotBlank(message = "Course name must not be empty")
        String name,

        @NotBlank(message = "Course description must not be empty")
        String description
) {
}
