package com.example.exam.repository;

import com.example.exam.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query("Select q from Question q WHERE q.exam.id = :examId")
    List<Question> findByExamId(@Param("examId") Long examId);
}
