package com.task.taskManagement.service;

import com.task.taskManagement.dao.TeamRepository;
import com.task.taskManagement.entities.Team;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ServiceTeam implements IServiceTeam{
    private TeamRepository teamRepository;

    @Override
    public void saveTeam(Team t, MultipartFile mf) throws IOException{

        if (!mf.isEmpty())
        {

            String image=saveImage(mf);
            t.setPhoto(image);
        }

        teamRepository.save(t);

    }

    @Override
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public Page<Team> getTeamByMc(String mc, Pageable t) {
        return teamRepository.findByNameContains(mc, t);
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

    public String saveImage(MultipartFile mf) throws IOException {
        String photoname=mf.getOriginalFilename();
        String tab[]=photoname.split("\\.");
        String newName=tab[0]+System.currentTimeMillis()+"."+tab[1];
        File f = new ClassPathResource("static/photos").getFile();
        String path= f.getAbsolutePath();
        Path p= Paths.get(path,newName);
        Files.write(p,mf.getBytes());
        return newName;
    }
}
