package com.example.demo.service;

import com.example.demo.dto.SchoolRequestDTO;
import com.example.demo.dto.SchoolResponseDTO;
import com.example.demo.entity.School;
import com.example.demo.mapper.SchoolMapper;
import com.example.demo.repository.SchoolRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SchoolServiceTest {
    @InjectMocks
    private  SchoolService schoolService;
    @Mock
    private SchoolRepository schoolRepository;
    @Mock
    private SchoolMapper schoolMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_successfully_save_school() {
        SchoolRequestDTO schoolRequestDTO = new SchoolRequestDTO(
                "hetic",
                "paris 18"
        );

        School school = new School();
        school.setId(15);
        school.setName("hetic");
        school.setAddress("paris 18");

        when(schoolMapper.toEntity(schoolRequestDTO))
                .thenReturn(school);
        when(schoolRepository.save(school))
                .thenReturn(school);
        when(schoolMapper.toResponseDTO(school))
                .thenReturn(new SchoolResponseDTO(
                        15,
                        "hetic",
                        "paris 18"
                ));

        SchoolResponseDTO schoolResponseDTO = schoolService.saveSchoolInDb(schoolRequestDTO);

        assertEquals(school.getId(), schoolResponseDTO.id());
        assertEquals(schoolRequestDTO.name(), schoolResponseDTO.name());
        assertEquals(schoolRequestDTO.address(), schoolResponseDTO.address());

        verify(schoolMapper).toEntity(schoolRequestDTO);
        verify(schoolRepository).save(school);
        verify(schoolMapper).toResponseDTO(school);
    }

    @Test
    public void should_find_all_schools() {
        School school = new School();
        school.setId(15);
        school.setName("hetic");
        school.setAddress("paris 18");

        SchoolResponseDTO schoolResponseDTO = new SchoolResponseDTO(
                15,
                "hetic",
                "paris 18"
        );

        when(schoolRepository.findAll())
                .thenReturn(List.of(school));
        when(schoolMapper.toResponseDTO(school))
                .thenReturn(schoolResponseDTO);

        List<SchoolResponseDTO> schoolResponseDTOS = schoolService.findAllSchools();

        assertEquals(1, schoolResponseDTOS.size());
        assertEquals(schoolResponseDTO.name(), schoolResponseDTOS.get(0).name());
        assertEquals(schoolResponseDTO.address(), schoolResponseDTOS.get(0).address());

        verify(schoolRepository).findAll();
        verify(schoolMapper).toResponseDTO(school);
    }
}