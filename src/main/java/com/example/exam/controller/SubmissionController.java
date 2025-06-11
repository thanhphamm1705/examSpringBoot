package com.example.exam.controller;

import com.example.exam.dto.SubmissionDTO;
import com.example.exam.entity.Submission;
import com.example.exam.mapper.SubmissionMapper;
import com.example.exam.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;

    @Autowired
    private SubmissionMapper submissionMapper;

    @PostMapping("/student/{studentId}/option/{optionId}")
    public SubmissionDTO submitAnswer(@PathVariable Long studentId, @PathVariable Long optionId) {
        return submissionMapper.toDTO(submissionService.submitAnswer(studentId, optionId));
    }

    @GetMapping("/score/student/{studentId}/exam/{examId}")
    public int calculateScore(@PathVariable Long studentId, @PathVariable Long examId) {
        return submissionService.calculateScore(studentId, examId);
    }

    @GetMapping("/{id}")
    public SubmissionDTO getSubmissionById(@PathVariable Long id) {
        return submissionMapper.toDTO(submissionService.getSubmissionById(id));
    }

    @GetMapping("/exam/{examId}")
    public List<SubmissionDTO> getSubmissionsByExamId(@PathVariable Long examId) {
        return submissionMapper.toDTOList(submissionService.getSubmissionsByExamId(examId));
    }

    @GetMapping("/student/{studentId}")
    public List<SubmissionDTO> getSubmissionsByStudentId(@PathVariable Long studentId) {
        return submissionMapper.toDTOList(submissionService.getSubmissionsByStudentId(studentId));
    }

    @GetMapping("/option/{optionId}")
    public List<SubmissionDTO> getSubmissionsByOptionId(@PathVariable Long optionId) {
        return submissionMapper.toDTOList(submissionService.getSubmissionsByOptionId(optionId));
    }

    @DeleteMapping("/{id}")
    public void deleteSubmission(@PathVariable Long id) {
        submissionService.deleteSubmission(id);
    }
}
