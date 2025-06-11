package com.example.exam.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ExamDTO {
    private Long id;
    private String title;
    private String description;
    private Long duration;
    private LocalDateTime createdAt;
}
