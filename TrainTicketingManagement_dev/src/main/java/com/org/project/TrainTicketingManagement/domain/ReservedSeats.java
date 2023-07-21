package com.org.project.TrainTicketingManagement.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="RESERVEDSEATS")
public class ReservedSeats {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reservedSeatId;
	private String seatNo;
	
	@OneToOne
	private TrainSchedule trainShedule;
	
	@ManyToOne
	private Client client;
	
	@ManyToOne
	private Carriages carriage;

}
