package com.example.task_management.database.repository;

import com.example.task_management.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String email);

    Optional<User> findByIdEquals(UUID id);

}
