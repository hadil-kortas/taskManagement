package com.task.taskManagement.controller;

import com.task.taskManagement.entities.Task;
import com.task.taskManagement.service.ServiceTask;
import com.task.taskManagement.service.ServiceTaskStatus;
import com.task.taskManagement.service.ServiceParticipant;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;

import lombok.AllArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class TaskController {


    ServiceTask serviceTask;
    ServiceTaskStatus serviceTaskStatus;
    ServiceParticipant serviceParticipant;

    @GetMapping("/tasks")
    public String getAllTasks (Model m,
                               @RequestParam(name = "mc", defaultValue = "") String mc,
                               @RequestParam(name = "page", defaultValue = "0") int page,
                               @RequestParam(name = "size", defaultValue = "6") int size)
    {
        page = Math.max(page, 0);
        Page<Task> tasks=serviceTask.getTaskByMc(mc, PageRequest.of(page, size));
        m.addAttribute("mc",mc);
        m.addAttribute("tasks", tasks.getContent());
        m.addAttribute("pages", new int[tasks.getTotalPages()]);
        m.addAttribute("currentpage", tasks.getNumber());


        return "task/tasks";
    }

    @GetMapping("/addTask")
    public String addTask(Model m)
    {

        m.addAttribute("task", new Task());
        return "task/addTask";
    }

    @PostMapping("/addTask")
    public String saveTask(@ModelAttribute Task t, Model m, @RequestParam("pdfFile")MultipartFile mf) throws IOException {


        serviceTask.saveTask(t, mf);
        return "redirect:/tasks";
    }

    @GetMapping("/task/{id}")
    public String getTask(@PathVariable("id") Long id, Model m) {
        Task task = serviceTask.getTask(id);
        m.addAttribute("task", task);
        return "task/viewTask";
    }

    @GetMapping("/edit/task/{id}")
    public String editTask(@PathVariable("id") Long id, Model model ) {
        Task task = serviceTask.getTask(id);
        model.addAttribute("task", task);
        return "task/editTask";
    }

    @PostMapping("/edit/task/{id}")
    public String editTask(@PathVariable("id") Long id, @ModelAttribute Task editedTask, @RequestParam("pdfFile") MultipartFile mf) throws IOException {
        serviceTask.editTask(id, editedTask, mf);
        return "redirect:/tasks";
    }

    @GetMapping("/delete/task/{id}")
    public String deleteTask(@PathVariable("id") Long idTask)
    {
        serviceTask.deleteTask(idTask);
        return "redirect:/tasks";
    }

    @GetMapping("/downloadFile")
    public ResponseEntity<Resource> downloadFile(@RequestParam String fileName) throws IOException {
        Resource resource = new ClassPathResource("static/photos/" + fileName);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(resource.getFile().length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(resource.getInputStream()));
    }


    


    /*@GetMapping("/")
    public String home()
    {
        return "redirect:/tasks";
    }*/




}
