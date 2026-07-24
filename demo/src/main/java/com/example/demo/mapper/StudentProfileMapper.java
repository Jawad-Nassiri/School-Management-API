package com.example.demo.mapper;

import com.example.demo.dto.StudentProfileRequestDTO;
import com.example.demo.dto.StudentProfileResponseDTO;
import com.example.demo.dto.StudentResponseDTO;
import com.example.demo.entity.Student;
import com.example.demo.entity.StudentProfile;
import org.springframework.stereotype.Component;

@Component
public class StudentProfileMapper {

    public StudentProfile toEntity(StudentProfileRequestDTO dto, Student student) {
        StudentProfile studentProfile = new StudentProfile();
        studentProfile.setBio(dto.bio());
        studentProfile.setStudent(student);
        return studentProfile;
    }

    public StudentProfileResponseDTO toResponseDTO(StudentProfile studentProfile) {
        return new StudentProfileResponseDTO (
                studentProfile.getId(),
                studentProfile.getBio(),
                studentProfile.getStudent().getId()
        );
    }
}
