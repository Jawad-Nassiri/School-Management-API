package com.example.demo.mapper;

import com.example.demo.dto.CourseRequestDTO;
import com.example.demo.dto.CourseResponseDTO;
import com.example.demo.entity.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public Course toEntity(CourseRequestDTO dto) {
        Course course = new Course();
        course.setName(dto.name());
        course.setDescription(dto.description());
        return course;
    }

    public CourseResponseDTO toResponse(Course course) {
        return new CourseResponseDTO(
                course.getId(),
                course.getName(),
                course.getDescription()
        );
    }
}
