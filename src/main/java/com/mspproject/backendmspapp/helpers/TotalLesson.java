package com.mspproject.backendmspapp.helpers;

import org.springframework.web.servlet.support.JstlUtils;

public class TotalLesson {
    private int student_id;
    private int total_lessons;

    public TotalLesson(int student_id, int total_lessons) {
        this.student_id = student_id;
        this.total_lessons = total_lessons;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getTotal_lessons() {
        return total_lessons;
    }

    public void setTotal_lessons(int total_lessons) {
        this.total_lessons = total_lessons;
    }
}
