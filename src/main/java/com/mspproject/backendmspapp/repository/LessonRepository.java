package com.mspproject.backendmspapp.repository;

import com.mspproject.backendmspapp.model.Lesson;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;

public interface LessonRepository extends CrudRepository<Lesson, Integer> {
    @Query(value="SELECT COUNT(*) AS total_lessons " +
            "FROM lessons " +
            "WHERE student_id=?1", nativeQuery = true)
    BigDecimal getTotalLessons(int id);
}
