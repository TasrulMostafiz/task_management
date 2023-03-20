package com.example.task_management.database.repository;

import com.example.task_management.database.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
//    @Query("select s from Task s where s.createdby=?1")
//    List<Task> findByCreatedbyEquals(UUID createdBy);

    List<Task> findByUser_IdEquals(UUID id);




}
