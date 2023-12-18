package com.task.taskManagement.controller;


import com.task.taskManagement.entities.Task;
import com.task.taskManagement.entities.Team;
import com.task.taskManagement.entities.User;
import com.task.taskManagement.service.ServiceTask;
import com.task.taskManagement.service.ServiceTaskStatus;
import com.task.taskManagement.service.ServiceTeam;
import com.task.taskManagement.service.ServiceUser;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor

public class TeamController {

    ServiceTeam serviceTeam;
    ServiceUser serviceUser;
    ServiceTask serviceTask;
    ServiceTaskStatus serviceTaskStatus;

    @GetMapping("/admin/teams")
    public String getAllTeams (Model m,
                               @RequestParam(name = "mc", defaultValue = "") String mc,
                               @RequestParam(name = "page", defaultValue = "0") int page,
                               @RequestParam(name = "size", defaultValue = "5") int size)
    {
        Page<Team> teams=serviceTeam.getTeamByMc(mc, PageRequest.of(page, size));
        m.addAttribute("mc",mc);
        m.addAttribute("teams", teams.getContent());
        m.addAttribute("pages", new int[teams.getTotalPages()]);
        m.addAttribute("currentpage", teams.getNumber());


        return "teams";
    }

    @GetMapping("/admin/addTeam")
    public String addTeam(Model m)
    {
        m.addAttribute("users", serviceUser.getAllUsers());
        m.addAttribute("tasks", serviceTask.getAllTasks());
        m.addAttribute("team", new Team());

        return "addTeam";
    }

    @PostMapping("/admin/addTeam")
    public String saveTeam(@ModelAttribute Team t, Model m, @RequestParam("image") MultipartFile mf) throws IOException {
        m.addAttribute("users", serviceUser.getAllUsers());
        m.addAttribute("tasks", serviceTask.getAllTasks());

        // Set the selected users and tasks for the team
        Set<User> selectedUsers = new HashSet<>(serviceUser.getUsersByIds(t.getUsers().stream().map(User::getId).collect(Collectors.toSet())));
        Set<Task> selectedTasks = new HashSet<>(serviceTask.getTasksByIds(t.getTasks().stream().map(Task::getId).collect(Collectors.toSet())));

        t.setUsers(selectedUsers);
        t.setTasks(new ArrayList<>(selectedTasks));


        serviceTeam.saveTeam(t,mf);
        return "redirect:/admin/teams";
    }
}
