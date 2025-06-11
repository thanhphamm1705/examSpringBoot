package com.example.exam.controller;

import com.example.exam.dto.QuestionDTO;
import com.example.exam.entity.Question;
import com.example.exam.mapper.QuestionMapper;
import com.example.exam.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    
    @Autowired
    private QuestionMapper questionMapper;

    @PostMapping("/exam/{examId}")
    public QuestionDTO createQuestion(@RequestBody Question question, @PathVariable Long examId) {
        return questionMapper.toDTO(questionService.createQuestion(question, examId));
    }

    @GetMapping("/exam/{examId}")
    public List<QuestionDTO> getQuestionsByExamId(@PathVariable Long examId) {
        return questionMapper.toDTOList(questionService.getQuestionsByExamId(examId));
    }

    @GetMapping("/{id}")
    public QuestionDTO getQuestionById(@PathVariable Long id) {
        return questionMapper.toDTO(questionService.getQuestionById(id));
    }

    @PutMapping("/{id}")
    public QuestionDTO updateQuestion(@PathVariable Long id, @RequestBody Question question) {
        return questionMapper.toDTO(questionService.updateQuestion(id, question));
    }

    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
    }
}
