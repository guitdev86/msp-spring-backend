package com.mspproject.backendmspapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.*;

@Entity
@Table(name="lessons")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int studentId;
    private String date;
    private int lessonLength;

    public Lesson() {
    }

    public Lesson(@JsonProperty int studentId,
                  @JsonProperty String date,
                  @JsonProperty int lessonLength) {
        this.studentId = studentId;
        this.date = date;
        this.lessonLength = lessonLength;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getLessonLength() {
        return lessonLength;
    }

    public void setLessonLength(int lessonLength) {
        this.lessonLength = lessonLength;
    }
}
