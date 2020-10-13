package com.mspproject.backendmspapp.helpers;

import com.mspproject.backendmspapp.model.Student;

public class Students {
    private Iterable<Student> students;

    public Students(Iterable<Student> students) {
        this.students = students;
    }

    public Iterable<Student> getStudents() {
        return students;
    }
}