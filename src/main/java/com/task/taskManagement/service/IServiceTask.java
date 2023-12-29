package com.task.taskManagement.service;

import com.task.taskManagement.entities.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface IServiceTask {

    public void saveTask(Task t, MultipartFile mf) throws IOException;
    public List<Task> getAllTasks();
    public Page<Task> getTaskByMc(String mc, Pageable t);

    public void deleteTask(Long id);
    public Task getTask (Long id);
    public void editTask(Long id, Task editedTask, MultipartFile mf) throws IOException;
}
