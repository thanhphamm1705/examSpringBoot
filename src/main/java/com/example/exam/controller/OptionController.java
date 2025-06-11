package com.example.exam.controller;

import com.example.exam.dto.OptionDTO;
import com.example.exam.entity.Option;
import com.example.exam.mapper.OptionMapper;
import com.example.exam.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/options")
public class OptionController {

    @Autowired
    private OptionService optionService;

    @Autowired
    private OptionMapper optionMapper;

    @PostMapping("/question/{questionId}")
    public OptionDTO createOption(@RequestBody Option option, @PathVariable Long questionId) {
        return optionMapper.toDTO(optionService.createOption(option, questionId));
    }

    @GetMapping("/question/{questionId}")
    public List<OptionDTO> getOptionsByQuestionId(@PathVariable Long questionId) {
        return optionMapper.toDTOList(optionService.getOptionsByQuestionId(questionId));
    }

    @GetMapping("/{id}")
    public OptionDTO getOptionById(@PathVariable Long id) {
        return optionMapper.toDTO(optionService.getOptionById(id));
    }

    @PutMapping("/{id}/mark-correct")
    public OptionDTO markAsCorrect(@PathVariable Long id) {
        return optionMapper.toDTO(optionService.markAsCorrect(id));
    }

    @GetMapping("/{id}/is-correct")
    public boolean isCorrect(@PathVariable Long id) {
        return optionService.isCorrect(id);
    }

    @PutMapping("/{id}")
    public OptionDTO updateOption(@PathVariable Long id, @RequestBody Option option) {
        return optionMapper.toDTO(optionService.updateOption(id, option));
    }

    @DeleteMapping("/{id}")
    public void deleteOption(@PathVariable Long id) {
        optionService.deleteOption(id);
    }
}
