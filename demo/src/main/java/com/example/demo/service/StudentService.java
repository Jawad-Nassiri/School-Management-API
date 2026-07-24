package com.example.demo.service;

import com.example.demo.dto.StudentRequestDTO;
import com.example.demo.dto.StudentResponseDTO;
import com.example.demo.entity.School;
import com.example.demo.entity.Student;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.repository.SchoolRepository;
import com.example.demo.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final SchoolRepository schoolRepository;

    public StudentResponseDTO saveStudentInDb(StudentRequestDTO studentRequestDTO) {
        School school = schoolRepository.findById(studentRequestDTO.schoolId()).get();
        Student student = studentMapper.toEntity(studentRequestDTO, school);
        Student savedStudent = studentRepository.save(student);
        return studentMapper.toResponseDTO(savedStudent);
    }

    public List<StudentResponseDTO> findAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toResponseDTO)
                .toList();
    }
}
