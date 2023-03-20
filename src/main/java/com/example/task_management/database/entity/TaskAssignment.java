package com.example.task_management.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "task_assignment")
public class TaskAssignment extends BaseEntity {

    @EmbeddedId
    private TaskAssignmentId id;


//    @Id
//    @Column(name = "task_id", nullable = false)
//    private UUID taskId;
////
////    @Id
////    @Column(name = "assignedto")
////    private UUID assignedto;
//
//    @MapsId("assignedto")
//    @ManyToOne(
//            optional = false
//    )
//    @JoinColumn(
//            name = "assignedto",
//            referencedColumnName = "id"
//    )
//    private User user;


}
