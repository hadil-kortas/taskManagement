package com.task.taskManagement.dao;

import com.task.taskManagement.entities.Team;
import com.task.taskManagement.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    public List<User> findByUsernameContains(String mc);
}
