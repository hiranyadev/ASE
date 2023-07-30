package com.org.project.TrainTicketingManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.org.project.TrainTicketingManagement.domain.BookingDetails;
import com.org.project.TrainTicketingManagement.domain.Client;

@Repository
public interface BookingDetailsRepository extends JpaRepository<BookingDetails, Long>{

	@Query("SELECT bkn FROM BookingDetails bkn WHERE bkn.client = ?1")
	List<BookingDetails> findBookingDetailsByClient(Client client);

}
