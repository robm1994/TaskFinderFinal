package org.taskfinder.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.taskfinder.entities.Offer;
import org.taskfinder.entities.Task;
import org.taskfinder.entities.User;
import org.taskfinder.services.OfferService;
import org.taskfinder.services.TaskService;
import org.taskfinder.services.UserService;

@Controller
public class CalendarController {
	@Autowired
	OfferService offerService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	TaskService taskService;

 
@GetMapping("/Schedule")
public String Schedule (Model model,  Principal principal) {
	String csv = "";
	String email = principal.getName();
	User user = userService.findOne(email);
	model.addAttribute("name", user.getName());
	
	ArrayList<Offer> offers = new ArrayList<Offer>();
	offers.addAll(offerService.findByAcceptedAndUser(user));
	
	List<Task> tasks = new ArrayList<>();
	for(int i= 0; i < offers.size(); i++) {
		Offer accepted = offers.get(i);
		Task task = accepted.getTask();
		tasks.add(task);
		//System.out.println(task.getDescription());
	}
		
	for(int j=0; j < tasks.size(); j++) {
			
			Task task1= tasks.get(j);
			System.out.print(task1);
			
			csv = csv+task1.getAddress()+ "|" +task1.getDate()+ "|" + task1.getTime()+ "," ;		
			System.out.println(csv);
		
	
	
	}
		csv = csv.substring(0, csv.length()-1);
		model.addAttribute("tasks", csv);
		return "views/workerSchedule";
		
		

	

}
	
	
}
		
	
	


//List<> performanceRestrictionsList = performanceRestrictionsService.findUserPerformance(user);
//	for (PerformanceRestrictions performanceRestrictions : performanceRestrictionsList) {
	
//	csv = csv+performanceRestrictions.getMarker().getName()+"|"+performanceRestrictions.getDate()+"|"+performanceRestrictions.getTime()+",";
//	}
//	csv = csv.substring(0, csv.length()-1);
//	model.addAttribute("myPerformances",csv);
//	return "views/myCal";
//}
	
	

