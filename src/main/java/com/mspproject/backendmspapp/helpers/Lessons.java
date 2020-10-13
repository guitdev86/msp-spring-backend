package com.mspproject.backendmspapp.helpers;

import com.mspproject.backendmspapp.model.Lesson;

public class Lessons {
    private Iterable<Lesson> lessons;

    public Lessons(Iterable<Lesson> lessons) {
        this.lessons = lessons;
    }

    public Iterable<Lesson> getLessons() {
        return lessons;
    }
}
