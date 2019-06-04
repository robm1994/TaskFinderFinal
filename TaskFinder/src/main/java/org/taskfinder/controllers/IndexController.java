package org.taskfinder.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.taskfinder.entities.User;
import org.taskfinder.services.UserService;

@Controller
public class IndexController {

	@GetMapping("/")
	public String showIndexPage() {

		return "index";
	}

	@GetMapping("/login")
	public String showLoginForm() {

		return "views/loginForm";
	}

	@GetMapping("/loginWorker")
	public String showWorkerLoginForm() {

		return "views/workerLogin";
	}

	@GetMapping("/about")
	public String aboutPage() {

		return "views/about";
	}

}
