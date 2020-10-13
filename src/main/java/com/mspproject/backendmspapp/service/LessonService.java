package com.mspproject.backendmspapp.service;

import com.mspproject.backendmspapp.helpers.TotalLesson;
import com.mspproject.backendmspapp.helpers.TotalLessonsWrapper;
import com.mspproject.backendmspapp.model.Lesson;
import com.mspproject.backendmspapp.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class LessonService {

    @Autowired
    LessonRepository lessonRepository;

    public Iterable<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    public void saveLesson(Lesson lesson) {
        lessonRepository.save(lesson);
    }

    public Optional<Lesson> getLessonById(int id) {
        return lessonRepository.findById(id);
    }

    public void deleteLesson(int id) {
        lessonRepository.deleteById(id);
    }

    public TotalLessonsWrapper getTotalLessons(int id) {
        BigDecimal totalLessons = lessonRepository.getTotalLessons(id);

        int numberOfLessons = totalLessons.intValue();

        return new TotalLessonsWrapper(new TotalLesson(id, numberOfLessons));
    }
}
