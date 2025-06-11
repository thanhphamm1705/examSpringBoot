package com.example.exam.dto;

import lombok.Data;

@Data
public class SubmissionDTO {
    private Long id;
    private Long studentId;
    private String studentName;
    private Long optionId;
    private String optionContent;
    private boolean isCorrect;
}
