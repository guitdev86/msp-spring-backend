package com.mspproject.backendmspapp.service;

import com.mspproject.backendmspapp.model.Student;
import com.mspproject.backendmspapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public Iterable<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public Optional<Student> getStudentById(int id) {
        return studentRepository.findById(id);
    }

    public Student getStudentObjectById(int id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        Student student = optionalStudent.get();
        return student;
    }

    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }

    public Optional<Student> getStudentByNameAndSurname(String name, String surname) {
        return studentRepository.findByName(name, surname);
    }

}
