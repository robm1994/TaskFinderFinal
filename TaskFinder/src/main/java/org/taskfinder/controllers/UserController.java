package org.taskfinder.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.taskfinder.entities.User;
import org.taskfinder.repositories.UserRepository;
import org.taskfinder.services.RatingService;
import org.taskfinder.services.RoleService;
import org.taskfinder.services.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private RatingService ratingService;

	private UserRepository userRepository;

	@GetMapping("/users")
	public String listUsers(Model model, @RequestParam(defaultValue = "") String name) {
		model.addAttribute("users", userService.findByName(name));
		return "views/list";
	}

	@ModelAttribute("Email")
	public void Emailfinder() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		// System.out.println(email);

	}

	
	 @GetMapping("/workers") 
	 public String listWorkers(Model model, @RequestParam(defaultValue = "") String word){
	 model.addAttribute("users", userService.findByRoles());
	 model.addAttribute("workers", userService.findByWord(word)); 
	 return "views/workerList"; 
	 }
	 
	 

		
	 

}
	 

