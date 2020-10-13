package com.mspproject.backendmspapp.repository;

import com.mspproject.backendmspapp.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student, Integer> {
    @Query(value="SELECT * FROM students WHERE name=?1 AND surname=?2", nativeQuery = true)
    Optional<Student> findByName(String name, String surname);
}
