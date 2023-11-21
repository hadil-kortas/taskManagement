package com.task.taskManagement;

import com.task.taskManagement.dao.*;
import com.task.taskManagement.entities.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

	}
}
