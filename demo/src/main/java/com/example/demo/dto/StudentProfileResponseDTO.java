package com.example.demo.dto;

import com.example.demo.entity.Student;

public record StudentProfileResponseDTO(
        Integer id,
        String bio,
        Integer studentId
) {
}
