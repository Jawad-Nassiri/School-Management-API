package com.example.demo.controller;

import com.example.demo.dto.CourseRequestDTO;
import com.example.demo.dto.CourseResponseDTO;
import com.example.demo.entity.Course;
import com.example.demo.service.CourseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @PostMapping("/courses")
    public CourseResponseDTO createCourse(@Valid @RequestBody CourseRequestDTO courseRequestDTO) {
        return courseService.saveCourseInDb(courseRequestDTO);
    }

    @GetMapping("/courses")
    public List<CourseResponseDTO> findAll() {
        return courseService.findAllCourses();
    }
}
