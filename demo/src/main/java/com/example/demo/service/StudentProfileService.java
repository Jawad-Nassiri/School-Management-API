package com.example.demo.service;

import com.example.demo.entity.StudentProfile;
import com.example.demo.repository.StudentProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentProfileService {
    private final StudentProfileRepository studentProfileRepository;

    public StudentProfile saveStudentProfileInDb(StudentProfile studentProfile) {
        return studentProfileRepository.save(studentProfile);
    }

    public List<StudentProfile> findAllStudentProfiles() {
        return studentProfileRepository.findAll();
    }
}
