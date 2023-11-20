package com.task.taskManagement.service;

import com.task.taskManagement.dao.UserRepository;
import com.task.taskManagement.entities.User;

import java.util.List;

public class ServiceUser implements IServiceUser{
    private UserRepository userRepository;

    @Override
    public void saveUser(User c) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public List<User> getUserByMc(String mc) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }

    @Override
    public User getUser(Long id) {
        return null;
    }
}
