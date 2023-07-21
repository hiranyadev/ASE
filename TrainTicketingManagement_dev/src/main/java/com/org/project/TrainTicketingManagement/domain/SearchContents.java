package com.org.project.TrainTicketingManagement.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchContents {
	
	private Station departureStation;
	private Station destinationStation;
	private String departureDate;

}
