package com.task.taskManagement.apiRest;

import com.task.taskManagement.entities.Task;
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


public class TaskRestController {

    ServiceTask serviceTask;

    @GetMapping("/tasks")
    public ModelAndView getAllTasks(
            @RequestParam(name = "mc", defaultValue = "") String mc,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "6") int size,
            Model model) {

        Page<Task> t = serviceTask.getTaskByMc(mc, PageRequest.of(page, size));

        model.addAttribute("tasks", t.getContent());
        model.addAttribute("pages", new int[t.getTotalPages()]);
        model.addAttribute("currentpage", page);
        model.addAttribute("mc", mc);

        ModelAndView modelAndView = new ModelAndView("taskRest");
        modelAndView.addObject("tasks", t);
        return modelAndView;
    }
}
