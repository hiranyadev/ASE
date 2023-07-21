package com.org.project.TrainTicketingManagement.service;

import com.org.project.TrainTicketingManagement.domain.Client;

public interface ClientService {
	
	public Client createClient(Client client);
	
	public boolean checkclientAlreadyExistByEmail(String email);

}
