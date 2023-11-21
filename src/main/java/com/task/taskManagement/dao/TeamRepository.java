package com.task.taskManagement.dao;

import com.task.taskManagement.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TeamRepository extends JpaRepository<Team,Long> {
    public List<Team> findByNameContains(String mc);
}
