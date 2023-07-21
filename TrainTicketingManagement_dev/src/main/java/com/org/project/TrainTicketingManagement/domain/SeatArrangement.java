package com.org.project.TrainTicketingManagement.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="SEATS_ARRANGEMENT")
public class SeatArrangement {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long seatArrId;
	private int seatNo;
	
	@ManyToOne(cascade= CascadeType.ALL,fetch=FetchType.LAZY)
	private TrainClasses classType;
	
	@ManyToOne(cascade= CascadeType.ALL,fetch=FetchType.LAZY)
	private TrainSchedule trainSchedule;

}
