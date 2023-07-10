package com.org.project.TrainTicketingManagement.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.org.project.TrainTicketingManagement.domain.TrainSchedule;
import com.org.project.TrainTicketingManagement.repository.CustomerPanelRepository;
import com.org.project.TrainTicketingManagement.service.CustomerPanelService;

@Service
public class CustomerPanelServiceImpl implements CustomerPanelService{
	
	public CustomerPanelRepository customerPanelRepository;

	@Override
	public List<TrainSchedule> getAvailableTrain(Date date) {
		return customerPanelRepository.findByscheduleDate(date);
	}

}
