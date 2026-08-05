package com.example.demo.mapper;


import com.example.demo.dto.CourseRequestDTO;
import com.example.demo.dto.CourseResponseDTO;
import com.example.demo.entity.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CourseMapperTest {
    private CourseMapper courseMapper;

    @BeforeEach
    void setUp() {
        courseMapper = new CourseMapper();
    }

    @Test
    public void shouldConvertRequestDtoToEntity() {
        CourseRequestDTO dto = new CourseRequestDTO("spring boot", "JUnit course");
        Course course = courseMapper.toEntity(dto);

        assertEquals(dto.name(), course.getName());
        assertEquals(dto.description(), course.getDescription());
    }

    @Test
    public void shouldConvertEntityToResponseDto() {
        Course course = new Course();
        course.setId(1);
        course.setName("spring boot");
        course.setDescription("JUnit course");

        CourseResponseDTO courseResponseDTO = courseMapper.toResponse(course);

        assertEquals(course.getId(), courseResponseDTO.id());
        assertEquals(course.getName(), courseResponseDTO.name());
        assertEquals(course.getDescription(), courseResponseDTO.description());
    }

}