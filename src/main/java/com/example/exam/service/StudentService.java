package com.example.exam.service;

import com.example.exam.dto.PageRequestDTO;
import com.example.exam.entity.Student;
import com.example.exam.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Page<Student> getStudentsPage(PageRequestDTO request) {
        PageRequest pageRequest = PageRequest.of(request.getPage(), request.getSize());

        if(request.getSearch() != null && !request.getSearch().trim().isEmpty()) {
            return studentRepository.findByKeyword(request.getSearch().trim(), pageRequest);
        }

        return studentRepository.findAll(pageRequest);
    }

    public Student updateStudent(Long id, Student studentDetails) {
        Student student = getStudentById(id);
        student.setUsername(studentDetails.getUsername());
        student.setPassword(studentDetails.getPassword());
        student.setFullname(studentDetails.getFullname());
        student.setEmail(studentDetails.getEmail());
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        Student student = getStudentById(id);
        studentRepository.delete(student);
    }
}
