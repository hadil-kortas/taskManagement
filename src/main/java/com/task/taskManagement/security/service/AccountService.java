package com.task.taskManagement.security.service;

import com.task.taskManagement.security.entities.AppRole;
import com.task.taskManagement.security.entities.AppUser;
import com.task.taskManagement.security.repository.RoleRepository;
import com.task.taskManagement.security.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional //ta3mel transaction fil base

public class AccountService implements IAccountService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public void addRole(String role) {
        roleRepository.save(AppRole.builder().role(role).build());

    }

    @Override
    public void addUser(String username, String password, String mail) {
        AppUser user=userRepository.findAppUserByUsername(username);
        if (user != null) throw new RuntimeException("user exist");
        userRepository.save(AppUser
                .builder()
                .id(UUID.randomUUID().toString())
                .mail(mail)
                .username(username)
                .password(passwordEncoder.encode(password))
                .build());

    }

    @Override
    public void addroletoUser(String username, String role) {
        AppUser user = loadUserByUserName(username);
        AppRole role1 = roleRepository.findById(role).orElse(null);
        user.getRoles().add(role1);

    }

    @Override
    public void deleteRoleToUser(String username, String role) {

        AppUser user = loadUserByUserName(username);

        AppRole role1 = roleRepository.findById(role).orElse(null);

        user.getRoles().remove(role1);
        userRepository.save(user);


    }

    @Override
    public AppUser loadUserByUserName(String username) {
        return userRepository.findAppUserByUsername(username);
    }
}
