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
		// Example of creating a new user
		User user = new User();
		user.setUsername("hadil_kortas");
		user.setPassword("hadil123");
		userRepository.save(user);


		User user2 = new User();
		user2.setUsername("ons_belhaj");
		user2.setPassword("onsons");
		userRepository.save(user2);


		Team team1 = new Team();
		team1.setName("A");
		team1.getUsers().add(user);
		team1.getUsers().add(user2);
		teamRepository.save(team1);

		// Example of creating a TaskStatue

		TaskStatus taskStatus = new TaskStatus();
		taskStatus.setStatusName("TODO");
		taskStatus.setStatus(Statusenum.TODO);
		taskStatusRepository.save(taskStatus);


		// Example of creating a new task
		Task task = new Task();
		task.setTitle("Create an admin dashboard");
		task.setDescription("admin dashboard");

		Team team = teamRepository.findById(1L).orElse(null);
		if (team != null) {
			task.setTeam(team1);
		} else {
			// Handle the case where the team with the given ID is not found
			// You might want to log an error or throw an exception
			System.out.println("Team not found with ID 1");
		}
		LocalDate d = LocalDate.of(2022,11,12);
		task.setDueDate(d);
		taskRepository.save(task);

		Task task1 = Task.builder()
				.title("Implement Feature X")
				.description("Implement a new feature in the system")
				.dueDate(LocalDate.of(2022,11,13))
				.team(team1)
				.taskStatus(TaskStatus.builder().statusName("in progress").status(Statusenum.INPROGRESS).build())
				.build();
		taskRepository.save(task1);



		// Example of creating a Role

		Role role = new Role();
		role.setRoleName("Admin");
		role.getUsers().add(user);
		roleRepository.save(role);




	};



	}
