package com.org.project.TrainTicketingManagement.controller;

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

import com.org.project.TrainTicketingManagement.domain.CabinDetails;
import com.org.project.TrainTicketingManagement.domain.Carriages;
import com.org.project.TrainTicketingManagement.domain.Client;
import com.org.project.TrainTicketingManagement.domain.Station;
import com.org.project.TrainTicketingManagement.domain.Train;
import com.org.project.TrainTicketingManagement.domain.TrainClasses;
import com.org.project.TrainTicketingManagement.domain.TrainLongPriceses;
import com.org.project.TrainTicketingManagement.domain.TrainSchedule;
import com.org.project.TrainTicketingManagement.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminPanelController {
	
	@Autowired
	public AdminService adminService;
	
	@GetMapping("/")
	public String adminHome() {
		return "admin/adminHome";
	}
	
	@GetMapping("/adminManageTrains")
	public String adminManageTrains(Model model) {
		List<Train> trains = adminService.getAllTrains();
		Train train = new Train();
		model.addAttribute("newtrain", train);
		model.addAttribute("trains", trains);
		return "admin/adminManageTrains";
	}
	
	@GetMapping("/adminManageSchedules")
	public String adminManageSchedules(Model model) {
		List<TrainSchedule> scheduleList = adminService.getAllSchedulesAfterDate(new Date());
		List<Station> stations = adminService.getAllStations();
		List<Train> trains = adminService.getAllTrains();
		TrainSchedule newschedule = new TrainSchedule();
		model.addAttribute("newschedule", newschedule);
		model.addAttribute("scheduleList", scheduleList);
		model.addAttribute("stations", stations);
		model.addAttribute("trains", trains);
		return "admin/adminManageSchedules";
	}
	
	@GetMapping("/adminManageStations")
	public String adminManageStations(Model model) {
		List<Station> stations = adminService.getAllStations();
		Station station = new Station();
		model.addAttribute("newstation", station);
		model.addAttribute("stations", stations);
		return "admin/adminManageStations";
	}
	
	@GetMapping("/adminManageBooking")
	public String adminManageBooking() {
		return "admin/adminManageBooking";
	}
	
	@GetMapping("/adminManageClient")
	public String adminManageClient(Model model) {
		List<Client> clients = adminService.getAllClient();
		model.addAttribute("clients", clients);
		return "admin/adminManageClient";
	}
	
	@GetMapping("/adminReports")
	public String adminReports() {
		return "admin/adminReports";
	}
	
	@GetMapping("/adminManageClasses")
	public String adminManageClasses(Model model) {
			List<TrainClasses> trainClasses = adminService.getAllTrainClasses();
			TrainClasses trainClass = new TrainClasses();
			model.addAttribute("newclass", trainClass);
			model.addAttribute("classess", trainClasses);
		return "admin/adminManageClasses";
	}
	
	@GetMapping("/adminManageCabins")
	public String adminManageCabins(Model model) {
			List<Carriages> trainCabins = adminService.getAllTrainCabins();
			List<TrainClasses> trainClasses = adminService.getAllTrainClasses();
			List<Train> trains = adminService.getAllTrains();
			List<CabinDetails> trainCabinsDetails = new ArrayList<>();
			for(Carriages cabins : trainCabins) {
				CabinDetails trainCabin = new CabinDetails();
				trainCabin.setTrainname(cabins.getTrain().getTrainName());
				trainCabin.setCabinclass(cabins.getTrainclass().getDescription());
				trainCabin.setCarriageno(cabins.getCarriageno());
				trainCabin.setCarriageId(cabins.getCarriageId());
				trainCabinsDetails.add(trainCabin);
			}
			Carriages Carriages = new Carriages();
			model.addAttribute("trains", trains);
			model.addAttribute("cabins", trainCabinsDetails);
			model.addAttribute("newcabin", Carriages);
			model.addAttribute("classess", trainClasses);
		return "admin/adminManageCabins";
	}
	
	@GetMapping("/adminManageJourneyPrices")
	public String adminManageJourneyPrices(Model model) {
		List<Station> stations = adminService.getAllStations();
		List<TrainLongPriceses> pricesList = adminService.getAllPricess();
		List<TrainClasses> trainClasses = adminService.getAllTrainClasses();
		TrainLongPriceses newJourney = new TrainLongPriceses();
		model.addAttribute("newJourney", newJourney);
		model.addAttribute("pricesList", pricesList);
		model.addAttribute("stations", stations);
		model.addAttribute("trainsclass", trainClasses);
		return "admin/adminManageJourneyPrices";
	}
	
	@PostMapping("/addstation")
	public String addNewStation(@ModelAttribute("newstation") Station station) {
		adminService.saveStation(station);
		System.out.println(station.getStationName());
		return "redirect:/admin/adminManageStations";
	}
	
	@GetMapping("/removestation/{id}")
	public String removeStation(@PathVariable Long id) {
		adminService.deleteStationById(id);
		return "redirect:/admin/adminManageStations";
	}
	
	@PostMapping("/addtrain")
	public String addNewStation(@ModelAttribute("newtrain") Train train) {
		adminService.saveTrain(train);
		System.out.println(train.getTrainName());
		return "redirect:/admin/adminManageTrains";
	}
	
	@GetMapping("/removetrain/{id}")
	public String deleteTrain(@PathVariable Long id) {
		adminService.deleteTrainById(id);
		return "redirect:/admin/adminManageTrains";
	}
	
	@PostMapping("/addclass")
	public String addNewStation(@ModelAttribute("newclass") TrainClasses trainClasses) {
		adminService.saveTrainClass(trainClasses);
		System.out.println(trainClasses.getDescription());
		return "redirect:/admin/adminManageClasses";
	}
	
	@GetMapping("/removeclass/{id}")
	public String deleteTrainClass(@PathVariable Long id) {
		adminService.deleteTrainClassById(id);
		return "redirect:/admin/adminManageClasses";
	}
	
	@PostMapping("/addcabin")
	public String addNewCabin(@ModelAttribute("newcabin") Carriages trainCabins) {
		adminService.saveTrainCabin(trainCabins);
		System.out.println(trainCabins.getCarriageId());
		return "redirect:/admin/adminManageCabins";
	}
	
	@GetMapping("/removecabin/{id}")
	public String deleteTrainCabin(@PathVariable Long id) {
		adminService.deleteTrainCabinById(id);
		return "redirect:/admin/adminManageCabins";
	}
	
	@PostMapping("/addnewschedule")
	public String addNewSchedule(@ModelAttribute("newschedule") TrainSchedule trainSchedule) {
		trainSchedule.setStatus(1);
		adminService.saveTrainSchedule(trainSchedule);
		System.out.println(trainSchedule.getTrain().getTrainName());
		return "redirect:/admin/adminManageSchedules";
	}
	
	@GetMapping("/removeschedule/{id}")
	public String deleteschedule(@PathVariable Long id) {
		adminService.deleteScheduleById(id);
		return "redirect:/admin/adminManageSchedules";
	}
	
	@PostMapping("/addnewJourney")
	public String addNewJourney(@ModelAttribute("newJourney") TrainLongPriceses trainLongPriceses) {
		adminService.saveJourneyPrices(trainLongPriceses);
		System.out.println(trainLongPriceses.getPrice());
		return "redirect:/admin/adminManageJourneyPrices";
	}
	
	@GetMapping("/removeJourney/{id}")
	public String deleteJourney(@PathVariable Long id) {
		adminService.deleteScheduleById(id);
		return "redirect:/admin/adminManageJourneyPrices";
	}
}
