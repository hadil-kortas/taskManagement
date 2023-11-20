package com.task.taskManagement.service;

import com.task.taskManagement.dao.TaskStatusRepository;
import com.task.taskManagement.entities.TaskStatus;

import java.util.List;

public class ServiceTaskStatus implements IServiceTaskStatus {
    private TaskStatusRepository taskStatusRepository;

    @Override
    public void saveTaskStatus(TaskStatus taskStatus) {

    }

    @Override
    public List<TaskStatus> getAllTaskStatus() {
        return null;
    }

    @Override
    public List<TaskStatus> getTaskStatusByMc(String mc) {
        return null;
    }

    @Override
    public void deleteTaskStatus(Long id) {

    }

    @Override
    public TaskStatus getTaskStatus(Long id) {
        return null;
    }
}
