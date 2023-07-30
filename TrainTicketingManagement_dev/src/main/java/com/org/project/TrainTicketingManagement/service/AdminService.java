package com.org.project.TrainTicketingManagement.service;

import java.util.Date;
import java.util.List;

import com.org.project.TrainTicketingManagement.domain.Carriages;
import com.org.project.TrainTicketingManagement.domain.Client;
import com.org.project.TrainTicketingManagement.domain.Station;
import com.org.project.TrainTicketingManagement.domain.Train;
import com.org.project.TrainTicketingManagement.domain.TrainClasses;
import com.org.project.TrainTicketingManagement.domain.TrainLongPriceses;
import com.org.project.TrainTicketingManagement.domain.TrainSchedule;

public interface AdminService {

	Station saveStation(Station station);

	List<Station> getAllStations();

	void deleteStationById(Long id);

	List<Train> getAllTrains();

	Train saveTrain(Train train);

	void deleteTrainById(Long id);

	List<Client> getAllClient();

	List<TrainClasses> getAllTrainClasses();

	TrainClasses saveTrainClass(TrainClasses trainClasses);

	void deleteTrainClassById(Long id);

	List<Carriages> getAllTrainCabins();

	Carriages saveTrainCabin(Carriages trainCabins);

	void deleteTrainCabinById(Long id);

	List<TrainSchedule> getAllSchedulesAfterDate(Date date);

	TrainSchedule saveTrainSchedule(TrainSchedule trainSchedule);

	void deleteScheduleById(Long id);

	List<TrainLongPriceses> getAllPricess();

	TrainLongPriceses saveJourneyPrices(TrainLongPriceses trainLongPriceses);

}
