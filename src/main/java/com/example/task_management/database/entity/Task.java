package com.example.task_management.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "task")
public class Task extends BaseEntity {
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Id
    @Column(name = "task_id", nullable = false)
    private UUID taskId;
    
    @Column(name = "task_name", nullable = true, length = 100)
    private String taskName;
    
    @Column(name = "description", nullable = true, length = -1)
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "start_date", nullable = false)
    private Timestamp startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "end_date", nullable = false)
    private Timestamp endDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "complete_date", nullable = true)
    private LocalDateTime completeDate;
    
    @Column(name = "priority", nullable = true, length = 100)
    private String priority;
    
    @Column(name = "status", nullable = true, length = 100)
    private String status;

//    @Column(columnDefinition ="uuid")
//    private UUID createdby;

    @ManyToOne(
            fetch = FetchType.EAGER,
            optional = false
    )
    @JoinColumn(
            name = "createdby",
            referencedColumnName = "id"
    )
    private User user;

//    @OneToMany(
//            mappedBy = "id"
//    )
////    @JoinColumn(
////            name = "task_id",
////            referencedColumnName = "task_id"
////    )
//    private List<TaskAssignment> tasks;

}
