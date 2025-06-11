package com.example.exam.mapper;

import com.example.exam.dto.ExamDTO;
import com.example.exam.entity.Exam;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExamMapper {
    
    public ExamDTO toDTO(Exam exam) {
        if (exam == null) {
            return null;
        }

        ExamDTO dto = new ExamDTO();
        dto.setId(exam.getId());
        dto.setTitle(exam.getTitle());
        dto.setDescription(exam.getDescription());
        dto.setDuration(exam.getDuration());
        dto.setCreatedAt(exam.getCreatedAt());
        return dto;
    }
    
    public List<ExamDTO> toDTOList(List<Exam> exams) {
        if (exams == null) {
            return null;
        }
        
        return exams.stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }
} 