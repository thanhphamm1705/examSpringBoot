package com.example.exam.repository;

import com.example.exam.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    @Query("SELECT s FROM Submission s WHERE s.student.id = :studentId")
    List<Submission> findByStudentId(@Param("studentId") Long studentId);

    @Query("SELECT s FROM Submission s WHERE s.option.question.exam.id = :examId")
    List<Submission> findByExamId(@Param("examId") Long examId);

    @Query("SELECT COUNT(s) > 0 FROM Submission s WHERE s.student.id = :studentId AND s.option.id = :optionId")
    boolean existsByStudentIdAndOptionId(@Param("studentId") Long studentId, @Param("optionId") Long optionId);

    @Query("SELECT s FROM Submission s WHERE s.option.id = :optionId")
    List<Submission> findByOptionId(@Param("optionId") Long optionId);
}
