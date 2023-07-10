package com.org.project.TrainTicketingManagement.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.project.TrainTicketingManagement.domain.TrainSchedule;

@Repository
public interface CustomerPanelRepository extends JpaRepository<TrainSchedule, Long>{
	
	List<TrainSchedule> findByscheduleDate(Date date);
	
	

}
