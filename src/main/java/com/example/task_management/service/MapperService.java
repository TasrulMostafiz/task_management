package com.example.task_management.service;

import com.example.task_management.database.entity.Task;
import com.example.task_management.database.entity.TaskAssignment;
import com.example.task_management.database.entity.User;
import com.example.task_management.database.repository.UserRepository;
import com.example.task_management.dto.TaskDTO;
import com.example.task_management.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MapperService {
    private ModelMapper modelMapper;

    public MapperService(){
        modelMapper=new ModelMapper();
        modelMapper.typeMap(Task.class, TaskDTO.class).addMappings(mapper -> {
            mapper.map(src -> src.getUser().getId(),TaskDTO::setCreatedby);
            mapper.map(src -> src.getUser(),TaskDTO::setAssignedby);
        });
    }

    public TaskDTO mapToDTO(Task task){
        TaskDTO taskDTO= modelMapper.map(task, TaskDTO.class);
//        List<UserDTO> userDTOS=new ArrayList<>();
////        task.getTasks().stream().map(x ->
////                userDTOS.add(modelMapper.map(x.getUser(), UserDTO.class))
////        );
//        List<TaskAssignment> taskAssignments = task.getTasks();
//        List<UUID> uuidList=new ArrayList<>();
//        for (int i = 0; i < taskAssignments.size(); i++) {
//            TaskAssignment taskAssignment = taskAssignments.get(i);
//            userDTOS.add(modelMapper.map(taskAssignment.getUser(), UserDTO.class));
//            uuidList.add(taskAssignment.getUser().getId());
//        }
//        taskDTO.setListAssignedto(userDTOS);
//        taskDTO.setAssignedto(uuidList);
        return taskDTO;
    }
    public Task mapToEntity(TaskDTO taskDTO){
        Task task= modelMapper.map(taskDTO, Task.class);
        return task;
    }

    public UserDTO mapUserToUserDTO(User user){
        return modelMapper.map(user, UserDTO.class);
    }
}
