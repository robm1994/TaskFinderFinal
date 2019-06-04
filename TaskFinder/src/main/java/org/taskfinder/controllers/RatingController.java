package org.taskfinder.controllers;

import java.security.Principal;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.taskfinder.entities.Offer;
import org.taskfinder.entities.Rating;
import org.taskfinder.entities.Task;
import org.taskfinder.entities.User;
import org.taskfinder.services.OfferService;
import org.taskfinder.services.RatingService;
import org.taskfinder.services.TaskService;
import org.taskfinder.services.UserService;

import ch.qos.logback.core.net.SyslogOutputStream;

@Controller
public class RatingController {

	@Autowired
	private TaskService taskService;

	@Autowired
	private UserService userService;

	@Autowired
	private RatingService ratingService;

	@Autowired
	private OfferService offerService;
	
	@GetMapping("/Ratings")
	public String LeaveRating(Model model, Principal principal) {
		String email = principal.getName();
		User user = userService.findOne(email);

		// int completed;
		// List<Task> task = taskService.findByCompleted(completed);
		// if(taskService.getCompleted() == 1) {
		model.addAttribute("tasks", taskService.findUserTask(user));
		// model.addAttribute("tasks", taskService.findByCompleted(completed);
		//model.addAttribute("tasks" )
		model.addAttribute("tasks", taskService.findByCompleted());
		model.addAttribute("ratings", ratingService.findUserRating(user));
		model.addAttribute("name", user.getName());

		return "views/Ratings";
	}


	@GetMapping("/leaveRating")
	public String leaveRating(@RequestParam(name = "id") Long id, Model model, Task task, HttpSession session) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		String email1 = offerService.findByAcceptedAndTask(task).getUser().getEmail();
		session.setAttribute("email", email);
		session.setAttribute("id", id);
		session.setAttribute("email1", email1);
		model.addAttribute("rating", new Rating());

		return "views/leaveRating";
	}

	@PostMapping("/leaveRating")
	public String leaveARating(@Valid Rating rating, BindingResult bindingResult, Model model, HttpSession session) {
		if (bindingResult.hasErrors()) {
			//return "views/offerPage";
		}

		String email = (String) session.getAttribute("email");
		System.out.println(email);

		Long id = (Long) session.getAttribute("id");
		String email1 = (String) session.getAttribute("email1");
		System.out.println(email1);

		try {

			ratingService.addRating(rating, userService.findOne(email), taskService.findById(id),
			userService.findOne(email1));

			// return "views/offerSuccess";

		} catch (Exception ex) {
			// Your exception handling code goes between these
			// curly braces, similar to the exception clause
			// in a PL/SQL block.
			return "views/duplicateRating";
		}

		// ratingService.
		return "index";
	}

}
