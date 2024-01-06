package com.task.taskManagement.service;

import com.task.taskManagement.dao.TaskRepository;
import com.task.taskManagement.entities.Task;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class ServiceTask implements IServiceTask{
    private TaskRepository taskRepository;


    @Override
    public void saveTask(Task t, MultipartFile mf) throws IOException {
        if (!mf.isEmpty())
        {

            String file=saveFile(mf);
            t.setFile(file);
        }
        taskRepository.save(t);

    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Page<Task> getTaskByMc(String mc, Pageable p) {
        return taskRepository.findByRefContains(mc, p);
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
    public void editTask(Long id, Task editedTask, MultipartFile mf) throws IOException {
        Optional<Task> existingTaskOptional = taskRepository.findById(id);
        if (existingTaskOptional.isPresent()) {
            Task existingTask = existingTaskOptional.get();
            existingTask.setRef(editedTask.getRef());
            existingTask.setTitle(editedTask.getTitle());
            existingTask.setDescription(editedTask.getDescription());

            if (mf != null && !mf.isEmpty()) {
                String newFile = saveFile(mf);
                existingTask.setFile(newFile);
            }

            taskRepository.save(existingTask);

        }

    }

    public String saveFile(MultipartFile mf) throws IOException {
        String fileName = mf.getOriginalFilename();
        String[] parts = fileName.split("\\.");
        String newName = parts[0] + System.currentTimeMillis() + "." + parts[1];
        File directoryFile = new ClassPathResource("static/photos").getFile();
        String path = directoryFile.getAbsolutePath();
        Path filePath = Paths.get(path, newName);
        Files.write(filePath, mf.getBytes());

        return newName;
    }




}
