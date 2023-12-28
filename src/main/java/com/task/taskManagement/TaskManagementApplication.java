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

		Participant participant = participantRepository.save(new Participant(null,"ons","belhaj","ons_belhaj","54039680","Machine learning developer","onsbelhaj@gmail.com","1234","1234",null,null))	;
		Task task = taskRepository.save(new Task(null,"ML01","Develop chatbot","create a chatbot.......",null,null));
		TaskStatus taskStatus = taskStatusRepository.save(new TaskStatus(null,"TO DO",null, Statusenum.TODO, 0));
		TaskAssignment taskAssignment = TaskAssignment.builder()
				.participant(participant)
				.task(task)
				.taskStatus(taskStatus)
				.startDate(LocalDateTime.now())
				.endDate(LocalDateTime.now().plusDays(7))
				.build();

		taskAssignementRepository.save(taskAssignment);


	}



	}
