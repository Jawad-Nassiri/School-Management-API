package com.example.demo.controller;


import com.example.demo.entity.School;
import com.example.demo.service.SchoolService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SchoolController {
    private final SchoolService schoolService;

    @PostMapping("/school")
    public School createSchool(@RequestBody School school) {
        return schoolService.saveSchoolInDb(school);
    }
}
