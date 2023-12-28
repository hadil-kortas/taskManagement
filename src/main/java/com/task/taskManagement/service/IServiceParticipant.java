package com.task.taskManagement.service;

import com.task.taskManagement.entities.Participant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IServiceParticipant {

    public void saveParticipant(Participant t, MultipartFile mf) throws IOException;
    public List<Participant> getAllParticipants();
    public Page<Participant> getParticipantByMc(String mc, String mc1, Pageable t);
    public void deleteParticipant(Long id);
    public Participant getParticipant (Long id);
    public void editParticipant(Long id, Participant editedParticipant, MultipartFile mf)throws IOException;
}
