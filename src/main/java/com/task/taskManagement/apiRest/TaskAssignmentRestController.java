package com.task.taskManagement.apiRest;


import com.task.taskManagement.entities.TaskAssignment;
import com.task.taskManagement.service.ServiceParticipant;
import com.task.taskManagement.service.ServiceTask;
import com.task.taskManagement.service.ServiceTaskAssignment;
import com.task.taskManagement.service.ServiceTaskStatus;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@AllArgsConstructor
@RequestMapping("/api")

public class TaskAssignmentRestController {

    ServiceTaskAssignment serviceTaskAssignment;
    ServiceParticipant serviceParticipant;
    ServiceTask serviceTask;
    ServiceTaskStatus serviceTaskStatus;

    @GetMapping("/user/taskAssignment")
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

    @GetMapping("/user/update/taskAssignment/{id}")
    public ModelAndView editTaskAssignment(@PathVariable("id") Long id) {
        TaskAssignment taskAssignment = serviceTaskAssignment.getTaskAssignment(id);

        ModelAndView modelAndView = new ModelAndView("editTaskAssignmentRest");
        modelAndView.addObject("taskAssignment", taskAssignment);
        modelAndView.addObject("participants", serviceParticipant.getAllParticipants());
        modelAndView.addObject("tasks", serviceTask.getAllTasks());
        modelAndView.addObject("taskStatus", serviceTaskStatus.getAllTaskStatus());
        return modelAndView;
    }

    @PutMapping("/user/update/taskAssignment/{id}")
    public ModelAndView updateTaskAssignment(@PathVariable("id") Long id, @RequestBody TaskAssignment editedTaskAssignment) {
        ModelAndView modelAndView = new ModelAndView("redirect:/api/user/taskAssignment");
        serviceTaskAssignment.editTaskAssignment(id, editedTaskAssignment);
        return modelAndView;
    }

}
