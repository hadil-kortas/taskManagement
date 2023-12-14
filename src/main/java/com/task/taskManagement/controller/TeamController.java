package com.task.taskManagement.controller;


import com.task.taskManagement.entities.Team;
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
import org.springframework.web.bind.annotation.RequestParam;

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
        m.addAttribute("team", new Team());
        m.addAttribute("users", serviceUser.getAllUsers());
        m.addAttribute("tasks", serviceTask.getAllTasks());
        return "addTeam";
    }
}
