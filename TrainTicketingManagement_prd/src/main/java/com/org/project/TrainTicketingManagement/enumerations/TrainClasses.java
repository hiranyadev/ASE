package com.org.project.TrainTicketingManagement.enumerations;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum TrainClasses {
	
	OC(1,"OBS","Observation Saloon"),
	AC(2,"ACS","Air Conditioned Saloon"),
	SC(3,"SCR","Second Class Reserved Seats"),
	TC(4,"TCR","Third Class Reserved Seats");
	
	private int id;
	private String code;
	private String description;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
