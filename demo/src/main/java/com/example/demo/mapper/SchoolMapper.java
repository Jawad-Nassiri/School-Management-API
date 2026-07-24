package com.example.demo.mapper;

import com.example.demo.dto.SchoolRequestDTO;
import com.example.demo.dto.SchoolResponseDTO;
import com.example.demo.entity.School;
import org.springframework.stereotype.Component;

@Component
public class SchoolMapper {

    public School toEntity(SchoolRequestDTO dto) {
        School school = new School();
        school.setName(dto.name());
        school.setAddress(dto.address());
        return school;
    }

    public SchoolResponseDTO toResponseDTO(School school) {
        return new SchoolResponseDTO(
                school.getId(),
                school.getName(),
                school.getAddress()
        );
    }
}