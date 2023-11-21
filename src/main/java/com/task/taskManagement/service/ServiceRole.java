package com.task.taskManagement.service;

import com.task.taskManagement.dao.RoleRepository;
import com.task.taskManagement.entities.Role;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class ServiceRole implements IServiceRole{
    private RoleRepository roleRepository;


    @Override
    public void saveRole(Role r) {
        roleRepository.save(r);

    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public List<Role> getRoleByMc(String mc) {
        return roleRepository.findByRoleNameContains(mc);
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);

    }

    @Override
    public Role getRole(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public void editRole(Long id, Role editedRole) {
        Optional<Role> existingRoleOptional = roleRepository.findById(id);
        if (existingRoleOptional.isPresent()) {
            Role existingRole = existingRoleOptional.get();

            existingRole.setRoleName(editedRole.getRoleName());
            existingRole.setUsers(editedRole.getUsers());

            roleRepository.save(existingRole);
        }

    }
}
