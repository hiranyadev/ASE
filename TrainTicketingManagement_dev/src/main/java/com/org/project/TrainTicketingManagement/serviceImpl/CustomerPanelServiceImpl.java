package com.org.project.TrainTicketingManagement.serviceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.project.TrainTicketingManagement.domain.Carriages;
import com.org.project.TrainTicketingManagement.domain.SearchContents;
import com.org.project.TrainTicketingManagement.domain.Station;
import com.org.project.TrainTicketingManagement.domain.Train;
import com.org.project.TrainTicketingManagement.domain.TrainClasses;
import com.org.project.TrainTicketingManagement.domain.TrainSchedule;
import com.org.project.TrainTicketingManagement.repository.CustomerPanelRepository;
import com.org.project.TrainTicketingManagement.repository.StationRepository;
import com.org.project.TrainTicketingManagement.service.CustomerPanelService;

@Service
public class CustomerPanelServiceImpl implements CustomerPanelService{
	
	@Autowired
	public CustomerPanelRepository customerPanelRepository;
	
	@Autowired
	public StationRepository stationRepository;

	@Override
	public List<TrainSchedule> getAvailableTrain(SearchContents searchContents) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		return customerPanelRepository.findAll();
		//return customerPanelRepository.getSheduleDataByDepartureAndArrival(searchContents.getDepartureStation(), searchContents.getDestinationStation(), formatter.parse(searchContents.getDepartureDate()));
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

}
