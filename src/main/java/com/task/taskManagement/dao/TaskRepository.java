package com.task.taskManagement.dao;

import com.task.taskManagement.entities.Role;
import com.task.taskManagement.entities.Task;
import com.task.taskManagement.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository

public interface TaskRepository extends JpaRepository<Task,Long> {
    public Page<Task> findByTitleContains(String mc, Pageable t);

    List<Task> findByIdIn(Set<Long> taskIds);
}
