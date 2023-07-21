package com.org.project.TrainTicketingManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.project.TrainTicketingManagement.domain.Station;

public interface StationRepository extends JpaRepository<Station, Long>{

}
