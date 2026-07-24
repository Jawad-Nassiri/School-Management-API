package com.example.demo.service;

import com.example.demo.dto.SchoolRequestDTO;
import com.example.demo.dto.SchoolResponseDTO;
import com.example.demo.entity.School;
import com.example.demo.mapper.SchoolMapper;
import com.example.demo.repository.SchoolRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SchoolService {
    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;

    public SchoolResponseDTO saveSchoolInDb(SchoolRequestDTO schoolRequestDTO) {
        School school = schoolMapper.toEntity(schoolRequestDTO);
        School savedSchool = schoolRepository.save(school);
        return schoolMapper.toResponseDTO(savedSchool);
    }

    public List<SchoolResponseDTO> findAllSchools() {
        return schoolRepository.findAll()
                .stream()
                .map(schoolMapper::toResponseDTO)
                .toList();
    }

}
