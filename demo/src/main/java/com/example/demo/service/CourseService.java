package com.example.demo.service;

import com.example.demo.dto.CourseRequestDTO;
import com.example.demo.dto.CourseResponseDTO;
import com.example.demo.entity.Course;
import com.example.demo.mapper.CourseMapper;
import com.example.demo.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseResponseDTO saveCourseInDb(CourseRequestDTO courseRequestDTO) {
        Course course = courseMapper.toEntity(courseRequestDTO);
        Course savedCourse = courseRepository.save(course);
        return courseMapper.toResponse(savedCourse);
    }

    public List<CourseResponseDTO> findAllCourses() {
        return courseRepository.findAll().stream().map(courseMapper::toResponse).toList();
    }

}
