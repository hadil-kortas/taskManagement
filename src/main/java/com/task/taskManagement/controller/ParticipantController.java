package com.task.taskManagement.controller;


import com.task.taskManagement.entities.Participant;
import com.task.taskManagement.service.ServiceTask;
import com.task.taskManagement.service.ServiceTaskStatus;
import com.task.taskManagement.service.ServiceParticipant;
import jakarta.servlet.http.Part;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@AllArgsConstructor

public class ParticipantController {

    ServiceParticipant serviceParticipant;
    ServiceTask serviceTask;
    ServiceTaskStatus serviceTaskStatus;



    @GetMapping("/participants")
    public String getAllParticipants (Model m,
                                      @RequestParam(name = "mc", defaultValue = "") String mc,
                                      @RequestParam(name = "mc", defaultValue = "") String mc1,
                                      @RequestParam(name = "page", defaultValue = "0") int page,
                                      @RequestParam(name = "size", defaultValue = "6") int size)
    {
        page = Math.max(page, 0);
        Page<Participant> participants=serviceParticipant.getParticipantByMc(mc, mc1,PageRequest.of(page, size));
        m.addAttribute("mc",mc);
        m.addAttribute("mc",mc1);
        m.addAttribute("participants", participants.getContent());
        m.addAttribute("pages", new int[participants.getTotalPages()]);
        m.addAttribute("currentpage", participants.getNumber());


        return "participant/participants";
    }

    @GetMapping("/addParticipant")
    public String addParticipant( Model m)
    {
        m.addAttribute("participant", new Participant());
        return "participant/addParticipant";
    }

    @PostMapping("/addParticipant")
    public String saveParticipant(@Valid Participant t, BindingResult bindingResult, Model m,
                                  @RequestParam("image") MultipartFile mf) throws IOException {

        if (bindingResult.hasErrors()) {
            return "participant/addParticipant";
        }


        serviceParticipant.saveParticipant(t,mf);
        return "redirect:/participants";
    }
    @GetMapping("/participant/{id}")
    public String getParticipant(@PathVariable("id") Long id, Model m) {
        Participant participant = serviceParticipant.getParticipant(id);
        m.addAttribute("participant", participant);
        return "participant/viewParticipant";
    }

    @GetMapping("/edit/{id}")
    public String editParticipant(@PathVariable("id") Long id, Model model ) {
        Participant participant = serviceParticipant.getParticipant(id);
        model.addAttribute("participant", participant);
        return "participant/editParticipant";
    }

    @PostMapping("/edit/{id}")
    public String editParticipant(@PathVariable("id") Long id, @ModelAttribute Participant editedParticipant, @RequestParam("image") MultipartFile mf) throws IOException {
        serviceParticipant.editParticipant(id, editedParticipant, mf);
        return "redirect:/participants";
    }



    @GetMapping("/delete/{id}")
    public String deleteParticipant(@PathVariable("id") Long idParticipant)
    {
        serviceParticipant.deleteParticipant(idParticipant);
        return "redirect:/participants";
    }


    @GetMapping("/")
    public String home()
    {
        return "redirect:/participants";
    }
}

