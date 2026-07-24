package com.example.demo.controller;

import com.example.demo.dto.StudentProfileRequestDTO;
import com.example.demo.dto.StudentProfileResponseDTO;
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
    public StudentProfileResponseDTO createStudentProfile(@RequestBody StudentProfileRequestDTO studentProfileRequestDTO) {
        return studentProfileService.saveStudentProfileInDb(studentProfileRequestDTO);
    }

    @GetMapping("/student-profiles")
    public List<StudentProfileResponseDTO> findAll() {
        return studentProfileService.findAllStudentProfiles();
    }
}
