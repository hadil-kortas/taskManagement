package com.task.taskManagement.dao;

import com.task.taskManagement.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team,Long> {
}
