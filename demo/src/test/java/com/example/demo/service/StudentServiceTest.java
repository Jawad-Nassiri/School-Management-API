package com.example.demo.service;

import com.example.demo.dto.StudentRequestDTO;
import com.example.demo.dto.StudentResponseDTO;
import com.example.demo.entity.School;
import com.example.demo.entity.Student;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.repository.SchoolRepository;
import com.example.demo.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class StudentServiceTest {
    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentMapper studentMapper;

    @Mock
    private SchoolRepository schoolRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_successfully_save_student() {
        StudentRequestDTO studentRequestDTO = new StudentRequestDTO(
                "leo",
                "leo@test.com",
                5
        );

        School school = new School();
        school.setId(5);
        school.setName("isoset");
        school.setAddress("paris 16");

        Student student = new Student();
        student.setId(100);
        student.setName("leo");
        student.setEmail("leo@test.com");
        student.setSchool(school);

        when(schoolRepository.findById(5))
                .thenReturn(Optional.of(school));
        when(studentMapper.toEntity(studentRequestDTO, school))
                .thenReturn(student);
        when(studentRepository.save(student))
                .thenReturn(student);
        when(studentMapper.toResponseDTO(student))
                .thenReturn(new StudentResponseDTO(
                        100,
                        "leo",
                        "leo@test.com",
                        5
                ));

        StudentResponseDTO studentResponseDTO = studentService.saveStudentInDb(studentRequestDTO);

        assertEquals(student.getId(), studentResponseDTO.id());
        assertEquals(studentRequestDTO.name(), studentResponseDTO.name());
        assertEquals(studentRequestDTO.email(), studentResponseDTO.email());
        assertEquals(studentRequestDTO.schoolId(), studentResponseDTO.schoolId());

        verify(schoolRepository).findById(5);
        verify(studentMapper).toEntity(studentRequestDTO, school);
        verify(studentRepository).save(student);
        verify(studentMapper).toResponseDTO(student);
    }


    @Test
    public void should_find_all_students(){

        StudentResponseDTO studentResponseDTO = new StudentResponseDTO(
                100,
                "leo",
                "leo@test.com",
                5
        );

        School school = new School();
        school.setId(5);
        school.setName("isoset");
        school.setAddress("paris 16");

        Student student = new Student();
        student.setId(100);
        student.setName("leo");
        student.setEmail("leo@test.com");
        student.setSchool(school);

        when(studentRepository.findAll())
                .thenReturn(List.of(student));
        when(studentMapper.toResponseDTO(student))
                .thenReturn(studentResponseDTO);

        List<StudentResponseDTO> studentResponseDTOS = studentService.findAllStudents();

        assertEquals(1, studentResponseDTOS.size());
        assertEquals(studentResponseDTO.id(), studentResponseDTOS.get(0).id());
        assertEquals(studentResponseDTO.name(), studentResponseDTOS.get(0).name());
        assertEquals(studentResponseDTO.email(), studentResponseDTOS.get(0).email());
        assertEquals(studentResponseDTO.schoolId(), studentResponseDTOS.get(0).schoolId());

        verify(studentRepository).findAll();
        verify(studentMapper).toResponseDTO(student);
    }
}