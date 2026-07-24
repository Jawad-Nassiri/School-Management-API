package com.example.demo.dto;

import com.example.demo.entity.Student;

public record StudentProfileRequestDTO(
        String bio,
        Integer studentId
) {
}
