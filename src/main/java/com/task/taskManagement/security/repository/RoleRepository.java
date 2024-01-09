package com.task.taskManagement.security.repository;

import com.task.taskManagement.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<AppRole, String> {
}
