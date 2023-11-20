package com.task.taskManagement.dao;

import com.task.taskManagement.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
