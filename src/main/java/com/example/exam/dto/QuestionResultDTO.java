package com.example.exam.dto;

import lombok.Data;

@Data
public class QuestionResultDTO {
    private Long questionId;
    private String questionContent;
    private String selectedOptionContent;
    private boolean isCorrect;
    private Long examId;
    private String examTitle;
} 