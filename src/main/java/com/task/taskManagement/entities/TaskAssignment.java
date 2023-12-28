package com.task.taskManagement.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity

public class TaskAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Task task;

    @ManyToOne
    private Participant participant;

    @ManyToOne
    private TaskStatus taskStatus;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
}

