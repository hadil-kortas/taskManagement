package com.task.taskManagement.apiRest;


import com.task.taskManagement.entities.TaskAssignment;
import com.task.taskManagement.service.ServiceTaskAssignment;
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

public class TaskAssignmentRestController {

    ServiceTaskAssignment serviceTaskAssignment;

    @GetMapping("/taskAssignment")
    public ModelAndView getAllTaskAssignments(
            Model model,
            @RequestParam(name = "mc", defaultValue = "") String taskRef,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "6") int size)
    {
        Page<TaskAssignment> taskAssignments = serviceTaskAssignment.getAllTaskAssignments( PageRequest.of(page, size));
        model.addAttribute("taskassignment", taskAssignments.getContent());
        model.addAttribute("pages", new int[taskAssignments.getTotalPages()]);
        model.addAttribute("currentpage", taskAssignments.getNumber());

        ModelAndView modelAndView = new ModelAndView("taskAssignmentRest");
        modelAndView.addObject("taskAssignment", taskAssignments);
        return modelAndView;

    }
}
