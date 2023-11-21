package com.task.taskManagement.dao;

import com.task.taskManagement.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Long> {
    public List<Role> findByRoleNameContains(String mc);

}
