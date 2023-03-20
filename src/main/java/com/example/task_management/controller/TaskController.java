package com.example.task_management.controller;

import com.example.task_management.database.entity.Task;
import com.example.task_management.dto.TaskDTO;
import com.example.task_management.service.TaskService;
import com.example.task_management.service.impl.TaskServiceImpl;
import com.example.task_management.util.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    // todo admin assign role


    @GetMapping("/list2")
    public ResponseEntity<List<Task>> getList2(){
        return new ResponseEntity<>(taskService.getList2(), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<TaskDTO>> getList(){
        return new ResponseEntity<>(taskService.getList(), HttpStatus.OK);
    }
    @GetMapping("/listbyuser/{userID}")
    public ResponseEntity<List<TaskDTO>> getListByUser(@PathVariable("userID") String userID){
        return new ResponseEntity<>(taskService.getListByUser(UUID.fromString(userID)), HttpStatus.OK);
    }

    @GetMapping("/taskbyid/{taskID}")
    public ResponseEntity<TaskDTO> getTaskByid(@PathVariable("taskID") String taskID){
        return new ResponseEntity<>(taskService.getTaskById(UUID.fromString(taskID)), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<TaskDTO> saveTask(@RequestBody TaskDTO taskDTO){
        return new ResponseEntity<>(taskService.saveTask(taskDTO), HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<TaskDTO> editTask(@RequestBody TaskDTO taskDTO){
        return new ResponseEntity<>(taskService.editTask(taskDTO), HttpStatus.OK);
    }

    @PutMapping("/complete/{taskID}")
    public ResponseEntity<CommonResponse> completeTask(@PathVariable("taskID") String taskID){
        return new ResponseEntity<>(taskService.completeTask(UUID.fromString(taskID)), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{taskID}")
    public ResponseEntity<CommonResponse> deleteTask(@PathVariable("taskID") String taskID){
        CommonResponse response=taskService.deleteTask(UUID.fromString(taskID));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
