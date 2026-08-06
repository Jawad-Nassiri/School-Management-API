package com.example.demo.service;

import com.example.demo.dto.CourseRequestDTO;
import com.example.demo.dto.CourseResponseDTO;
import com.example.demo.entity.Course;
import com.example.demo.mapper.CourseMapper;
import com.example.demo.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CourseServiceTest {
    @InjectMocks
    private CourseService courseService;

    @Mock
    private CourseRepository courseRepository;
    @Mock
    private CourseMapper courseMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_successfully_save_course() {
        CourseRequestDTO courseRequestDTO = new CourseRequestDTO(
                "Spring Boot",
                "Mockito for services ..."
        );

        Course course = new Course();
        course.setId(20);
        course.setName("Spring Boot");
        course.setDescription("Mockito for services ...");

        when(courseMapper.toEntity(courseRequestDTO))
                .thenReturn(course);
        when(courseRepository.save(course))
                .thenReturn(course);
        when(courseMapper.toResponse(course))
                .thenReturn(new CourseResponseDTO(
                        20,
                        "Spring Boot",
                        "Mockito for services ..."
                ));

        CourseResponseDTO courseResponseDTO = courseService.saveCourseInDb(courseRequestDTO);

        assertEquals(course.getId(), courseResponseDTO.id());
        assertEquals(courseRequestDTO.name(), courseResponseDTO.name());
        assertEquals(courseRequestDTO.description(), courseResponseDTO.description());


        verify(courseMapper).toEntity(courseRequestDTO);
        verify(courseRepository).save(course);
        verify(courseMapper).toResponse(course);
    }

    @Test
    public void should_find_all_courses() {
        Course course = new Course();
        course.setId(20);
        course.setName("Spring Boot");
        course.setDescription("Mockito for services ...");

        CourseResponseDTO courseResponseDTO = new CourseResponseDTO(
                20,
                "Spring Boot",
                "Mockito for services ..."
        );


        when(courseRepository.findAll())
                .thenReturn(List.of(course));
        when(courseMapper.toResponse(course))
                .thenReturn(courseResponseDTO);

        List<CourseResponseDTO> courseResponseDTOS = courseService.findAllCourses();

        assertEquals(1, courseResponseDTOS.size());
        assertEquals(courseResponseDTO.id(), courseResponseDTOS.get(0).id());
        assertEquals(courseResponseDTO.name(), courseResponseDTOS.get(0).name());
        assertEquals(courseResponseDTO.description(), courseResponseDTOS.get(0).description());

        verify(courseRepository).findAll();
        verify(courseMapper).toResponse(course);
    }
}