package com.task.taskManagement.dao;

import com.task.taskManagement.entities.Role;
import com.task.taskManagement.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team,Long> {
    public List<Team> findByNomContains(String mc);
}
