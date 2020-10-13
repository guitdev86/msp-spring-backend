package com.mspproject.backendmspapp.helpers;

import com.mspproject.backendmspapp.model.Lesson;

import java.util.Optional;

public class SingleLesson {
    private Optional<Lesson> lesson;

    public SingleLesson(Optional<Lesson> lesson) {
        this.lesson = lesson;
    }

    public Optional<Lesson> getLesson() {
        return lesson;
    }

}
