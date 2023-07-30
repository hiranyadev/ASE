package com.org.project.TrainTicketingManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.org.project.TrainTicketingManagement.domain.Client;
import com.org.project.TrainTicketingManagement.domain.Tickets;

@Repository
public interface TicketRepository extends JpaRepository<Tickets, Long>{

	@Query("SELECT tkt FROM Tickets tkt WHERE tkt.client = ?1 ")
	List<Tickets> findTicketsByClient(Client client);

}
