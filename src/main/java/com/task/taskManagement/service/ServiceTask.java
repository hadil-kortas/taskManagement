package com.task.taskManagement.service;

import com.task.taskManagement.dao.TaskRepository;
import com.task.taskManagement.entities.Role;
import com.task.taskManagement.entities.Task;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class ServiceTask implements IServiceTask{
    private TaskRepository taskRepository;

    @Override
    public void saveTask(Task t) {
        taskRepository.save(t);

    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> getTaskByMc(String mc) {
        return taskRepository.findByTitleContains(mc);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);

    }

    @Override
    public Task getTask(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    @Override
    public void editTask(Long id, Task editedTask) {
        Optional<Task> existingTaskOptional = taskRepository.findById(id);
        if (existingTaskOptional.isPresent()) {
            Task existingTask = existingTaskOptional.get();

            existingTask.setTitle(editedTask.getTitle());
            existingTask.setDescription(editedTask.getDescription());
            existingTask.setDueDate(editedTask.getDueDate());
            existingTask.setUser(editedTask.getUser());
            existingTask.setTeam(editedTask.getTeam());
            existingTask.setTaskStatus(editedTask.getTaskStatus());


            taskRepository.save(existingTask);
        }
    }
}
