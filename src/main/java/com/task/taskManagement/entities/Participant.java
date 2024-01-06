package com.task.taskManagement.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
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

    @NotEmpty(message = "Firstname is required")
    private String firstname;

    @NotEmpty(message = "Lastname is required")
    private String lastname;

    @NotEmpty(message = "Username is required")
    private String username;

    private String phone;

    @NotEmpty(message = "Job is required")
    private String job;

    @NotEmpty(message = "Email is required")
    private String email;

    @NotEmpty(message = "Password is required")
    private String password;

    @NotEmpty(message = "Confirm Password is required")
    private String confirmPassword;

    @AssertTrue(message = "Password and Confirm Password must match")
    private boolean isPasswordMatch() {
        return password != null && password.equals(confirmPassword);
    }


    @OneToMany(mappedBy = "participant", cascade = CascadeType.ALL)
    List<TaskAssignment> taskAssignements ;

    private String photo;




}
