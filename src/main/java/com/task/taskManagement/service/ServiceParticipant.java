package com.task.taskManagement.service;

import com.task.taskManagement.dao.ParticipantRepository;
import com.task.taskManagement.entities.Participant;
import com.task.taskManagement.entities.Task;
import jakarta.transaction.Transactional;
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
public class ServiceParticipant implements IServiceParticipant {
    private ParticipantRepository participantRepository;
    ServiceTask serviceTask;




    @Override
    public void saveParticipant(Participant p, MultipartFile mf) throws IOException {

            if (!mf.isEmpty())
            {

                String image=saveImage(mf);
                p.setPhoto(image);
            }

            participantRepository.save(p);
    }


    @Override
    public List<Participant> getAllParticipants() {
        return participantRepository.findAll();
    }

    @Override
    public Page<Participant> getParticipantByMc(String mc, String mc1, Pageable p) {
        return participantRepository.findByUsernameContainsOrJobContains(mc, mc1, p);
    }

    @Override
    public void deleteParticipant(Long id) {
        participantRepository.deleteById(id);

    }

    @Override
    public Participant getParticipant(Long id) {
        return participantRepository.findById(id).orElse(null);
    }

    @Override
    public void editParticipant(Long id, Participant editedParticipant, MultipartFile mf) throws IOException{
        Optional<Participant> existingParticipantOptional = participantRepository.findById(id);
        if (existingParticipantOptional.isPresent()) {
            Participant existingParticipant = existingParticipantOptional.get();

            existingParticipant.setFirstname(editedParticipant.getFirstname());
            existingParticipant.setLastname(editedParticipant.getLastname());
            existingParticipant.setUsername(editedParticipant.getUsername());
            existingParticipant.setEmail(editedParticipant.getEmail());
            existingParticipant.setPhone(editedParticipant.getPhone());
            existingParticipant.setJob(editedParticipant.getJob());

            if (mf != null && !mf.isEmpty()) {
                String newImageName = saveImage(mf);
                existingParticipant.setPhoto(newImageName);
            }



            participantRepository.save(existingParticipant);
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
