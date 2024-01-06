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
//		TaskStatus taskStatus = taskStatusRepository.save(new TaskStatus(null,null,null, Statusenum.TODO, 0));
//		TaskStatus taskStatus1 = taskStatusRepository.save(new TaskStatus(null,null,null, Statusenum.INPROGRESS, 50));
//		TaskStatus taskStatus2 = taskStatusRepository.save(new TaskStatus(null,null,null, Statusenum.ALLMOSTDONE, 75));
//		TaskStatus taskStatus3 = taskStatusRepository.save(new TaskStatus(null,null,null, Statusenum.DONE, 100));


	}



	}

