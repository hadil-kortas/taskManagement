package com.task.taskManagement.service;

import com.task.taskManagement.dao.TaskAssignementRepository;
import com.task.taskManagement.entities.TaskAssignment;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class ServiceTaskAssignment implements IServiceTaskAssignment {

    private TaskAssignementRepository taskAssignementRepository;

    @Override
    public void saveTaskAssignment(TaskAssignment taskAssignement) {
        taskAssignementRepository.save(taskAssignement);

    }

    @Override
    public List<TaskAssignment> getAllTaskAssignments() {
        return taskAssignementRepository.findAll();
    }

    @Override
    public Page<TaskAssignment> getTaskAssignmentByMc(String mc, Pageable p) {
        return taskAssignementRepository.findByTaskTitleContainingIgnoreCase(mc, p);
    }

    @Override
    public void deleteTaskAssignment(Long id) {
        taskAssignementRepository.deleteById(id);

    }

    @Override
    public TaskAssignment getTaskAssignment(Long id) {
        return taskAssignementRepository.findById(id).orElse(null);
    }

    @Override
    public void editTaskAssignment(Long id, TaskAssignment editedTaskAssignment) {
        Optional<TaskAssignment> existingTaskAssignmentOptional = taskAssignementRepository.findById(id);
        if (existingTaskAssignmentOptional.isPresent()) {
            TaskAssignment existingTaskAssignment = existingTaskAssignmentOptional.get();

            existingTaskAssignment.setTask(editedTaskAssignment.getTask());
            existingTaskAssignment.setParticipant(editedTaskAssignment.getParticipant());
            existingTaskAssignment.setTaskStatus(editedTaskAssignment.getTaskStatus());
            existingTaskAssignment.setStartDate(editedTaskAssignment.getStartDate());
            existingTaskAssignment.setEndDate(editedTaskAssignment.getEndDate());


            taskAssignementRepository.save(existingTaskAssignment);

        }
    }
}
