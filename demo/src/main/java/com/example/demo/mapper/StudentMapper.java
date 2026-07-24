package com.example.demo.mapper;

import com.example.demo.dto.StudentRequestDTO;
import com.example.demo.dto.StudentResponseDTO;
import com.example.demo.entity.School;
import com.example.demo.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public Student toEntity(StudentRequestDTO dto, School school) {
        Student student = new Student();
        student.setName(dto.name());
        student.setEmail(dto.email());
        student.setSchool(school);
        return student;
    }

    public StudentResponseDTO toResponseDTO(Student student) {
        return new StudentResponseDTO(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getSchool().getId()
        );
    }
}
