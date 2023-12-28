package com.task.taskManagement.controller;


import com.task.taskManagement.entities.Task;
import com.task.taskManagement.entities.TaskAssignment;
import com.task.taskManagement.service.ServiceParticipant;
import com.task.taskManagement.service.ServiceTask;
import com.task.taskManagement.service.ServiceTaskAssignment;
import com.task.taskManagement.service.ServiceTaskStatus;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
@AllArgsConstructor
public class TaskAssignmentController {

    ServiceTaskAssignment serviceTaskAssignment;
    ServiceTask serviceTask;
    ServiceTaskStatus serviceTaskStatus;
    ServiceParticipant serviceParticipant;


    @GetMapping("/taskAssignment")
    public String getAllTaskAssignments (Model m,
                                         @RequestParam(name = "mc", defaultValue = "") String mc,
                                         @RequestParam(name = "page", defaultValue = "0") int page,
                                         @RequestParam(name = "size", defaultValue = "6") int size)
    {
        Page<TaskAssignment> taskAssignment= serviceTaskAssignment.getTaskAssignmentByMc(mc, PageRequest.of(page, size));
        m.addAttribute("mc", mc);
        m.addAttribute("taskassignment", taskAssignment.getContent());
        m.addAttribute("pages", new int[taskAssignment.getTotalPages()]);
        m.addAttribute("currentpage", taskAssignment.getContent());

        return "taskAssignment/taskAssignment";
    }

    @GetMapping("/addTaskAssignment")
    public String addTaskAssignment(Model m)
    {

        m.addAttribute("partipants", serviceParticipant.getAllParticipants());
        m.addAttribute("task", serviceTask.getAllTasks());
        m.addAttribute("taskstatus", serviceTaskStatus.getAllTaskStatus());
        m.addAttribute("task", new Task());

        return "taskAssignment/addTaskAssignment";
    }

    @PostMapping("/addTaskAsignment")
    public String saveTaskAssignment(@ModelAttribute TaskAssignment taskAssignement, Model m) throws IOException {

        m.addAttribute("particiant", serviceParticipant.getAllParticipants());
        m.addAttribute("task", serviceTask.getAllTasks());
        m.addAttribute("taskstatus", serviceTaskStatus.getAllTaskStatus());



        serviceTaskAssignment.saveTaskAssignment(taskAssignement);
        return "redirect:/taskAssignment";
    }
}
