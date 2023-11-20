package com.task.taskManagement.service;

import com.task.taskManagement.entities.User;

import java.util.List;

public interface IServiceUser {

    public void saveUser(User c);
    public List<User> getAllUsers();
    public List<User> getUserByMc(String mc);
    public void deleteUser(Long id);
    public User getUser (Long id);
}
