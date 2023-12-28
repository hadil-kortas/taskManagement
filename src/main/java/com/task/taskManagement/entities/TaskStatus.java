package com.task.taskManagement.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class TaskStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String statusName;
    @OneToMany(mappedBy = "taskStatus")
    private List<TaskAssignment> taskAssignements;
    private Statusenum status;
    private Integer progressPercentage;

}
