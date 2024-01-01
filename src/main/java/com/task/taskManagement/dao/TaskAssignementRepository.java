package com.task.taskManagement.dao;

import com.task.taskManagement.entities.TaskAssignment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskAssignementRepository extends JpaRepository <TaskAssignment, Long> {


    List<TaskAssignment> findTaskAssignmentByTaskRef(String taskRef);
}

