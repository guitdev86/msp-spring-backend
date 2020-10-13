package com.mspproject.backendmspapp.repository;

import com.mspproject.backendmspapp.model.SessionTracking;
import com.mspproject.backendmspapp.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface SessionRepository extends CrudRepository<SessionTracking, Integer> {
    @Query(value="SELECT * FROM sessions WHERE logged_user=?1", nativeQuery = true)
    Optional<SessionTracking> findByName(String name);
}
