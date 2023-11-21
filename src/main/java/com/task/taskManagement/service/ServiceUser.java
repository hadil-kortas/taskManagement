package com.task.taskManagement.service;

import com.task.taskManagement.dao.UserRepository;
import com.task.taskManagement.entities.Team;
import com.task.taskManagement.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ServiceUser implements IServiceUser{
    private UserRepository userRepository;

    @Override
    public void saveUser(User u) {
        userRepository.save(u);

    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getUserByMc(String mc) {
        return userRepository.findByUsernameContains(mc);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);

    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void editUser(Long id, User editedUser) {
        Optional<User> existingUserOptional = userRepository.findById(id);
        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();

            existingUser.setUsername(editedUser.getUsername());
            existingUser.setPassword(editedUser.getPassword());
            existingUser.setTasks(editedUser.getTasks());
            existingUser.setTeams(editedUser.getTeams());
            existingUser.setRoles(editedUser.getRoles());

            userRepository.save(existingUser);
        }
    }
}
