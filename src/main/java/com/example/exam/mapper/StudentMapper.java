package com.example.exam.mapper;

import com.example.exam.dto.StudentDTO;
import com.example.exam.entity.Student;
import org.springframework.stereotype.Component;

import java.util.List;
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
}

