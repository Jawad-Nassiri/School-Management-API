package com.example.demo.controller;

import com.example.demo.entity.StudentProfile;
import com.example.demo.service.StudentProfileService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class StudentProfileController {
    private final StudentProfileService studentProfileService;

    @PostMapping("/student-profiles")
    public StudentProfile createStudentProfile(@RequestBody StudentProfile studentProfile) {
        return studentProfileService.saveStudentProfileInDb(studentProfile);
    }

    @GetMapping("/student-profiles")
    public List<StudentProfile> findAll() {
        return studentProfileService.findAllStudentProfiles();
    }
}
