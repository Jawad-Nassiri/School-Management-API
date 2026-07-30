package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;

public record SchoolRequestDTO(
        @NotBlank(message = "Name must not be empty")
        String name,

        @NotBlank(message = "Address must not be empty")
        String address
) {
}
