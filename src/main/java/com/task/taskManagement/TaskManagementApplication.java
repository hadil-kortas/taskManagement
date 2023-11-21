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


		// Example of creating a new task
		Task task = new Task();
		task.setTitle("Create an admin dashboard");
		task.setDescription("admin dashboard");

		teamRepository.findById(1L).get();
		LocalDate d = LocalDate.of(2022,11,12);
		task.setDueDate(d);
		taskRepository.save(task);

	}
}