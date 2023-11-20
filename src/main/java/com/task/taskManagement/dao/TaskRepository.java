package com.task.taskManagement.dao;

import com.task.taskManagement.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long> {
}
