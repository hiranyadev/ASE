package com.org.project.TrainTicketingManagement.controller;

import javax.servlet.http.HttpSession;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.org.project.TrainTicketingManagement.domain.Client;
import com.org.project.TrainTicketingManagement.domain.TrainSchedule;
import com.org.project.TrainTicketingManagement.service.CustomerPanelService;

@Controller
@RequestMapping("/client")
public class CustomerPanelController {
	
	@Autowired
	public CustomerPanelService customerPanelService;
	
	@GetMapping("/")
	public String home() {
		return "client/home";
	}	
	
	@GetMapping("/allAvailableTrains/{id}")
	public String getAvailableTrain(@PathVariable("date") Date date, Model model, HttpSession session) {
		Client client = (Client) session.getAttribute("loginuser");
		if(client.getClientId()>0) {
			List<TrainSchedule> trainShedule = customerPanelService.getAvailableTrain(date);
		}else {
			
		}
		return null;		
	}

}
