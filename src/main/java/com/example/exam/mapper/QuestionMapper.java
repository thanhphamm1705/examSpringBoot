package com.example.exam.mapper;

import com.example.exam.dto.QuestionDTO;
import com.example.exam.entity.Question;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestionMapper {
    
    public QuestionDTO toDTO(Question question) {
        if (question == null) {
            return null;
        }

        QuestionDTO dto = new QuestionDTO();
        dto.setId(question.getId());
        dto.setContent(question.getContent());
        return dto;
    }
    
    public List<QuestionDTO> toDTOList(List<Question> questions) {
        if (questions == null) {
            return null;
        }
        
        return questions.stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }
} 