package com.org.project.TrainTicketingManagement.service;

import java.util.Date;
import java.util.List;

import com.org.project.TrainTicketingManagement.domain.Carriages;
import com.org.project.TrainTicketingManagement.domain.SearchContents;
import com.org.project.TrainTicketingManagement.domain.Station;
import com.org.project.TrainTicketingManagement.domain.Train;
import com.org.project.TrainTicketingManagement.domain.TrainClasses;
import com.org.project.TrainTicketingManagement.domain.TrainSchedule;

public interface CustomerPanelService {

	List<TrainSchedule> getAllAvailableTrain();
	List<TrainSchedule> getAvailableTrain(SearchContents searchContents);
	List<Station> getAvailableStaions();
	TrainSchedule getTrainScheduleById(Long id);
	List<Carriages> getTrainClassesByTrainId(Train train);

}
