package com.example.demo.service;

import com.example.demo.dto.EnrollmentRequestDTO;
import com.example.demo.dto.EnrollmentResponseDTO;
import com.example.demo.entity.Course;
import com.example.demo.entity.Enrollment;
import com.example.demo.entity.Student;
import com.example.demo.mapper.EnrollmentMapper;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.EnrollmentRepository;
import com.example.demo.repository.StudentRepository;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class EnrollmentServiceTest {
    @InjectMocks
    private EnrollmentService enrollmentService;

    @Mock
    private EnrollmentRepository enrollmentRepository;
    @Mock
    private EnrollmentMapper enrollmentMapper;
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private CourseRepository courseRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_successfully_save_enrollment() {
        EnrollmentRequestDTO enrollmentRequestDTO = new EnrollmentRequestDTO(
                LocalDate.parse("2021-01-01"),
                10,
                20
        );

        Student student = new Student();
        student.setId(10);

        Course course = new Course();
        course.setId(20);

        Enrollment enrollment = new Enrollment();
        enrollment.setId(30);
        enrollment.setEnrollmentDate(LocalDate.parse("2021-01-01"));
        enrollment.setStudent(student);
        enrollment.setCourse(course);

        when(studentRepository.findById(10))
                .thenReturn(Optional.of(student));
        when(courseRepository.findById(20))
                .thenReturn(Optional.of(course));
        when(enrollmentMapper.toEntity(enrollmentRequestDTO, student, course))
                .thenReturn(enrollment);
        when(enrollmentRepository.save(enrollment))
                .thenReturn(enrollment);
        when(enrollmentMapper.toResponse(enrollment))
                .thenReturn(new EnrollmentResponseDTO(
                        30,
                        LocalDate.parse("2021-01-01"),
                        10,
                        20
                ));

        EnrollmentResponseDTO enrollmentResponseDTO = enrollmentService.saveEnrollmentInDb(enrollmentRequestDTO);

        assertEquals(enrollment.getId(), enrollmentResponseDTO.id());
        assertEquals(enrollmentRequestDTO.enrollmentDate(), enrollmentResponseDTO.enrollmentDate());
        assertEquals(enrollmentRequestDTO.studentId(), enrollmentResponseDTO.studentId());
        assertEquals(enrollmentRequestDTO.courseId(), enrollmentResponseDTO.courseId());


        verify(studentRepository).findById(10);
        verify(courseRepository).findById(20);
        verify(enrollmentMapper).toEntity(enrollmentRequestDTO, student, course);
        verify(enrollmentRepository).save(enrollment);
        verify(enrollmentMapper).toResponse(enrollment);
    }

    @Test
    public void should_find_all_enrollments() {
        Student student = new Student();
        student.setId(10);

        Course course = new Course();
        course.setId(20);

        Enrollment enrollment = new Enrollment();
        enrollment.setId(30);
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrollmentDate(LocalDate.parse("2021-01-01"));

        EnrollmentResponseDTO enrollmentResponseDTO = new EnrollmentResponseDTO(
                30,
                LocalDate.parse("2021-01-01"),
                10,
                20
        );

        when(enrollmentRepository.findAll())
                .thenReturn(List.of(enrollment));

        when(enrollmentMapper.toResponse(enrollment))
                .thenReturn(enrollmentResponseDTO);

        List<EnrollmentResponseDTO> result = enrollmentService.findAllEnrollments();

        assertEquals(1, result.size());
        assertEquals(enrollmentResponseDTO.id(), result.get(0).id());
        assertEquals(enrollmentResponseDTO.studentId(), result.get(0).studentId());
        assertEquals(enrollmentResponseDTO.courseId(), result.get(0).courseId());

        verify(enrollmentRepository).findAll();
        verify(enrollmentMapper).toResponse(enrollment);
    }
}