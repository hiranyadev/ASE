package com.org.project.TrainTicketingManagement.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.org.project.TrainTicketingManagement.domain.Carriages;
import com.org.project.TrainTicketingManagement.domain.Client;
import com.org.project.TrainTicketingManagement.domain.SearchContents;
import com.org.project.TrainTicketingManagement.domain.Station;
import com.org.project.TrainTicketingManagement.domain.Train;
import com.org.project.TrainTicketingManagement.domain.TrainClasses;
import com.org.project.TrainTicketingManagement.domain.TrainSchedule;
import com.org.project.TrainTicketingManagement.dto.BookingDetailsDto;
import com.org.project.TrainTicketingManagement.dto.TrainSearchDetails;
import com.org.project.TrainTicketingManagement.service.CustomerPanelService;

@Controller
@RequestMapping("/client")
public class CustomerPanelController {
	
	@Autowired
	public CustomerPanelService customerPanelService;
	
	@GetMapping("/")
	public String home(Model model) {
		List<Station> allStations = customerPanelService.getAvailableStaions();
		model.addAttribute("stations", allStations);
		model.addAttribute("bookingData", new SearchContents());
		return "client/home";
	}	
	
	@GetMapping("/allAvailableTrains/{id}")
	public String getAvailableTrain(@PathVariable("date") Date date, Model model, HttpSession session) {
		Client client = (Client) session.getAttribute("loginuser");
		if(client.getClientId()>0) {
			List<TrainSchedule> trainShedule = customerPanelService.getAvailableTrain(new SearchContents());
		}else {
			
		}
		return null;		
	}
	
	@PostMapping("/searchData")
	public String getAvailableTrainForTheDate(@ModelAttribute("bookingData") SearchContents searchContents, HttpServletRequest request, Model model) {
		System.out.println("AAAAAAAA"+searchContents);
		List<TrainSchedule> availableTrains = customerPanelService.getAvailableTrain(searchContents);
		model.addAttribute("trainSchedules", availableTrains);
		return "client/searchtrain";		
	}
	
	@GetMapping("/booking/{id}")
	public String bookingPage(@PathVariable Long id, Model model) {
		Train train = new Train();
		TrainSchedule trainSchedule = customerPanelService.getTrainScheduleById(id);
		List<Carriages> trainClasses = trainSchedule.getTrain().getCarriages();
		List<Carriages> carriageList = new ArrayList();
		BookingDetailsDto bookingDetailsDto = new BookingDetailsDto();
		bookingDetailsDto.setClientName("Anura");
		bookingDetailsDto.setNic("987456321V");
		bookingDetailsDto.setTrainName("Uththaradewi");
		bookingDetailsDto.setTrainNumber("11012");
		bookingDetailsDto.setDeparturStation("Colombo Fort");
		bookingDetailsDto.setDestinationStation("Kandy");
		bookingDetailsDto.setBookingDate("12/08/2023");
		train = trainSchedule.getTrain();
		carriageList = train.getCarriages();
		List<String> seatrows = new ArrayList<>();
		List<Integer> booked = new ArrayList<>();
		model.addAttribute("booked", booked);
		model.addAttribute("trainClasses", trainClasses);
		model.addAttribute("bookDetails", bookingDetailsDto);
		model.addAttribute("seats", seatrows);
		return "client/booking";
	}
	
	//@PostMapping("/recevedBooking/{clientId}/{scheduleId}")

}
