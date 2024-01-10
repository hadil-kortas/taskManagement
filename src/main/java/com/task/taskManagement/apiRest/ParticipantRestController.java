package com.task.taskManagement.apiRest;


import com.task.taskManagement.entities.Participant;
import com.task.taskManagement.entities.Task;
import com.task.taskManagement.service.ServiceParticipant;
import com.task.taskManagement.service.ServiceTask;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@AllArgsConstructor
@RequestMapping("/api")

public class ParticipantRestController {

    ServiceParticipant serviceParticipant;


    @GetMapping("/user/participants")
    public ModelAndView getAllParticipants(
            @RequestParam(name = "mc", defaultValue = "") String mc,
            @RequestParam(name = "mc", defaultValue = "") String mc1,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "6") int size,
            Model model) {

        Page<Participant> p = serviceParticipant.getParticipantByMc(mc, mc1, PageRequest.of(page, size));

        model.addAttribute("participants", p.getContent());
        model.addAttribute("pages", new int[p.getTotalPages()]);
        model.addAttribute("currentpage", page);
        model.addAttribute("mc", mc);
        model.addAttribute("mc",mc1);

        ModelAndView modelAndView = new ModelAndView("participantRest");
        modelAndView.addObject("participants", p);
        return modelAndView;
    }



//    @GetMapping("/participants")
//    public List<Participant> getAllParticipants(
//            @RequestParam(name = "mc", defaultValue = "") String mc,
//            @RequestParam(name = "page", defaultValue = "0") int page,
//            @RequestParam(name = "size", defaultValue = "6") int size) {
//
//        Page<Participant> p = serviceParticipant.getParticipantByMc(mc, mc, PageRequest.of(page, size));
//
//        return p.getContent();
//    }

}
