package com.example.task_management.service;

import com.example.task_management.database.entity.Task;
import com.example.task_management.dto.TaskDTO;
import com.example.task_management.util.CommonResponse;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    public TaskDTO saveTask(TaskDTO taskDTO);
    public TaskDTO editTask(TaskDTO taskDTO);
    public CommonResponse completeTask(UUID taskId);
    public CommonResponse deleteTask(UUID taskId);
    public List<TaskDTO> getList();
    public List<Task> getList2();
    public List<TaskDTO> getListByUser(UUID createdBy);
    public TaskDTO getTaskById(UUID taskID);
}
