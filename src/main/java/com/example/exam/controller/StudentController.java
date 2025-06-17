package com.example.exam.controller;

import com.example.exam.dto.PageRequestDTO;
import com.example.exam.dto.StudentDTO;
import com.example.exam.dto.StudentDetailDTO;
import com.example.exam.entity.Student;
import com.example.exam.mapper.StudentMapper;
import com.example.exam.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

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
    public Page<StudentDTO> getStudents(PageRequestDTO pageRequest) {
        return studentService.getStudentsPage(pageRequest).map(studentMapper::toDTO);
    }

    @PutMapping("/{id}")
    public StudentDTO updateStudent(@PathVariable Long id, @RequestBody Student student) {
        return studentMapper.toDTO(studentService.updateStudent(id, student));
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    @GetMapping("/{id}/detail")
    public StudentDetailDTO getStudentDetail(@PathVariable Long id) {
        return studentMapper.toDetailDTO(studentService.getStudentById(id));
    }
}
