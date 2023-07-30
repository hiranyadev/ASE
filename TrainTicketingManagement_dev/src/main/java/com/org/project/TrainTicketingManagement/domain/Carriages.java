package com.org.project.TrainTicketingManagement.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name="carriages")
public class Carriages {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CARRIAGEID")
	private Long carriageId;
	private String carriageno;
	private int noOfSeats;
	
	@ManyToOne
	private Train train;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="trainClassid", nullable=false)
	private TrainClasses trainclass;
	
	@OneToOne(cascade = CascadeType.ALL)
	private TrainLongPriceses journeyprice;

}
