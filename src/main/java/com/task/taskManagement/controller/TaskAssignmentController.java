package com.task.taskManagement.controller;


import com.task.taskManagement.entities.Task;
import com.task.taskManagement.entities.TaskAssignment;
import com.task.taskManagement.service.ServiceParticipant;
import com.task.taskManagement.service.ServiceTask;
import com.task.taskManagement.service.ServiceTaskAssignment;
import com.task.taskManagement.service.ServiceTaskStatus;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@AllArgsConstructor
public class TaskAssignmentController {



    ServiceTaskAssignment serviceTaskAssignment;
    ServiceTask serviceTask;
    ServiceTaskStatus serviceTaskStatus;
    ServiceParticipant serviceParticipant;


    @GetMapping("/taskAssignment")
    public String getAllTaskAssignments (Model m,
                                         @RequestParam(name = "mc", defaultValue = "") String taskRef,
                                         @RequestParam(name = "page", defaultValue = "0") int page,
                                         @RequestParam(name = "size", defaultValue = "6") int size)
    {
        page = Math.max(page, 0);

        Page<TaskAssignment> taskAssignments = serviceTaskAssignment.getAllTaskAssignments( PageRequest.of(page, size));
        m.addAttribute("mc", taskRef);
        m.addAttribute("taskassignment", taskAssignments.getContent());
        m.addAttribute("pages", new int[taskAssignments.getTotalPages()]);
        m.addAttribute("currentpage", taskAssignments.getNumber());


        return "taskAssignment/taskAssignment";
    }

    @GetMapping("/taskAssignment/findByTaskRef")
    public String getTaskAssignmentsByTaskRef(Model m, @RequestParam("mc") String taskRef) {
        List<TaskAssignment> taskAssignments = serviceTaskAssignment.findByTaskRef(taskRef);
        m.addAttribute("mc", taskRef);
        m.addAttribute("taskassignment", taskAssignments);
        m.addAttribute("currentpage", 0);
        return "taskAssignment/taskAssignment";
    }




    @GetMapping("/addTaskAssignment")
    public String addTaskAssignment(Model m)
    {

        m.addAttribute("taskAssignment", new TaskAssignment());
        m.addAttribute("participants", serviceParticipant.getAllParticipants());
        m.addAttribute("tasks", serviceTask.getAllTasks());
        m.addAttribute("taskstatus", serviceTaskStatus.getAllTaskStatus());


        return "taskAssignment/addTaskAssignment";
    }

    @PostMapping("/addTaskAssignment")
    public String saveTaskAssignment(@Valid TaskAssignment taskAssignment, BindingResult bindingResult, Model m)  {

        if (bindingResult.hasErrors()) {

            m.addAttribute("participants", serviceParticipant.getAllParticipants());
            m.addAttribute("tasks", serviceTask.getAllTasks());
            m.addAttribute("taskstatus", serviceTaskStatus.getAllTaskStatus());
            return "addTaskAssignment";

        }
        serviceTaskAssignment.saveTaskAssignment(taskAssignment);
        return "redirect:/taskAssignment";
    }

    @GetMapping("/taskAssignment/{id}")
    public String getTaskAssignment(@PathVariable("id") Long id, Model m) {
        TaskAssignment taskAssignment = serviceTaskAssignment.getTaskAssignment(id);
        m.addAttribute("taskAssignment", taskAssignment);
        return "taskAssignment/viewTaskAssignment";
    }

    @GetMapping("/edit/taskAssignment/{id}")
    public String editTaskAssignment(@PathVariable("id") Long id, Model model ) {
        TaskAssignment taskAssignment = serviceTaskAssignment.getTaskAssignment(id);
        model.addAttribute("taskAssignment", taskAssignment);
        model.addAttribute("particiant", serviceParticipant.getAllParticipants());
        model.addAttribute("task", serviceTask.getAllTasks());
        model.addAttribute("taskstatus", serviceTaskStatus.getAllTaskStatus());
        return "taskAssignment/editTaskAssignment";
    }

    @PostMapping("/edit/taskAssignment/{id}")
    public String editTaskAssignment(@PathVariable("id") Long id, @ModelAttribute TaskAssignment editedTaskAssignment) throws IOException {
        serviceTaskAssignment.editTaskAssignment(id, editedTaskAssignment);
        return "redirect:/taskAssignment";
    }

    @GetMapping("/delete/taskAssignment/{id}")
    public String deleteTaskAssignment(@PathVariable("id") Long idTaskAssignment)
    {
        serviceTaskAssignment.deleteTaskAssignment(idTaskAssignment);
        return "redirect:/taskAssignment";
    }


}
