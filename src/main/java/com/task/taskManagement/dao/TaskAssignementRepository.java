package com.task.taskManagement.dao;

import com.task.taskManagement.entities.TaskAssignment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskAssignementRepository extends JpaRepository <TaskAssignment, Long> {

    public Page<TaskAssignment> findByTaskTitleContainingIgnoreCase(String taskTitle, Pageable pageable);

}
