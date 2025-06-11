package com.example.exam.service;

import com.example.exam.entity.Submission;
import com.example.exam.repository.OptionRepository;
import com.example.exam.repository.StudentRepository;
import com.example.exam.repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SubmissionService {

    @Autowired
    private SubmissionRepository submissionRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private OptionRepository optionRepository;

    public Submission getSubmissionById(Long id) {
        return submissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Submission not found with id: " + id));
    }

    public List<Submission> getSubmissionsByExamId(Long examId) {
        return submissionRepository.findByExamId(examId);
    }

    public List<Submission> getSubmissionsByStudentId(Long studentId) {
        return submissionRepository.findByStudentId(studentId);
    }

    public List<Submission> getSubmissionsByOptionId(Long optionId) {
        return submissionRepository.findByOptionId(optionId);
    }

    public Submission submitAnswer(Long studentId, Long optionId) {
        if(submissionRepository.existsByStudentIdAndOptionId(studentId, optionId)) {
            throw new RuntimeException("Submission already exists for this student and option");
        }
        Submission submission = new Submission();
        submission.setStudent(studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId)));
        submission.setOption(optionRepository.findById(optionId)
                .orElseThrow(() -> new RuntimeException("Option not found with id: " + optionId)));
        return submissionRepository.save(submission);
    }

    public int calculateScore(Long studentId, Long examId) {
        List<Submission> submissions = submissionRepository.findByStudentId(studentId);
        return (int) submissions.stream()
            .filter(s -> s.getOption().getQuestion().getExam().getId().equals(examId) && s.getOption().isCorrect())
            .count();
    }

    public void deleteSubmission(Long id) {
        Submission submission = getSubmissionById(id);
        submissionRepository.delete(submission);
    }
}
