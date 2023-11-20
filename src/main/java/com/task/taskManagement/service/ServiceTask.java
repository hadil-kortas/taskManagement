package com.task.taskManagement.service;

import com.task.taskManagement.dao.TaskRepository;
import com.task.taskManagement.entities.Task;

import java.util.List;

public class ServiceTask implements IServiceTask{
    private TaskRepository taskRepository;

    @Override
    public void saveTask(Task t) {

    }

    @Override
    public List<Task> getAllTasks() {
        return null;
    }

    @Override
    public List<Task> getTaskByMc(String mc) {
        return null;
    }

    @Override
    public void deleteTask(Long id) {

    }

    @Override
    public Task getTask(Long id) {
        return null;
    }
}
