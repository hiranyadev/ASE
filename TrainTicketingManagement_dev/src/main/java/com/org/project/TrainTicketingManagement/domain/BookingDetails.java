package com.org.project.TrainTicketingManagement.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name="BOOKINGDETAILS")
public class BookingDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long bookingId;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Client client;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private TrainSchedule shedule;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Tickets ticket;
	

}
