package org.taskfinder;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.taskfinder.entities.User;
import org.taskfinder.entities.Task;
//import org.taskfinder.services.OfferService;
import org.taskfinder.services.TaskService;
import org.taskfinder.services.UserService;

@SpringBootApplication
public class TaskFinderApplication implements CommandLineRunner {
	@Autowired
	private UserService userService;
	@Autowired
	private TaskService taskService;
	// private OfferService offerService;

	public static void main(String[] args) {
		SpringApplication.run(TaskFinderApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		{
			User newAdmin = new User("admin@mail.com", "Admin", "123456", "gfdg", "086456124");
			userService.createAdmin(newAdmin);

			User newWorker = new User("robmoore941@gmail.com", "Worker", "1234567", "Plumbing", "0872310760");
			userService.createWorker(newWorker);

			User newWorker1 = new User("dudley1@gmail.com", "Worker", "hello123", "Electrics", "0872310760");
			userService.createWorker(newWorker1);

			User newWorker2 = new User("C15801977@mydit.ie", "Worker", "hello123", "Electrics", "0872310760");
			userService.createWorker(newWorker2);

		}
	}

}
