package com.example.demo.mapper;

import com.example.demo.dto.StudentProfileRequestDTO;
import com.example.demo.dto.StudentProfileResponseDTO;
import com.example.demo.entity.Student;
import com.example.demo.entity.StudentProfile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentProfileMapperTest {
    private StudentProfileMapper studentProfileMapper;

    @BeforeEach
    void setUp() {
        studentProfileMapper = new StudentProfileMapper();
    }

    @Test
    public void shouldConvertRequestDTOToEntity() {
        StudentProfileRequestDTO dto = new StudentProfileRequestDTO("test student biography", 1);
        Student student = new Student();
        student.setId(1);

        StudentProfile studentProfile = studentProfileMapper.toEntity(dto, student);

        assertEquals(dto.bio(), studentProfile.getBio());
        assertEquals(dto.studentId(), studentProfile.getStudent().getId());

    }

    @Test
    public void shouldConvertEntityToResponseDTO() {
        Student student = new Student();
        student.setId(1);

        StudentProfile studentProfile = new StudentProfile();
        studentProfile.setId(10);
        studentProfile.setBio("Java student");
        studentProfile.setStudent(student);

        StudentProfileResponseDTO dto = studentProfileMapper.toResponseDTO(studentProfile);

        assertEquals(studentProfile.getId(), dto.id());
        assertEquals(studentProfile.getBio(), dto.bio());
        assertEquals(student.getId(), dto.studentId());
    }
}