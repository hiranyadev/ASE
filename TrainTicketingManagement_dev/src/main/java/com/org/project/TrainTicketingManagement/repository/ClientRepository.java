package com.org.project.TrainTicketingManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.project.TrainTicketingManagement.domain.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
	
	public boolean existsByEmail(String email);

	public Client findByUsername(String username);

	public Client findByEmail(String username);

}
