package com.task.taskManagement;

import com.task.taskManagement.dao.*;
import com.task.taskManagement.entities.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
@AllArgsConstructor
public class TaskManagementApplication implements CommandLineRunner {

	private ParticipantRepository participantRepository;
	private TaskRepository taskRepository;
	private TaskAssignementRepository taskAssignementRepository;
	private TaskStatusRepository taskStatusRepository;




	public static void main(String[] args) {
		SpringApplication.run(TaskManagementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Task task = taskRepository.save(new Task(null, "ML01","Develop chatbot", "create a chatbot .........",null,null));



	}



	}
