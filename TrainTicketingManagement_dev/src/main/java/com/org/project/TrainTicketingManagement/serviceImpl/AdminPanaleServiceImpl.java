package com.org.project.TrainTicketingManagement.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.project.TrainTicketingManagement.domain.Carriages;
import com.org.project.TrainTicketingManagement.domain.Client;
import com.org.project.TrainTicketingManagement.domain.Station;
import com.org.project.TrainTicketingManagement.domain.Train;
import com.org.project.TrainTicketingManagement.domain.TrainClasses;
import com.org.project.TrainTicketingManagement.domain.TrainLongPriceses;
import com.org.project.TrainTicketingManagement.domain.TrainSchedule;
import com.org.project.TrainTicketingManagement.repository.ClientRepository;
import com.org.project.TrainTicketingManagement.repository.CustomerPanelRepository;
import com.org.project.TrainTicketingManagement.repository.StationRepository;
import com.org.project.TrainTicketingManagement.repository.TrainCarriagesRepository;
import com.org.project.TrainTicketingManagement.repository.TrainClassesRepository;
import com.org.project.TrainTicketingManagement.repository.TrainLongPricesesRepository;
import com.org.project.TrainTicketingManagement.repository.TrainRepository;
import com.org.project.TrainTicketingManagement.service.AdminService;

@Service
public class AdminPanaleServiceImpl implements AdminService{
	
	@Autowired
	StationRepository stationRepository;
	
	@Autowired
	TrainRepository trainRepository;
	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	TrainClassesRepository trainClassesRepository;
	
	@Autowired
	TrainCarriagesRepository trainCarriagesRepository;
	
	@Autowired
	CustomerPanelRepository customerPanelRepository;
	
	@Autowired
	TrainLongPricesesRepository trainLongPricesesRepository;

	@Override
	public Station saveStation(Station station) {
		return stationRepository.save(station);
	}

	@Override
	public List<Station> getAllStations() {
		return stationRepository.findAll();
	}

	@Override
	public void deleteStationById(Long id) {
		//stationRepository.deleteStationById(id);	
		stationRepository.deleteById(id);
	}

	@Override
	public List<Train> getAllTrains() {
		return trainRepository.findAll();
	}

	@Override
	public Train saveTrain(Train train) {
		return trainRepository.save(train);		
	}

	@Override
	public void deleteTrainById(Long id) {
		trainRepository.deleteById(id);		
	}

	@Override
	public List<Client> getAllClient() {
		return clientRepository.findAll();
	}

	@Override
	public List<TrainClasses> getAllTrainClasses() {
		return trainClassesRepository.findAll();
	}

	@Override
	public TrainClasses saveTrainClass(TrainClasses trainClasses) {
		return trainClassesRepository.save(trainClasses);
	}

	@Override
	public void deleteTrainClassById(Long id) {
		trainClassesRepository.deleteById(id);
	}

	@Override
	public List<Carriages> getAllTrainCabins() {
		return trainCarriagesRepository.findAll();
	}

	@Override
	public Carriages saveTrainCabin(Carriages trainCabins) {
		return trainCarriagesRepository.save(trainCabins);
	}

	@Override
	public void deleteTrainCabinById(Long id) {
		trainCarriagesRepository.deleteById(id);
	}

	@Override
	public List<TrainSchedule> getAllSchedulesAfterDate(Date date) {
		return customerPanelRepository.findTrainScheduleByScheduleDateAndStatus(date);
	}

	@Override
	public TrainSchedule saveTrainSchedule(TrainSchedule trainSchedule) {
		return customerPanelRepository.save(trainSchedule);
	}

	@Override
	public void deleteScheduleById(Long id) {
		customerPanelRepository.deleteById(id);
	}

	@Override
	public List<TrainLongPriceses> getAllPricess() {
		return trainLongPricesesRepository.findAll();
	}

	@Override
	public TrainLongPriceses saveJourneyPrices(TrainLongPriceses trainLongPriceses) {
		return trainLongPricesesRepository.save(trainLongPriceses);
	}

}
