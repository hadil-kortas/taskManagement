package com.task.taskManagement.security.repository;

import com.task.taskManagement.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, String> {

    public AppUser findAppUserByUsername(String userName);
}
