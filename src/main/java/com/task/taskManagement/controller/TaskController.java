package com.task.taskManagement.controller;

import com.task.taskManagement.entities.Task;
import com.task.taskManagement.service.ServiceTask;
import com.task.taskManagement.service.ServiceTaskStatus;
import com.task.taskManagement.service.ServiceParticipant;
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
public class TaskController {


    ServiceTask serviceTask;
    ServiceTaskStatus serviceTaskStatus;
    ServiceParticipant serviceParticipant;

    @GetMapping("/tasks")
    public String getAllTasks (Model m,
                               @RequestParam(name = "mc", defaultValue = "") String mc,
                               @RequestParam(name = "page", defaultValue = "0") int page,
                               @RequestParam(name = "size", defaultValue = "6") int size)
    {
        Page<Task> tasks=serviceTask.getTaskByMc(mc, PageRequest.of(page, size));
        m.addAttribute("mc",mc);
        m.addAttribute("tasks", tasks.getContent());
        m.addAttribute("pages", new int[tasks.getTotalPages()]);
        m.addAttribute("currentpage", tasks.getNumber());


        return "task/tasks";
    }

    @GetMapping("/addTask")
    public String addTask(Model m)
    {

        m.addAttribute("partipants", serviceParticipant.getAllParticipants());
        m.addAttribute("taskstatus", serviceTaskStatus.getAllTaskStatus());
        m.addAttribute("task", new Task());

        return "task/addTask";
    }

    @PostMapping("/addTask")
    public String saveTask(@ModelAttribute Task t, Model m) throws IOException {

        m.addAttribute("teams", serviceParticipant.getAllParticipants());
        m.addAttribute("taskstatus", serviceTaskStatus.getAllTaskStatus());



        serviceTask.saveTask(t);
        return "redirect:/tasks";
    }
}
