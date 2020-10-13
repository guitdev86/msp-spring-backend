package com.mspproject.backendmspapp.repository;

import com.mspproject.backendmspapp.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
}
