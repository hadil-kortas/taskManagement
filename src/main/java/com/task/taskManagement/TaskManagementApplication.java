package com.task.taskManagement;

import com.task.taskManagement.dao.*;
import com.task.taskManagement.entities.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

@SpringBootApplication
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
		// Example of creating a new role
		Role role = new Role();
		role.setRoleName("ROLE_USER");
		roleRepository.save(role);

		// Example of creating a new team
		Team team = new Team();
		team.setName("tigers");
		teamRepository.save(team);

		// Example of creating a new user
		User user = new User();
		user.setUsername("john_doe");
		user.setPassword("password123");
		user.setRoles(new HashSet<>(Arrays.asList(role)));  // Assigning the role to the user
		//user.setTasks(new HashSet<>());  // Initializing an empty task set
		user.setTeams(new HashSet<>(Arrays.asList(team)));  // Assigning the team to the user
		userRepository.save(user);

		// Example of creating a new task
		Task task = new Task();
		task.setTitle("Sample Task");
		task.setDescription("This is a sample task.");
		task.setDueDate(LocalDate.now().plusDays(7));
		task.setUser(user);  // Assigning the task to the user
		task.setTeam(team);
		taskRepository.save(task);

		// Example of retrieving tasks and users
		Iterable<Task> tasks = taskRepository.findAll();
		for (Task t : tasks) {
			System.out.println("Task: " + t.getTitle() + ", Assigned to: " + t.getUser().getUsername());
		}

		Iterable<User> users = userRepository.findAll();
		for (User u : users) {
			System.out.println("User: " + u.getUsername() +
					", Roles: " + u.getRoles().toString() +
					", Teams: " + u.getTeams().toString() +
					", Number of tasks: " + u.getTasks().size());
		}
	}
}
