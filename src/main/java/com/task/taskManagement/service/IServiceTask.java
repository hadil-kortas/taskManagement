package com.task.taskManagement.service;

import com.task.taskManagement.entities.Role;
import com.task.taskManagement.entities.Task;
import com.task.taskManagement.entities.TaskStatus;

import java.util.List;

public interface IServiceTask {

    public void saveTask(Task t);
    public List<Task> getAllTasks();
    public List<Task> getTaskByMc(String mc);
    public void deleteTask(Long id);
    public Task getTask (Long id);
    public void editTask(Long id, Task editedTask);
}
