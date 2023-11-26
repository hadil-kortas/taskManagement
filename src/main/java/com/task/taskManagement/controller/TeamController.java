package com.task.taskManagement.controller;


import com.task.taskManagement.entities.Team;
import com.task.taskManagement.service.ServiceTask;
import com.task.taskManagement.service.ServiceTaskStatus;
import com.task.taskManagement.service.ServiceTeam;
import com.task.taskManagement.service.ServiceUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor

public class TeamController {

    ServiceTeam serviceTeam;
    ServiceUser serviceUser;
    ServiceTask serviceTask;
    ServiceTaskStatus serviceTaskStatus;

    @GetMapping("/admin")
    public String getAllTeams (Model m,
                               @RequestParam(name = "mc", defaultValue = "") String mc)
    {
        List<Team> teams=serviceTeam.getTeamByMc(mc);
        m.addAttribute("teams", teams);
        m.addAttribute("mc", mc);

        return "vue";
    }
}
