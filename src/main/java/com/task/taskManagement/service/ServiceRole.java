package com.task.taskManagement.service;

import com.task.taskManagement.dao.RoleRepository;
import com.task.taskManagement.entities.Role;

import java.util.List;

public class ServiceRole implements IServiceRole{
    private RoleRepository roleRepository;


    @Override
    public void saveRole(Role r) {

    }

    @Override
    public List<Role> getAllRoles() {
        return null;
    }

    @Override
    public List<Role> getRoleByMc(String mc) {
        return null;
    }

    @Override
    public void deleteRole(Long id) {

    }

    @Override
    public Role getRole(Long id) {
        return null;
    }
}
