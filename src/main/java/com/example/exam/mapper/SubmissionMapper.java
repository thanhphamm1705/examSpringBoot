package com.example.exam.mapper;

import com.example.exam.dto.SubmissionDTO;
import com.example.exam.entity.Submission;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SubmissionMapper {
    
    public SubmissionDTO toDTO(Submission submission) {
        if (submission == null) {
            return null;
        }

        SubmissionDTO dto = new SubmissionDTO();
        dto.setId(submission.getId());
        dto.setStudentId(submission.getStudent().getId());
        dto.setStudentName(submission.getStudent().getFullname());
        dto.setOptionId(submission.getOption().getId());
        dto.setOptionContent(submission.getOption().getContent());
        dto.setCorrect(submission.getOption().isCorrect());
        return dto;
    }
    
    public List<SubmissionDTO> toDTOList(List<Submission> submissions) {
        if (submissions == null) {
            return null;
        }
        
        return submissions.stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }
} 