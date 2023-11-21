package com.task.taskManagement.service;

import com.task.taskManagement.entities.Team;

import java.util.List;

public interface IServiceTeam {

    public void saveTeam(Team t);
    public List<Team> getAllTeams();
    public List<Team> getTeamByMc(String mc);
    public void deleteTeam(Long id);
    public Team getTeam (Long id);
    public void editTeam(Long id, Team editedTeam);
}
