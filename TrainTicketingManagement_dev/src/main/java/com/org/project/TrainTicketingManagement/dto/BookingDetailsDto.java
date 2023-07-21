package com.org.project.TrainTicketingManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDetailsDto {
	
	private String trainName;
	private int bookdSeats;
	private String departurStation;
	private String destinationStation;
	private String trainNumber;
	private String trainClass;
	private String bookingDate;
	private String clientName;
	private String nic;

}
