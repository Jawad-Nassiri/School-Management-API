package com.example.demo.mapper;


import com.example.demo.dto.EnrollmentRequestDTO;
import com.example.demo.entity.Course;
import com.example.demo.entity.Enrollment;
import com.example.demo.entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;



class EnrollmentMapperTest {
    private EnrollmentMapper enrollmentMapper;

    @BeforeEach
    void setUp() {
        enrollmentMapper = new EnrollmentMapper();
    }

    @Test
    public void shouldConvertRequestDTOtoEntity() {
        EnrollmentRequestDTO dto = new EnrollmentRequestDTO(
                LocalDate.parse("2021-07-16"),
                1,
                2
        );

        Student student = new Student();
        student.setId(1);
        Course course = new Course();
        course.setId(2);

        Enrollment enrollment = enrollmentMapper.toEntity(dto, student, course);

        assertEquals(dto.enrollmentDate(), enrollment.getEnrollmentDate());
        assertEquals(dto.studentId(), enrollment.getStudent().getId());
        assertEquals(dto.courseId(), enrollment.getCourse().getId());
    }
}