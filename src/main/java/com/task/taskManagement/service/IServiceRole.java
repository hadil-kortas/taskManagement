package com.task.taskManagement.service;

import com.task.taskManagement.entities.Role;

import java.util.List;

public interface IServiceRole {

    public void saveRole(Role r);
    public List<Role> getAllRoles();
    public List<Role> getRoleByMc(String mc);
    public void deleteRole(Long id);
    public Role getRole (Long id);

}
