package com.org.project.TrainTicketingManagement.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.org.project.TrainTicketingManagement.domain.BookingDetails;
import com.org.project.TrainTicketingManagement.domain.Carriages;
import com.org.project.TrainTicketingManagement.domain.Client;
import com.org.project.TrainTicketingManagement.domain.Payments;
import com.org.project.TrainTicketingManagement.domain.SearchContents;
import com.org.project.TrainTicketingManagement.domain.SeatArrangement;
import com.org.project.TrainTicketingManagement.domain.Station;
import com.org.project.TrainTicketingManagement.domain.Tickets;
import com.org.project.TrainTicketingManagement.domain.Train;
import com.org.project.TrainTicketingManagement.domain.TrainSchedule;
import com.org.project.TrainTicketingManagement.dto.BookingDetailsDto;
import com.org.project.TrainTicketingManagement.service.ClientService;
import com.org.project.TrainTicketingManagement.service.CustomerPanelService;
import com.org.project.TrainTicketingManagement.serviceImpl.PaypalService;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

@Controller
@RequestMapping("/client")
public class CustomerPanelController {
	
	public static final String SUCCESS_URL = "pay/success";
	public static final String CANCEL_URL = "pay/cancel";
	
	@Autowired
	public CustomerPanelService customerPanelService;
	
	@Autowired
	public ClientService clientService;
	
	@Autowired
	PaypalService service;
	
	@ModelAttribute
	private void loginUserDetails(Model model, Principal principal, HttpSession session) {
		String username = principal.getName();
		Client client = clientService.getClientByEmail(username);
		model.addAttribute("client", client);
		session.setAttribute("userconfig", client);
	}
	
	@GetMapping("/")
	public String home(Model model, HttpSession session, Principal principle) {
		Client client = (Client) session.getAttribute("userconfig");
		client = (Client) model.getAttribute("client");
		List<Station> allStations = customerPanelService.getAvailableStaions();
		model.addAttribute("stations", allStations);
		model.addAttribute("bookingData", new SearchContents());
		session.setAttribute("client", client);
		return "client/home";
	}	
	
	@PostMapping("/searchData")
	public String getAvailableTrainForTheDate(@ModelAttribute("bookingData") SearchContents searchContents, HttpServletRequest request, Model model) {
		System.out.println("AAAAAAAA"+searchContents);
		if(searchContents.getDepartureDate()!=null && searchContents.getDepartureStation()!=null && searchContents.getDestinationStation()!=null) {
			List<TrainSchedule> availableTrains = customerPanelService.getAvailableTrain(searchContents);
			model.addAttribute("trainSchedules", availableTrains);
			return "client/searchtrain";
		}
		return "redirect:/client/";		
	}
	
	@GetMapping("/booking/{id}")
	public String bookingPage(@PathVariable Long id, Model model, HttpSession session) {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Train train = new Train();
		Client client = (Client) session.getAttribute("client");
		TrainSchedule trainSchedule = customerPanelService.getTrainScheduleById(id);
		train = trainSchedule.getTrain();
		List<Carriages> trainClasses = trainSchedule.getTrain().getCarriages();
		List<Carriages> carriageList = new ArrayList();
		BookingDetailsDto bookingDetailsDto = new BookingDetailsDto();
		bookingDetailsDto.setClientName(client.getFirstName()+" "+client.getLastName());
		bookingDetailsDto.setNic(client.getNic());
		bookingDetailsDto.setTrainName(train.getTrainName());
		bookingDetailsDto.setTrainNumber(train.getTrainNo());
		bookingDetailsDto.setDeparturStation(trainSchedule.getDeparture().getStationName());
		bookingDetailsDto.setDestinationStation(trainSchedule.getArrival().getStationName());
		bookingDetailsDto.setBookingDate(dateFormat.format(trainSchedule.getScheduleDate()));
		bookingDetailsDto.setDeparturStationId(trainSchedule.getDeparture().getStationId());
		bookingDetailsDto.setDestinationStationId(trainSchedule.getArrival().getStationId());
		bookingDetailsDto.setScheduleId(trainSchedule.getScheduleId());
		bookingDetailsDto.setClientId(client.getClientId());
		LocalDate bookingDate = LocalDate.now();
		
		carriageList = train.getCarriages();
		List<String> seatrows = new ArrayList<String>();
		List<SeatArrangement> seat = client.getSeat();		
		List<SeatArrangement> all = customerPanelService.getAllBookedSeatsByDate(bookingDate);
		
		for (SeatArrangement s : all) {
			for (String s1 : s.getSeatNo()) {
				seatrows.add(s1);
			}
		}
		
		List<Integer> booked = new ArrayList<>();
		//Carriages newarriages = new Carriages() ;
		model.addAttribute("booked", booked);
		model.addAttribute("trainClasses", trainClasses);
		model.addAttribute("bookDetails", bookingDetailsDto);
		session.setAttribute("bookingDetails", bookingDetailsDto);
		model.addAttribute("seats", seatrows);
		model.addAttribute("seat", seat);
		//model.addAttribute("newarriages", newarriages);
		return "client/booking";
	}
	
	@PostMapping("/recervedBooking")
	public String recervedBooking(@RequestParam(name ="newarriages") Long cabinid,
			@ModelAttribute("seat") SeatArrangement seatArrangement, 
									@ModelAttribute("bookDetails") BookingDetailsDto bookingDetailsDto,
									HttpSession session, Model model ) throws ParseException {
		Client client = (Client) session.getAttribute("client");
		bookingDetailsDto = (BookingDetailsDto) session.getAttribute("bookingDetails");
		System.out.println("model"+seatArrangement.getSeatNo());
		Date bookingDate = new SimpleDateFormat("yyyy-MM-dd").parse(bookingDetailsDto.getBookingDate());
		
		Carriages bookingCarriage = customerPanelService.getBookingCabineById(cabinid);
		TrainSchedule bookingSchedule = customerPanelService.getTrainScheduleById(bookingDetailsDto.getScheduleId());
		
		seatArrangement.setClient(client);
		seatArrangement.setClassType(bookingCarriage);
		seatArrangement.setTrainSchedule(bookingSchedule);
		
		seatArrangement = customerPanelService.createSeateArrangement(seatArrangement);
		
		Tickets bookingtickets = new Tickets();
		bookingtickets.setTicketNumber("EXPOTCKT"+bookingDetailsDto.getBookingDate()+client.getClientId());
		bookingtickets.setNumOfSeats(seatArrangement.getSeatNo().size());
		bookingtickets.setPrice(bookingCarriage.getJourneyprice().getPrice());
		bookingtickets.setClient(client);
		bookingtickets.setDepartureDate(bookingDate);
		bookingtickets.setDepartureStation(customerPanelService.getStationById(bookingDetailsDto.getDeparturStationId()));
		bookingtickets.setDestinationStation(customerPanelService.getStationById(bookingDetailsDto.getDestinationStationId()));
		bookingtickets.setCancelled(false);
		bookingtickets.setCancelationStatus("A");
		bookingtickets.setCancelDate(null);
		bookingtickets.setTicketDate(new Date());
		bookingtickets.setSeat(seatArrangement);
		bookingtickets = customerPanelService.createTicketBooking(bookingtickets);
		
		BookingDetails bookingDetails = new BookingDetails();
		bookingDetails.setClient(client);
		bookingDetails.setShedule(bookingSchedule);
		bookingDetails.setTicket(bookingtickets);
		bookingDetails.setBookingDate(bookingDate);		
		bookingDetails = customerPanelService.createBookingDetails(bookingDetails);
		
		double totalAmount = bookingCarriage.getJourneyprice().getPrice()*seatArrangement.getSeatNo().size();
		bookingDetailsDto.setOthers("Ticket No: "+bookingtickets.getTicketNumber()+" Cabin Class: "+seatArrangement.getClassType().getTrainclass().getDescription()+" Recerved Seats: "+seatArrangement.allSeatsRecerved(seatArrangement)+" Total Ticket Price: "+totalAmount);
		model.addAttribute("bookDetails", bookingDetailsDto);
		model.addAttribute("bookingDetails", bookingDetails);
		
		return "/client/viewbooking";
	}
	
	
	
	
	
	@GetMapping("/viewbookinghistory")
	private String viewBooking(HttpSession session, Model model) {
		Client client = (Client) session.getAttribute("client");
		
		List<Tickets> ticketList = customerPanelService.getAllTicketsByClient(client);
		List<BookingDetails> bookingHistoryList = customerPanelService.getAllBookingHisotryByClient(client);
		
		model.addAttribute("bookingDetails", bookingHistoryList);
		model.addAttribute("bookingtickets", ticketList);
		
		return "/client/payments";
	}
	
	@PostMapping("/bookingpayments")
	private String bookingPayments(HttpSession session, Model model) {
		Client client = (Client) session.getAttribute("client");
		
		List<Tickets> ticketList = customerPanelService.getAllTicketsByClient(client);
		List<BookingDetails> bookingHistoryList = customerPanelService.getAllBookingHisotryByClient(client);
		
		model.addAttribute("bookingDetails", bookingHistoryList);
		model.addAttribute("bookingtickets", ticketList);
		
		return "/client/viewbookinghistory";
	}
	
	@PostMapping("/pay")
	public String payment(@ModelAttribute("order") Payments payments) {
		try {
			Payment payment = service.createPayment(payments.getPaymentAmount(), payments.getCurrency(), payments.getMethod(),
					payments.getIntent(), payments.getDescription(), "http://localhost:8080/" + CANCEL_URL,
					"http://localhost:8080/" + SUCCESS_URL);
			for(Links link:payment.getLinks()) {
				if(link.getRel().equals("approval_url")) {
					return "redirect:"+link.getHref();
				}
			}
			
		} catch (PayPalRESTException e) {
		
			e.printStackTrace();
		}
		return "redirect:/";
	}
	
	 @GetMapping(value = CANCEL_URL)
	    public String cancelPay() {
	        return "cancel";
	    }

	    @GetMapping(value = SUCCESS_URL)
	    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
	        try {
	            Payment payment = service.executePayment(paymentId, payerId);
	            System.out.println(payment.toJSON());
	            if (payment.getState().equals("approved")) {
	                return "success";
	            }
	        } catch (PayPalRESTException e) {
	         System.out.println(e.getMessage());
	        }
	        return "redirect:/";
	    }

}
