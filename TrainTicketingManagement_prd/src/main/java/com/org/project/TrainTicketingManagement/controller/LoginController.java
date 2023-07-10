package com.org.project.TrainTicketingManagement.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.org.project.TrainTicketingManagement.domain.Client;
import com.org.project.TrainTicketingManagement.service.ClientService;
import com.org.project.TrainTicketingManagement.service.CustomerPanelService;

@Controller
public class LoginController {
	
	@Autowired
	public ClientService clientService;
	
	@Autowired
	public CustomerPanelService customerPanelService;
	
	@GetMapping("/")
	public String login() {
		return "index";
	}
	
	@GetMapping("/signin")
	public String userLogin() {
		return "login";
	}
	
	@PostMapping("/signup")
	public String userRegistration(@ModelAttribute Client client, HttpSession session) {
		boolean clientAlreadyExist = clientService.checkclientAlreadyExistByEmail(client.getEmail());
		
		if(clientAlreadyExist) {
			session.setAttribute("msg", "Your already register with this email address. Please Login");
		}else {
			Client createdClient = clientService.createClient(client);
			if(createdClient != null)
				session.setAttribute("msg", "Welcome to Online ticketing system. Please Login");
			else
				session.setAttribute("msg", "Somthing went wrong! Please try again later");
		}
		
		return "redirect:/signin";
	}
	
	/*
	 * @GetMapping("/login") public String signInToSystem() {
	 * //clientService.createClient(client); return "home"; }
	 * 
	 * @GetMapping("/home") public String home(Model model) { //List<TrainSchedule>
	 * availableTrains = customerPanelService.getAvailableTrain(new Date());
	 * //model.addAttribute("trainSchedule", availableTrains); return "home"; }
	 */
	
	@GetMapping("/forgotPassword")
	public String forgotPassword() {
		return "login";
	}

}