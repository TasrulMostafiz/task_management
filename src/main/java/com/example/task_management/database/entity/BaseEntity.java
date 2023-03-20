package com.example.task_management.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(columnDefinition ="timestamp")
    private LocalDateTime createdate= LocalDateTime.now();

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(columnDefinition ="timestamp")
    private LocalDateTime modifydate= LocalDateTime.now();


}
