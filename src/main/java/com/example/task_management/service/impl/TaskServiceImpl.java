package com.example.task_management.service.impl;

import com.example.task_management.config.JwtService;
import com.example.task_management.database.entity.*;
import com.example.task_management.database.repository.TaskAssignmentRepository;
import com.example.task_management.database.repository.TaskRepository;
import com.example.task_management.database.repository.UserRepository;
import com.example.task_management.dto.TaskDTO;
import com.example.task_management.dto.UserDTO;
import com.example.task_management.exceptoins.BlogAPIException;
import com.example.task_management.exceptoins.ResourceNotFoundException;
import com.example.task_management.service.MapperService;
import com.example.task_management.service.TaskService;
import com.example.task_management.util.CommonResponse;
import com.example.task_management.util.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskAssignmentRepository taskAssignmentRepository;

    @Autowired
    private MapperService mapperService;



    @Override
    public TaskDTO saveTask(TaskDTO taskDTO) {
        try
        {
            List<UUID> assignedto = taskDTO.getAssignedto();
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = (User) authentication.getPrincipal();
            Task task=mapperService.mapToEntity(taskDTO);
//            User build = User.builder()
//                    .id(user.getId())
//                    .build();
            task.setUser(user);
            taskRepository.save(task);
            taskDTO=mapperService.mapToDTO(task);

//            TaskDTO finalTaskDTO = taskDTO;
//            assignedto.stream()
//                            .map(x->taskAssignmentRepository
//                                    .save(new TaskAssignment(finalTaskDTO.getTaskId(),
//                                            userRepository.findByIdEquals(x).get()))
//                            );
//            for(int i=0;i<assignedto.size();i++){
//                UUID uuid = assignedto.get(i);
//                taskAssignmentRepository.save(new TaskAssignment(finalTaskDTO.getTaskId(),userRepository.findByIdEquals(uuid).get()));
//            }

            for (int i = 0; i < assignedto.size(); i++) {
                UUID uuid = assignedto.get(i);
                taskAssignmentRepository.save(new TaskAssignment(
                                new TaskAssignmentId(task, userRepository.findByIdEquals(uuid).get())
                        )
                );
            }



//            assignedto.stream()
//                    .map(x->taskAssignmentRepository
//                            .save(new TaskAssignment(new TaskAssignmentId(task,
//                                    userRepository.findByIdEquals(x).get())))
//                    );

        }catch (Exception e){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return taskDTO;
    }

    @Override
    public TaskDTO editTask(TaskDTO taskDTO)  {
        try
        {
            Optional<Task> taskOptional = taskRepository.findById(taskDTO.getTaskId());
            if(taskOptional.isPresent()){
                Task task=mapperService.mapToEntity(taskDTO);
                task.setUser(userRepository.findByIdEquals(taskDTO.getCreatedby()).get());
                taskRepository.save(task);
                taskDTO=mapperService.mapToDTO(task);
            }else{
                throw new ResourceNotFoundException("Task not found","id",taskDTO.getTaskId());
            }
        }catch (Exception e){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return taskDTO;
    }

    @Override
    public CommonResponse completeTask(UUID taskId) {
        try
        {
            Optional<Task> taskOptional = taskRepository.findById(taskId);
            if(taskOptional.isPresent()){
                Task task = taskOptional.get();
                task.setCompleteDate(LocalDateTime.now());
                task.setModifydate(LocalDateTime.now());
                return new CommonResponse(StatusCode.OK,"Task has been completed successfully");
            }else{
                throw new ResourceNotFoundException("Task not found","id",taskId);
            }
        }catch (Exception e){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public CommonResponse deleteTask(UUID taskId) {
        try
        {
            Optional<Task> taskOptional = taskRepository.findById(taskId);
            if(taskOptional.isPresent()){
                taskRepository.delete(taskOptional.get());
                return new CommonResponse(StatusCode.OK,"Task has been deleted successfully");
            }else{
                throw new ResourceNotFoundException("Task not found","id",taskId);
            }
        }catch (Exception e){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public List<TaskDTO> getList() {
        List<TaskDTO> collect = taskRepository.findAll().stream()
                .map(task -> mapperService.mapToDTO(task))
                .collect(Collectors.toList());
//        collect.stream().map(taskDTO ->
//                taskAssignmentRepository
//                        .findById_Task_TaskIdEquals(taskDTO.getTaskId())
//                        .stream().map(taskAssignment -> taskDTO.getListAssignedto()
//                                .add(mapperService.mapUserToUserDTO(taskAssignment.getId().getUser())))
//
//        );


//        for (TaskDTO taskDTO:collect) {
//            List<UserDTO> userDTOS=new ArrayList<>();
//            List<UUID> uuidList=new ArrayList<>();
//            List<TaskAssignment> byIdTaskTaskIdEquals = taskAssignmentRepository.findById_Task_TaskIdEquals(taskDTO.getTaskId());
//            for (TaskAssignment taskAssignment:byIdTaskTaskIdEquals) {
//                userDTOS.add(mapperService.mapUserToUserDTO(taskAssignment.getId().getUser()));
//                uuidList.add(taskAssignment.getId().getUser().getId());
//            }
//            taskDTO.setListAssignedto(userDTOS);
//            taskDTO.setAssignedto(uuidList);
//        }
        collect=getListofAssignedTo(collect);


        return collect;
    }

    @Override
    public List<Task> getList2() {
        return taskRepository.findAll();
    }

    @Override
    public List<TaskDTO> getListByUser(UUID createdBy) {
        List<TaskDTO> collect = taskRepository.findByUser_IdEquals(createdBy).stream()
                .map(task -> mapperService.mapToDTO(task))
                .collect(Collectors.toList());
        collect=getListofAssignedTo(collect);
        return collect;
    }

    @Override
    public TaskDTO getTaskById(UUID taskID) {
        return  taskRepository.findById(taskID)
                .map(task-> mapperService.mapToDTO(task))
                .orElseThrow(()-> new ResourceNotFoundException("Task not found","id",taskID));
    }

    private List<TaskDTO> getListofAssignedTo(List<TaskDTO> taskDTOList){
        for (TaskDTO taskDTO:taskDTOList) {
            List<UserDTO> userDTOS=new ArrayList<>();
            List<UUID> uuidList=new ArrayList<>();
            List<TaskAssignment> byIdTaskTaskIdEquals = taskAssignmentRepository.findById_Task_TaskIdEquals(taskDTO.getTaskId());
            for (TaskAssignment taskAssignment:byIdTaskTaskIdEquals) {
                userDTOS.add(mapperService.mapUserToUserDTO(taskAssignment.getId().getUser()));
                uuidList.add(taskAssignment.getId().getUser().getId());
            }
            taskDTO.setListAssignedto(userDTOS);
            taskDTO.setAssignedto(uuidList);
        }
        return taskDTOList;
    }
}
