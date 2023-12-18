package com.task.taskManagement.controller;

import com.task.taskManagement.entities.Task;
import com.task.taskManagement.entities.Team;
import com.task.taskManagement.entities.User;
import com.task.taskManagement.service.ServiceTask;
import com.task.taskManagement.service.ServiceTaskStatus;
import com.task.taskManagement.service.ServiceTeam;
import com.task.taskManagement.service.ServiceUser;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class TaskController {


    ServiceTask serviceTask;
    ServiceTaskStatus serviceTaskStatus;
    ServiceTeam serviceTeam;
    ServiceUser serviceUser;

    @GetMapping("/admin/tasks")
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


        return "tasks";
    }

    @GetMapping("/admin/addTask")
    public String addTask(Model m)
    {

        m.addAttribute("teams", serviceTeam.getAllTeams());
        m.addAttribute("taskstatus", serviceTaskStatus.getAllTaskStatus());
        m.addAttribute("task", new Task());

        return "addTask";
    }

    @PostMapping("/admin/addTask")
    public String saveTask(@ModelAttribute Task t, Model m) throws IOException {

        m.addAttribute("teams", serviceTeam.getAllTeams());
        m.addAttribute("taskstatus", serviceTaskStatus.getAllTaskStatus());



        serviceTask.saveTask(t);
        return "redirect:/admin/tasks";
    }
}
