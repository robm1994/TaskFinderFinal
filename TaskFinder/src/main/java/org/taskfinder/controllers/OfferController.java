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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.taskfinder.entities.Offer;
import org.taskfinder.entities.Task;
import org.taskfinder.entities.User;
import org.taskfinder.services.MailService;
import org.taskfinder.services.OfferService;
import org.taskfinder.services.TaskService;
import org.taskfinder.services.UserService;

@Controller
public class OfferController {

	@Autowired
	private OfferService offerService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private UserService userService;

	@Autowired
	private MailService mailService;

	@GetMapping("/makeOffer")
	public String offerForm(@RequestParam(name = "id") Long id, Model model, Task task, HttpSession session) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();

		session.setAttribute("id", id);

		session.setAttribute("email", email);

		model.addAttribute("offer", new Offer());

		return "views/offerPage";

	}

	@PostMapping("/makeOffer")
	public String addOffer(@Valid Offer offer, BindingResult bindingResult, HttpSession session, Model model) {
		if (bindingResult.hasErrors()) {
			return "views/offerPage";
		}

		String email = (String) session.getAttribute("email");
		Long id = (Long) session.getAttribute("id");
		Task task = taskService.findById(id);
		String description = task.getDescription();
		String date = task.getDate();

		try {

			offerService.addOffer(offer, userService.findOne(email), taskService.findById(id));
			mailService.sendMail(session, description, date);

		} catch (Exception ex) {
			// Your exception handling code goes between these
			// curly braces, similar to the exception clause
			// in a PL/SQL block.
			return "views/duplicateOffer";
		}

		return "views/offerSuccess";

	}

	@GetMapping("/acceptedOffer")
	public String acceptOffer(Model model, @RequestParam(value = "id") String id, HttpSession session,
			Principal principal) {
		Long offer_id = Long.parseLong(id);
		Offer offer = offerService.findOne(offer_id);
		String email = principal.getName();
		User user = userService.findOne(email);
		User admin = userService.findOne("admin@mail.com");

		if (offer.getAmount() < user.getBalance()) {

			offer.setAccepted(1);
			offerService.save(offer);
			Long taskID = offer.getTask().getId();
			Task task = taskService.findById(taskID);
			task.setCompleted(1);
			taskService.save(task);

			double payment = offer.getAmount();
			userService.makePayment(user, admin, payment);
			String email_worker = offerService.findEmailByOffer(offer);
			String task1 = offer.getTask().getDescription();
			mailService.asendMail(email_worker, task1);
			
			return "views/paymentSuccess";

		} else {

			model.addAttribute("balance", user.getBalance());
			return "views/AccountTopup";

		}

	}

	@PostMapping("/acceptedOffer")
	public String acceptOffer(@Valid Offer offer, BindingResult bindingResult, HttpSession session, Model model,
			Task task) {

		return "views/acceptedOffer";

	}

	@GetMapping("/offerPage1")
	public String acceptOffer1() {

		return "views/offerPage1";

	}

}
