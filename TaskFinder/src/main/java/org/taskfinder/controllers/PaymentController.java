package org.taskfinder.controllers;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.taskfinder.entities.Offer;
import org.taskfinder.entities.User;
import org.taskfinder.services.UserService;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

@Controller
public class PaymentController {

	@Autowired
	UserService userService;
	
	
	@GetMapping("/payment")
	public String showPaymentPage(Model model, Principal principal, @RequestParam(name="id") Long id, Offer offer) {

		String email = principal.getName();
		User user = userService.findOne(email);
		User admin = userService.findOne("admin@mail.com");
		System.out.println(admin.getBalance());
		//model.addAttribute("balance", admin.getBalance());
		model.addAttribute("balance", user.getBalance());

		return "views/payment";
	}

	@PostMapping("/payment")
	public String makePayment(HttpServletRequest request) {

		try {
			Stripe.apiKey = "sk_test_N56IDcudcRzuzTxffuR5IvSo00JRh882Gg";
			Map<String, Object> chargeParams = new HashMap<String, Object>();
			double price = (Double.parseDouble(request.getParameter("amount")) * 100);
			int eventPrice = (int) Math.round(price);
			chargeParams.put("amount", eventPrice);
			chargeParams.put("currency", "eur");
			chargeParams.put("description", "Top up");
			chargeParams.put("source", "tok_amex");

			Charge.create(chargeParams);
		} catch (StripeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findOne(auth.getName());
		User admin = userService.findOne("admin@mail.com");
		double payment = Double.parseDouble(request.getParameter("amount"));
		userService.makePayment(user, admin, payment);
		userService.topUpAccount(admin, payment);

		return "views/payment";

	}

	@GetMapping("/topup")
	public String showTopUpPage(Model model, Principal principal) {
		String email = principal.getName();
		User user = userService.findOne(email);
		model.addAttribute("balance", user.getBalance());

		return "views/AccountTopUp";
	}

	@PostMapping("/topup")
	public String makeTopUp(HttpServletRequest request) {

		try {
			Stripe.apiKey = "sk_test_N56IDcudcRzuzTxffuR5IvSo00JRh882Gg";
			Map<String, Object> chargeParams = new HashMap<String, Object>();
			double price = (Double.parseDouble(request.getParameter("amount")) * 100);
			int eventPrice = (int) Math.round(price);
			chargeParams.put("amount", eventPrice);
			chargeParams.put("currency", "eur");
			chargeParams.put("description", "Top up");
			chargeParams.put("source", "tok_amex");

			Charge.create(chargeParams);
		} catch (StripeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findOne(auth.getName());
		double topup = Double.parseDouble(request.getParameter("amount"));
		userService.topUpAccount(user, topup);

		return "views/topupSuccess";

	}
	
	@GetMapping("/withdraw")
	public String withdrawPage(Model model, Principal principal) {
		String email = principal.getName();
		User user = userService.findOne(email);
		model.addAttribute("balance", user.getBalance());

		return "views/AccountWithdraw";
	}
	
	
	@PostMapping("/withdraw")
	public String makeWithdraw(HttpServletRequest request) {

		try {
			Stripe.apiKey = "sk_test_N56IDcudcRzuzTxffuR5IvSo00JRh882Gg";
			Map<String, Object> chargeParams = new HashMap<String, Object>();
			double price = (Double.parseDouble(request.getParameter("amount")) * 100);
			int eventPrice = (int) Math.round(price);
			chargeParams.put("amount", eventPrice);
			chargeParams.put("currency", "eur");
			chargeParams.put("description", "Top up");
			chargeParams.put("source", "tok_amex");

			Charge.create(chargeParams);
		} catch (StripeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findOne(auth.getName());
		double withdraw = Double.parseDouble(request.getParameter("amount"));
		userService.withdrawAccount(user, withdraw);

		return "views/AccountWithdraw";

	}
	
}
