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

		User user3 = new User();
		user3.setUsername("achour_mariem");
		user3.setPassword("achourmariem");
		userRepository.save(user3);


		User user4 = new User();
		user4.setUsername("jhon_jhon");
		user4.setPassword("jhon");
		userRepository.save(user4);



		Team team1 = new Team();
		team1.setName("A");
		team1.getUsers().add(user);
		team1.getUsers().add(user2);
		teamRepository.save(team1);

		Team team2 = new Team();
		team2.setName("B");
		team2.getUsers().add(user3);
		team2.getUsers().add(user4);
		teamRepository.save(team2);

		// Example of creating a TaskStatue

		TaskStatus taskStatus = new TaskStatus();
		taskStatus.setStatusName("TODO");
		taskStatus.setStatus(Statusenum.TODO);
		taskStatus.setProgressPercentage(10);
		taskStatusRepository.save(taskStatus);


		// Example of creating a new task

		Task task1 = Task.builder()
				.title("Implement Feature X")
				.description("Implement a new feature in the system")
				.dueDate(LocalDate.of(2022,11,13))
				.team(team1)
				.taskStatus(TaskStatus.builder().statusName("in progress").status(Statusenum.INPROGRESS).progressPercentage(50).build())
				.build();
		taskRepository.save(task1);
		/*Task task3 = Task.builder()
				.title("Implement Feature Y")
				.description("Implement a new feature in the system")
				.dueDate(LocalDate.of(2022,11,13))
				.team(team1)
				.taskStatus(TaskStatus.builder().statusName("All Most Done").status(Statusenum.ALLMOSTDONE).progressPercentage(75).build())
				.build();
		taskRepository.save(task3);*/

		Task task2 = Task.builder()
				.title("Implement Admin dashboard")
				.description("Implement a new feature in the system")
				.dueDate(LocalDate.of(2022,11,14))
				.team(team2)
				.taskStatus(TaskStatus.builder().statusName("DONE").status(Statusenum.DONE).progressPercentage(100).build())
				.build();
		taskRepository.save(task2);



		// Example of creating a Role

		Role role = new Role();
		role.setRoleName("Admin");
		role.getUsers().add(user);
		roleRepository.save(role);




	};



	}
