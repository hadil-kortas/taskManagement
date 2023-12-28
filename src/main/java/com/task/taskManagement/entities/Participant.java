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

public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String firstname;
    private String lastname;
    private String username;
    private String phone;
    private String job;
    private String email;
    private String password;
    private String confirmPassword;

    @OneToMany(mappedBy = "participant", cascade = CascadeType.ALL)
    List<TaskAssignment> taskAssignements ;

    private String photo;

}
