package com.org.project.TrainTicketingManagement.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.org.project.TrainTicketingManagement.domain.Client;
import com.org.project.TrainTicketingManagement.repository.ClientRepository;
import com.org.project.TrainTicketingManagement.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public Client createClient(Client client) {
		client.setPassword(passwordEncoder.encode(client.getPassword()));
		client.setRole("ROLE_USER");
		clientRepository.save(client);
		return clientRepository.save(client);
	}

	public boolean checkclientAlreadyExistByEmail(String email) {
		return clientRepository.existsByEmail(email);
	}
}