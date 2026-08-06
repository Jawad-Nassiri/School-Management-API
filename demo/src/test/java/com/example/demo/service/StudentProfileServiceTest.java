package com.example.demo.service;

import com.example.demo.dto.StudentProfileRequestDTO;
import com.example.demo.dto.StudentProfileResponseDTO;
import com.example.demo.entity.Student;
import com.example.demo.entity.StudentProfile;
import com.example.demo.mapper.StudentProfileMapper;
import com.example.demo.repository.StudentProfileRepository;
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

class StudentProfileServiceTest {
    @InjectMocks
    private StudentProfileService studentProfileService;

    @Mock
    private StudentProfileRepository studentProfileRepository;
    @Mock
    private StudentProfileMapper studentProfileMapper;
    @Mock
    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_successfully_save_student_profile() {
        StudentProfileRequestDTO studentProfileRequestDTO = new StudentProfileRequestDTO(
                "Spring boot student...",
                50
        );

        Student student = new Student();
        student.setId(50);

        StudentProfile studentProfile = new StudentProfile();
        studentProfile.setId(100);
        studentProfile.setBio("Spring boot student...");
        studentProfile.setStudent(student);

        when(studentRepository.findById(50))
                .thenReturn(Optional.of(student));
        when(studentProfileMapper.toEntity(studentProfileRequestDTO, student))
                .thenReturn(studentProfile);
        when(studentProfileRepository.save(studentProfile))
                .thenReturn(studentProfile);
        when(studentProfileMapper.toResponseDTO(studentProfile))
                .thenReturn(new StudentProfileResponseDTO(
                        100,
                        "Spring boot student...",
                        50
                ));

        StudentProfileResponseDTO studentProfileResponseDTO = studentProfileService.saveStudentProfileInDb(studentProfileRequestDTO);

        assertEquals(studentProfile.getId(), studentProfileResponseDTO.id());
        assertEquals(studentProfileRequestDTO.bio(), studentProfileResponseDTO.bio());
        assertEquals(studentProfileRequestDTO.studentId(), studentProfileResponseDTO.studentId());

        verify(studentRepository).findById(50);
        verify(studentProfileMapper).toEntity(studentProfileRequestDTO, student);
        verify(studentProfileRepository).save(studentProfile);
        verify(studentProfileMapper).toResponseDTO(studentProfile);

    }

    @Test
    public void should_find_all_student_profiles() {
        Student student = new Student();
        student.setId(50);

        StudentProfile studentProfile = new StudentProfile();
        studentProfile.setId(100);
        studentProfile.setBio("Spring boot student...");
        studentProfile.setStudent(student);

        StudentProfileResponseDTO studentProfileResponseDTO = new StudentProfileResponseDTO(
                100,
                "Spring boot student...",
                50
        );

        when(studentProfileRepository.findAll())
                .thenReturn(List.of(studentProfile));
        when(studentProfileMapper.toResponseDTO(studentProfile))
                .thenReturn(studentProfileResponseDTO);

        List<StudentProfileResponseDTO> studentProfileResponseDTOS = studentProfileService.findAllStudentProfiles();

        assertEquals(1, studentProfileResponseDTOS.size());
        assertEquals(studentProfileResponseDTO.id(), studentProfileResponseDTOS.get(0).id());
        assertEquals(studentProfileResponseDTO.bio(), studentProfileResponseDTOS.get(0).bio());
        assertEquals(studentProfileResponseDTO.studentId(), studentProfileResponseDTOS.get(0).studentId());

        verify(studentProfileRepository).findAll();
        verify(studentProfileMapper).toResponseDTO(studentProfile);

    }
}