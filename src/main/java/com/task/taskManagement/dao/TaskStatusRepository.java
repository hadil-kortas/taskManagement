package com.task.taskManagement.dao;

import com.task.taskManagement.entities.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskStatusRepository extends JpaRepository<TaskStatus,Long> {
}
