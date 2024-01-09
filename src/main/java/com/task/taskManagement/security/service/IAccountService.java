package com.task.taskManagement.security.service;

import com.task.taskManagement.security.entities.AppUser;

public interface IAccountService {

    public void addRole(String role);
    public void addUser(String username, String password, String mail);
    public void addroletoUser(String username, String role);

    public void deleteRoleToUser(String username, String role);
    public AppUser loadUserByUserName(String username);
}
