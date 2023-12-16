package com.task.taskManagement.service;

import com.task.taskManagement.entities.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IServiceTeam {

    public void saveTeam(Team t, MultipartFile mf) throws IOException;
    public List<Team> getAllTeams();
    public Page<Team> getTeamByMc(String mc, Pageable t);
    public void deleteTeam(Long id);
    public Team getTeam (Long id);
    public void editTeam(Long id, Team editedTeam);
}
