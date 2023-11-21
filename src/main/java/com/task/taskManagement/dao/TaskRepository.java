package com.task.taskManagement.dao;

import com.task.taskManagement.entities.Role;
import com.task.taskManagement.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {
    public List<Task> findByTitleContains(String mc);
}
