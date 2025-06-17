package com.example.exam.dto;

import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
public class StudentDetailDTO {
    private Long id;
    private String username;
    private String fullname;
    private String email;
    private int totalQuestions;
    private int correctAnswers;
    private double averageScore;

    private Map<String, List<QuestionResultDTO>> examResults;
} 