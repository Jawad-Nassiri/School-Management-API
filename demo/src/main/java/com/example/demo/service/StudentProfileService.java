package com.example.demo.service;

import com.example.demo.dto.StudentProfileRequestDTO;
import com.example.demo.dto.StudentProfileResponseDTO;
import com.example.demo.entity.Student;
import com.example.demo.entity.StudentProfile;
import com.example.demo.mapper.StudentProfileMapper;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentProfileService {
    private final StudentProfileRepository studentProfileRepository;
    private final StudentProfileMapper studentProfileMapper;
    private final StudentRepository studentRepository;

    public StudentProfileResponseDTO saveStudentProfileInDb(StudentProfileRequestDTO studentProfileRequestDTO) {
        Student student = studentRepository.findById(studentProfileRequestDTO.studentId()).get();
        StudentProfile studentProfile = studentProfileMapper.toEntity(studentProfileRequestDTO, student);
        StudentProfile savedStudentProfile = studentProfileRepository.save(studentProfile);
        return studentProfileMapper.toResponseDTO(savedStudentProfile);
    }

    public List<StudentProfileResponseDTO> findAllStudentProfiles() {
        return studentProfileRepository.findAll().stream().map(studentProfileMapper::toResponseDTO).toList();
    }
}
