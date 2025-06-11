package com.example.exam.mapper;

import com.example.exam.dto.OptionDTO;
import com.example.exam.entity.Option;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OptionMapper {
    
    public OptionDTO toDTO(Option option) {
        if (option == null) {
            return null;
        }

        OptionDTO dto = new OptionDTO();
        dto.setId(option.getId());
        dto.setContent(option.getContent());
        dto.setCorrect(option.isCorrect());
        return dto;
    }
    
    public List<OptionDTO> toDTOList(List<Option> options) {
        if (options == null) {
            return null;
        }
        
        return options.stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }
} 