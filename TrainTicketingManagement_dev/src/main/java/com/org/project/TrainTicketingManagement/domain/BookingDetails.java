package com.org.project.TrainTicketingManagement.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

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
	private Long bookingId;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CLIENTID", nullable=false)
	private Client client;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private TrainSchedule shedule;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Tickets ticket;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date bookingDate;
	

}
