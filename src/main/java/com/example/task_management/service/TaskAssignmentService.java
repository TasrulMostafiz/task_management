package com.example.task_management.service;

import com.example.task_management.database.entity.TaskAssignment;
import com.example.task_management.dto.TaskDTO;

public interface TaskAssignmentService {
    public TaskAssignment saveTask(TaskAssignment taskAssignment);
}
