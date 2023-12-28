package com.task.taskManagement.service;

import com.task.taskManagement.entities.TaskAssignment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IServiceTaskAssignment {

    public void saveTaskAssignment(TaskAssignment taskAssignement);
    public List<TaskAssignment> getAllTaskAssignments();
    public Page<TaskAssignment> getTaskAssignmentByMc(String mc, Pageable t);

    public void deleteTaskAssignment(Long id);
    public TaskAssignment getTaskAssignment (Long id);
    public void editTaskAssignment(Long id, TaskAssignment editedTaskAssignment);
}
