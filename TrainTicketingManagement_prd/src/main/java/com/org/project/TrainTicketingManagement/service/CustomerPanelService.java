package com.org.project.TrainTicketingManagement.service;

import java.util.Date;
import java.util.List;

import com.org.project.TrainTicketingManagement.domain.TrainSchedule;

public interface CustomerPanelService {

	List<TrainSchedule> getAvailableTrain(Date date);

}
