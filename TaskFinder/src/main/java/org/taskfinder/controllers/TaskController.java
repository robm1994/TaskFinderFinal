package org.taskfinder.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

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
import org.taskfinder.entities.Task;
import org.taskfinder.entities.User;
import org.taskfinder.repositories.TaskRepository;
import org.taskfinder.services.OfferService;
import org.taskfinder.services.TaskService;
import org.taskfinder.services.UserService;

@Controller
public class TaskController {

	@Autowired
	private TaskService taskService;
	@Autowired
	private UserService userService;
	@Autowired
	private OfferService offerService;
	@Autowired
	private TaskRepository taskRepository;

	@GetMapping("/addTask")
	public String taskForm(Model model, HttpSession session) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		String email = auth.getName();

		session.setAttribute("email", email);

		model.addAttribute("task", new Task());

		return "views/taskForm";

	}

	@PostMapping("/addTask")
	public String addTask(@Valid Task task, BindingResult bindingResult, HttpSession session) {
		if (bindingResult.hasErrors()) {
			return "views/taskForm";
		}

		String email = (String) session.getAttribute("email");
		taskService.addTask(task, userService.findOne(email));

		return "index";
	}

	@GetMapping("/tasks")
	public String listTasks(Model model, @RequestParam(defaultValue = "")String name) {
//		model.addAttribute("tasks", taskService.findByUncompleted());
//		model.addAttribute("tasks", taskService.findByName(name));
		
		List<Task> search = taskService.findByName(name);
		List<Task> uncompleted = taskService.findByUncompleted();
		List<Task> tasks = new ArrayList<>();
		
		for (int i = 0; i < search.size(); i++) {
			Task match = search.get(i);
			
			if (uncompleted.contains(match)) {
				tasks.add(match);
			}
		}
		
		model.addAttribute("tasks", tasks);
		
		
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		String email = auth.getName();
//		
//		session.setAttribute("id", id);
//		System.out.println(id);
//
//		session.setAttribute("email", email);
//
    	//model.addAttribute("offer", new Offer());
//		
		
		return "views/taskList";
	}

	@GetMapping("/myPostings")
	public String showProfilePage(Model model, Principal principal, String name) {

		String email = principal.getName();
		User user = userService.findOne(email);
		System.out.println(user);
		model.addAttribute("tasks", taskService.findUserTask(user));
		model.addAttribute("users", userService.findByName(name));
		model.addAttribute("name", user.getName());

		return "views/myPostings";
	}

	@GetMapping("/offerList")
	public String showofferList(Model model, Principal principal) {

		String email = principal.getName();
		User user = userService.findOne(email);

		model.addAttribute("offers", offerService.findUserOffer(user));
		model.addAttribute("name", user.getName());

		return "views/offerList";
	}

	@GetMapping("/taskOffers")
	public String showTaskOffers(Model model, @RequestParam(name = "id") Long id) {

		Task task = taskService.findById(id);

		model.addAttribute("offers", offerService.findTaskOffer(task));

		return "views/taskOffers";
	}

	
}
