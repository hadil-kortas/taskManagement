package com.task.taskManagement.service;

import com.task.taskManagement.dao.TeamRepository;
import com.task.taskManagement.entities.Task;
import com.task.taskManagement.entities.Team;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ServiceTeam implements IServiceTeam{
    private TeamRepository teamRepository;

    @Override
    public void saveTeam(Team t) {
        teamRepository.save(t);

    }

    @Override
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public List<Team> getTeamByMc(String mc) {
        return teamRepository.findByNameContains(mc);
    }

    @Override
    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);

    }

    @Override
    public Team getTeam(Long id) {
        return teamRepository.findById(id).orElse(null);
    }

    @Override
    public void editTeam(Long id, Team editedTeam) {
        Optional<Team> existingTeamOptional = teamRepository.findById(id);
        if (existingTeamOptional.isPresent()) {
            Team existingTeam = existingTeamOptional.get();

            existingTeam.setName(editedTeam.getName());
            existingTeam.setTasks(editedTeam.getTasks());

            teamRepository.save(existingTeam);
        }
    }
}
