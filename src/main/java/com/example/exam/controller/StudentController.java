package com.example.exam.controller;

import com.example.exam.dto.StudentDTO;
import com.example.exam.entity.Student;
import com.example.exam.mapper.StudentMapper;
import com.example.exam.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentMapper studentMapper;

    @PostMapping
    public StudentDTO createStudent(@RequestBody Student student) {
        return studentMapper.toDTO(studentService.createStudent(student));
    }

    @GetMapping("/{id}")
    public StudentDTO getStudentById(@PathVariable Long id) {
        return studentMapper.toDTO(studentService.getStudentById(id));
    }

    @GetMapping
    public List<StudentDTO> getAllStudents(@RequestParam(required = false) String search) {
        if (search != null && !search.trim().isEmpty()) {
            return studentMapper.toDTOList(studentService.searchStudents(search.trim()));
        }
        return studentMapper.toDTOList(studentService.getAllStudents());
    }

    @PutMapping("/{id}")
    public StudentDTO updateStudent(@PathVariable Long id, @RequestBody Student student) {
        return studentMapper.toDTO(studentService.updateStudent(id, student));
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }
}
