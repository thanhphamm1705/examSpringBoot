package com.example.exam.mapper;

import com.example.exam.dto.QuestionResultDTO;
import com.example.exam.dto.StudentDTO;
import com.example.exam.dto.StudentDetailDTO;
import com.example.exam.entity.Student;
import com.example.exam.entity.Submission;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class StudentMapper {

    public StudentDTO toDTO(Student student) {
        if (student == null) {
            return null;
        }

        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setUsername(student.getUsername());
        dto.setFullname(student.getFullname());
        dto.setEmail(student.getEmail());
        return dto;
    }

    public List<StudentDTO> toDTOList(List<Student> students) {
        if (students == null) {
            return null;
        }
        
        return students.stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    public StudentDetailDTO toDetailDTO(Student student) {
        if (student == null) {
            return null;
        }

        StudentDetailDTO detailDTO = new StudentDetailDTO();

        detailDTO.setId(student.getId());
        detailDTO.setUsername(student.getUsername());
        detailDTO.setFullname(student.getFullname());
        detailDTO.setEmail(student.getEmail());

        List<Submission> submissions = student.getSubmissions();
        if (submissions != null && !submissions.isEmpty()) {
            int totalQuestions = submissions.size();
            int correctAnswers = 0;
            Map<String, List<QuestionResultDTO>> examResults = new HashMap<>();

            for (Submission submission : submissions) {
                if (submission.getOption().isCorrect()) {
                    correctAnswers++;
                }

                QuestionResultDTO questionResult = new QuestionResultDTO();
                questionResult.setQuestionId(submission.getOption().getQuestion().getId());
                questionResult.setQuestionContent(submission.getOption().getQuestion().getContent());
                questionResult.setSelectedOptionContent(submission.getOption().getContent());
                questionResult.setCorrect(submission.getOption().isCorrect());
                questionResult.setExamId(submission.getOption().getQuestion().getExam().getId());
                questionResult.setExamTitle(submission.getOption().getQuestion().getExam().getTitle());

                String examTitle = submission.getOption().getQuestion().getExam().getTitle();
                examResults.computeIfAbsent(examTitle, k -> new ArrayList<>()).add(questionResult);
            }

            detailDTO.setTotalQuestions(totalQuestions);
            detailDTO.setCorrectAnswers(correctAnswers);
            detailDTO.setAverageScore(totalQuestions > 0 ? (double) correctAnswers / totalQuestions * 10 : 0);
            detailDTO.setExamResults(examResults);
        }

        return detailDTO;
    }
}

