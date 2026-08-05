package com.example.demo.mapper;

import com.example.demo.dto.SchoolRequestDTO;
import com.example.demo.dto.SchoolResponseDTO;
import com.example.demo.entity.School;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SchoolMapperTest {
    private SchoolMapper schoolMapper;

    @BeforeEach
    void setUp() {
        schoolMapper = new SchoolMapper();
    }

    @Test
    public void shouldConvertRequestDTOToEntity() {
        SchoolRequestDTO dto = new SchoolRequestDTO("Isoset", "paris 13");
        School school = schoolMapper.toEntity(dto);

        assertEquals(dto.name(), school.getName());
        assertEquals(dto.address(), school.getAddress());
    }

    @Test
    public void shouldConvertEntityToResponseDTO() {
        School school = new School();
        school.setId(1);
        school.setName("isoset");
        school.setAddress("paris");

        SchoolResponseDTO schoolResponseDTO = schoolMapper.toResponseDTO(school);

        assertEquals(school.getId(), schoolResponseDTO.id());
        assertEquals(school.getName(), schoolResponseDTO.name());
        assertEquals(school.getAddress(), schoolResponseDTO.address());
    }
}