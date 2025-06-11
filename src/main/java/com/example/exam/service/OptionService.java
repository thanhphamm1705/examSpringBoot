package com.example.exam.service;

import com.example.exam.entity.Option;
import com.example.exam.repository.OptionRepository;
import com.example.exam.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionService {

    @Autowired
    private OptionRepository optionRepository;
    @Autowired
    private QuestionRepository questionRepository;

    public Option createOption(Option option, Long questionId) {
        option.setQuestion(questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found with id " + questionId)));
        return optionRepository.save(option);
    }

    public List<Option> getOptionsByQuestionId(Long questionId) {
        return optionRepository.findByQuestionId(questionId);
    }

    public Option getOptionById(Long optionId) {
        return optionRepository.findById(optionId)
                .orElseThrow(() -> new RuntimeException("Option not found with id: " + optionId));
    }

    public Option markAsCorrect(Long optionId) {
        Option option = getOptionById(optionId);
        option.setCorrect(true);
        return optionRepository.save(option);
    }

    public boolean isCorrect(Long optionId) {
        Option option = getOptionById(optionId);
        return option.isCorrect();
    }

    public Option updateOption(Long optionId, Option optionDetails) {
        Option option = getOptionById(optionId);
        option.setContent(optionDetails.getContent());
        option.setCorrect(optionDetails.isCorrect());
        return optionRepository.save(option);
    }

    public void deleteOption(Long optionId) {
        Option option = getOptionById(optionId);
        optionRepository.delete(option);
    }
}
