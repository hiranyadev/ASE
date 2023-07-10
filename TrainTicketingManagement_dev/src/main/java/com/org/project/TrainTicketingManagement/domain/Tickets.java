package com.org.project.TrainTicketingManagement.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name="TICKETS")
public class Tickets extends Trace{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ticketId;
	private int numOfSeats;
	private double price;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CLIENTID", nullable=false)
	private Client client;
	
	private String departureDate;
	private String ReturnDate;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Station departureStation;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Station destinationStation;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Station ReturndepartureStation;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Station ReturndestinationStation;
	
	@ManyToMany(cascade= CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinTable(name="DEPART_TRAINS", 
				joinColumns= {@JoinColumn(name="ticketId", referencedColumnName="ticketId")}, 
				inverseJoinColumns= {@JoinColumn(name="DEPART_TRAIN_ID",referencedColumnName="trainId")}) 
	private List<Train> departTrains;
	
	@ManyToMany(cascade= CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinTable(name="RETURN_TRAINS", 
				joinColumns= {@JoinColumn(name="ticketId", referencedColumnName="ticketId")}, 
				inverseJoinColumns= {@JoinColumn(name="RETURN_TRAIN_ID",referencedColumnName="trainId")}) 
	private List<Train> returnTrains;
	private boolean isCancelled;
	private String cancelationStatus;
	

}
