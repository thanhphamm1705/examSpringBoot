package com.example.exam.repository;

import com.example.exam.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT s FROM Student s WHERE " +
           "LOWER(s.username) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(s.fullname) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(s.email) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Student> findByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
