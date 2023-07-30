package com.org.project.TrainTicketingManagement.serviceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.project.TrainTicketingManagement.domain.BookingDetails;
import com.org.project.TrainTicketingManagement.domain.Carriages;
import com.org.project.TrainTicketingManagement.domain.Client;
import com.org.project.TrainTicketingManagement.domain.SearchContents;
import com.org.project.TrainTicketingManagement.domain.SeatArrangement;
import com.org.project.TrainTicketingManagement.domain.Station;
import com.org.project.TrainTicketingManagement.domain.Tickets;
import com.org.project.TrainTicketingManagement.domain.Train;
import com.org.project.TrainTicketingManagement.domain.TrainSchedule;
import com.org.project.TrainTicketingManagement.repository.BookingDetailsRepository;
import com.org.project.TrainTicketingManagement.repository.ClientRepository;
import com.org.project.TrainTicketingManagement.repository.CustomerPanelRepository;
import com.org.project.TrainTicketingManagement.repository.StationRepository;
import com.org.project.TrainTicketingManagement.repository.TicketRepository;
import com.org.project.TrainTicketingManagement.repository.TrainCarriagesRepository;
import com.org.project.TrainTicketingManagement.repository.TrainSeatArrangementRepository;
import com.org.project.TrainTicketingManagement.service.CustomerPanelService;

@Service
public class CustomerPanelServiceImpl implements CustomerPanelService{
	
	@Autowired
	public CustomerPanelRepository customerPanelRepository;
	
	@Autowired
	public StationRepository stationRepository;
	
	@Autowired
	public TicketRepository ticketRepository;
	
	@Autowired
	public BookingDetailsRepository bookingDetailsRepository;
	
	@Autowired
	public TrainCarriagesRepository trainCarriagesRepository;
	
	@Autowired
	public TrainSeatArrangementRepository trainSeatArrangementRepository;

	@Override
	public List<TrainSchedule> getAvailableTrain(SearchContents searchContents) {
		//return customerPanelRepository.findAll();
		return customerPanelRepository.findTrainScheduleByDepartureAndArrivalAndScheduleDateAndStatus(searchContents.getDepartureStation(), searchContents.getDestinationStation(), searchContents.getDepartureDate());
	}

	@Override
	public List<TrainSchedule> getAllAvailableTrain() {
		return customerPanelRepository.findAll();
	}

	@Override
	public List<Station> getAvailableStaions() {
		return stationRepository.findAll();
	}

	@Override
	public TrainSchedule getTrainScheduleById(Long id) {
		return customerPanelRepository.findById(id).get();
	}

	@Override
	public List<Carriages> getTrainClassesByTrainId(Train train) {
		return null;//carriagesRepository.getTrainClassesByTrainId((int) train.getTrainId());
	}

	@Override
	public List<SeatArrangement> getAllBookedSeatsByDate(LocalDate bookingDate) {
		List<SeatArrangement> list = new ArrayList();
		return list;
	}

	@Override
	public Station getStationById(Long departurStationId) {
		return stationRepository.findById(departurStationId).get();
	}

	@Override
	public Tickets createTicketBooking(Tickets bookingtickets) {
		return ticketRepository.save(bookingtickets);
	}
	
	@Override
	public BookingDetails createBookingDetails(BookingDetails bookingDetails) {
		return bookingDetailsRepository.saveAndFlush(bookingDetails);
	}

	@Override
	public List<Tickets> getAllTicketsByClient(Client client) {
		return ticketRepository.findTicketsByClient(client);
	}

	@Override
	public List<BookingDetails> getAllBookingHisotryByClient(Client client) {
		return bookingDetailsRepository.findBookingDetailsByClient(client);
	}

	@Override
	public Carriages getBookingCabineById(Long cabinid) {
		return trainCarriagesRepository.findById(cabinid).get();
	}

	@Override
	public SeatArrangement createSeateArrangement(SeatArrangement seatArrangement) {
		return trainSeatArrangementRepository.save(seatArrangement);
	}

}
