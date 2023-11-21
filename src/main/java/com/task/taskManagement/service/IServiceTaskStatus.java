package com.task.taskManagement.service;

import com.task.taskManagement.entities.TaskStatus;

import java.util.List;

public interface IServiceTaskStatus {
    public void saveTaskStatus(TaskStatus taskStatus);
    public List<TaskStatus> getAllTaskStatus();

    public void deleteTaskStatus(Long id);
    public TaskStatus getTaskStatus (Long id);
    public void editTaskStatus(Long id, TaskStatus editedTaskStatus);
}
