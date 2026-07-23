package com.example.demo.service;

import com.example.demo.entity.School;
import com.example.demo.repository.SchoolRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SchoolService {
    private final SchoolRepository schoolRepository;

    public School saveSchoolInDb(School school) {
        return schoolRepository.save(school);
    }

}
