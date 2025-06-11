package com.example.exam.controller;

import com.example.exam.dto.ExamDTO;
import com.example.exam.entity.Exam;
import com.example.exam.service.ExamService;
import com.example.exam.mapper.ExamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exams")
public class ExamController {

    @Autowired
    private ExamService examService;
    
    @Autowired
    private ExamMapper examMapper;

    @PostMapping
    public ExamDTO createExam(@RequestBody Exam exam) {
        return examMapper.toDTO(examService.createExam(exam));
    }

    @GetMapping("/{id}")
    public ExamDTO getExamById(@PathVariable Long id) {
        return examMapper.toDTO(examService.getExamById(id));
    }

    @GetMapping
    public List<ExamDTO> getAllExams() {
        return examMapper.toDTOList(examService.getAllExams());
    }

    @PutMapping("/{id}")
    public ExamDTO updateExam(@PathVariable Long id, @RequestBody Exam exam) {
        return examMapper.toDTO(examService.updateExam(id, exam));
    }

    @DeleteMapping("/{id}")
    public void deleteExam(@PathVariable Long id) {
        examService.deleteExam(id);
    }
}
