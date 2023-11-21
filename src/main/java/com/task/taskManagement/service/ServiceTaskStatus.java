package com.task.taskManagement.service;

import com.task.taskManagement.dao.TaskStatusRepository;
import com.task.taskManagement.entities.Task;
import com.task.taskManagement.entities.TaskStatus;

import java.util.List;
import java.util.Optional;

public class ServiceTaskStatus implements IServiceTaskStatus {
    private TaskStatusRepository taskStatusRepository;

    @Override
    public void saveTaskStatus(TaskStatus taskStatus) {
        taskStatusRepository.save(taskStatus);

    }

    @Override
    public List<TaskStatus> getAllTaskStatus() {
        return taskStatusRepository.findAll();
    }


    @Override
    public void deleteTaskStatus(Long id) {
        taskStatusRepository.deleteById(id);

    }

    @Override
    public TaskStatus getTaskStatus(Long id) {
        return taskStatusRepository.findById(id).orElse(null);
    }

    @Override
    public void editTaskStatus(Long id, TaskStatus editedTaskStatus){
        Optional<TaskStatus> existingTaskStatusOptional = taskStatusRepository.findById(id);
        if (existingTaskStatusOptional.isPresent()) {
            TaskStatus existingTaskStatus = existingTaskStatusOptional.get();

            existingTaskStatus.setStatusName(editedTaskStatus.getStatusName());
            existingTaskStatus.setTasks(editedTaskStatus.getTasks());
            existingTaskStatus.setStatus(editedTaskStatus.getStatus());

            taskStatusRepository.save(existingTaskStatus);
        }
    }
}
