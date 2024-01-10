package com.task.taskManagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Reference is required")
    private String ref;

    @NotEmpty(message = "Title is required")
    private String title;

    @NotEmpty
    @Size(max = 255, message = "Description must be less than 255 characters")
    private String description;

    private String file;


    @OneToMany
    private List<TaskAssignment> taskAssignement;


}
