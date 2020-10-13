package com.mspproject.backendmspapp.helpers;

import com.fasterxml.jackson.annotation.OptBoolean;
import com.mspproject.backendmspapp.model.Student;

import java.util.Optional;

public class StudentWrapper {
    private Optional<Student> student;

    public StudentWrapper(Optional<Student> student) {
        this.student = student;
    }

    public Optional<Student> getStudent() {
        return student;
    }

    public void setStudent(Optional<Student> student) {
        this.student = student;
    }
}
