package com.task.taskManagement;

import com.task.taskManagement.dao.*;
import com.task.taskManagement.entities.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Example;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@SpringBootApplication
@AllArgsConstructor
public class TaskManagementApplication implements CommandLineRunner {

	private RoleRepository roleRepository;
	private TaskRepository taskRepository;
	private TaskStatusRepository taskStatusRepository;
	private TeamRepository teamRepository;
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(TaskManagementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


		// Example of creating a new team
		Team team5 = new Team();
		team5.setName("B");

		User user1 = new User();
		user1.setUsername("dffdsfsd");
		user1.setPassword("sdfsdfsf");
		team5.getUsers().add(user1);

		User user6 = new User();
		user6.setUsername("zeaze");
		user6.setPassword("zeaze");
		team5.getUsers().add(user6);

		Task task5 = new Task();
		task5.setTitle("jdlskdjlsqd");
		task5.setTeam(team5);
		task5.setDescription("fqsdqddqdqddqsd");
		task5.setDueDate(LocalDateTime.of(2023,9,25,16,30));
		task5.setTaskStatus(TaskStatus.builder().statusName("to do").status(Statusenum.TODO).progressPercentage(0).build());
		team5.getTasks().add(task5);
		teamRepository.save(team5);






	}



	}
