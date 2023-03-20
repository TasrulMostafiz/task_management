package com.example.task_management.database.repository;

import com.example.task_management.database.entity.Task;
import com.example.task_management.database.entity.TaskAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface TaskAssignmentRepository extends JpaRepository<TaskAssignment, UUID> {
    List<TaskAssignment> findById_Task_TaskIdEquals(UUID taskId);

    @Query("select t from TaskAssignment t where t.id.task = ?1")
    List<TaskAssignment> findById_TaskEquals(Task task);

}
