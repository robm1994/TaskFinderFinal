package org.taskfinder.controllers;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.taskfinder.entities.Rating;
import org.taskfinder.entities.User;
import org.taskfinder.services.OfferService;
import org.taskfinder.services.RatingService;
import org.taskfinder.services.TaskService;
import org.taskfinder.services.UserService;

@Controller
public class ProfileController {

	@Autowired
	private TaskService taskService;
	@Autowired
	private UserService userService;
	@Autowired
	private OfferService offerService;
	@Autowired
	private RatingService ratingService;

	@GetMapping("/myProfile")
	public String showProfilePage(Model model, Principal principal, String name, Rating rating) {

		String email = principal.getName();
		User worker = userService.findOne(email);
		model.addAttribute("ratings", ratingService.findWorkerRating(worker));
		model.addAttribute("users", userService.findOne(email));
		model.addAttribute("name", worker.getName());
		model.addAttribute("skill", worker.getSkill());
		model.addAttribute("number", worker.getNumber());
		model.addAttribute("email", worker.getEmail());

		ArrayList<Rating> ratings = new ArrayList<Rating>();
		ratings.addAll(ratingService.findWorkerRating(worker));

		double average = 0;
		double total = 0;
		for (int i = 0; i < ratings.size(); i++) {
			Rating rating_1 = ratings.get(i);
			System.out.println(rating_1);
			total += rating_1.getStars();
			average = (total / ratings.size());

			model.addAttribute("average", average);

		}

		return "views/userProfile";

	}

	@GetMapping("/workerProfile")
	public String showWorkerProfilePage(Model model, Principal principal, @RequestParam(name = "email") String email,
			Rating rating) {

		User worker = userService.findOne(email);
		model.addAttribute("ratings", ratingService.findWorkerRating(worker));
		model.addAttribute("users", userService.findOne(email));
		model.addAttribute("name", worker.getName());
		model.addAttribute("skill", worker.getSkill());
		model.addAttribute("number", worker.getNumber());
		model.addAttribute("email", worker.getEmail());

		ArrayList<Rating> ratings = new ArrayList<Rating>();
		ratings.addAll(ratingService.findWorkerRating(worker));

		double average = 0;
		double total = 0;
		for (int i = 0; i < ratings.size(); i++) {
			Rating rating_1 = ratings.get(i);
			System.out.println(rating_1);
			total += rating_1.getStars();
			average = (total / ratings.size());
		



			model.addAttribute("average", average);

		}

		return "views/userProfile";

	}

//	@GetMapping("/offerList")
//	public String showofferList(Model model, Principal principal) {
//		
//		String email = principal.getName();
//		User user = userService.findOne(email);
//		
//		
//		model.addAttribute("offers", offerService.findUserOffer(user));
//		
//		
//		return "views/offerList";
//	}
//	

}
