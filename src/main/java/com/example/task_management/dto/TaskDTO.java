package com.example.task_management.dto;

import com.example.task_management.database.entity.TaskAssignment;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class TaskDTO {
    private UUID taskId;
    private UUID createdby;
    private List<UUID> assignedto;

    private String taskName;

    private String description;

    private Timestamp startDate;

    private Timestamp endDate;
    private LocalDateTime completeDate;

    private String priority;

    private String status;

    private UserDTO assignedby;

    private List<UserDTO> listAssignedto;
}
