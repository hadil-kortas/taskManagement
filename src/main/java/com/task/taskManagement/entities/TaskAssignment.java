package com.task.taskManagement.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
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

//    @OneToOne(cascade = CascadeType.MERGE)
    @ManyToOne
    @NotNull(message = "Task is required")
    private Task task;

    @ManyToOne
    @NotNull(message = "Participant is required")
    private Participant participant;

    @ManyToOne
    private TaskStatus taskStatus;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Start Date is required")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "End Date is required")
    private LocalDate endDate;


}

