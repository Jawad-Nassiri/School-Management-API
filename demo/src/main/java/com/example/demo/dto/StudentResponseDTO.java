package com.example.demo.dto;

public record StudentResponseDTO(
        Integer id,
        String name,
        String email,
        Integer schoolId
) {
}
