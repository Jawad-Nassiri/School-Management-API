package com.example.demo.mapper;

import com.example.demo.dto.StudentRequestDTO;
import com.example.demo.dto.StudentResponseDTO;
import com.example.demo.entity.School;
import com.example.demo.entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class StudentMapperTest {
    private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        studentMapper = new StudentMapper();
    }

    @Test
    public void shouldConvertStudentDTOToEntity() {
        StudentRequestDTO dto = new StudentRequestDTO("leo", "leo.doran@test.com", 1);
        School school = new School();

        Student student = studentMapper.toEntity(dto, school);

        assertEquals(dto.name(), student.getName());
        assertEquals(dto.email(), student.getEmail());

    }

    @Test
    public void shouldConvertEntityToResponseDto() {
        Student student = new Student();
        School school = new School();
        student.setId(1);
        student.setName("leo");
        student.setEmail("leo@test.com");
        student.setSchool(school);

        StudentResponseDTO studentResponseDTO = studentMapper.toResponseDTO(student);

        assertEquals(student.getId(), studentResponseDTO.id());
        assertEquals(student.getName(), studentResponseDTO.name());
        assertEquals(student.getEmail(), studentResponseDTO.email());
        assertEquals(school, student.getSchool());

    }

}