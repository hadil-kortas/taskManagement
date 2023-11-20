package com.task.taskManagement.service;

import com.task.taskManagement.dao.TeamRepository;
import com.task.taskManagement.entities.Team;

import java.util.List;

public class ServiceTeam implements IServiceTeam{
    private TeamRepository teamRepository;

    @Override
    public void saveTeam(Team t) {

    }

    @Override
    public List<Team> getAllTeams() {
        return null;
    }

    @Override
    public List<Team> getTeamByMc(String mc) {
        return null;
    }

    @Override
    public void deleteTeam(Long id) {

    }

    @Override
    public Team getTeam(Long id) {
        return null;
    }
}
