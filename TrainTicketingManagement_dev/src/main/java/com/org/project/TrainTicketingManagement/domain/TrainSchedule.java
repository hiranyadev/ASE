package com.org.project.TrainTicketingManagement.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="TRAIN_SCHEDULE")
public class TrainSchedule extends Trace{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long scheduleId;
	@OneToOne(cascade=CascadeType.ALL)
	private Station departure;
	@OneToOne(cascade=CascadeType.ALL)
	private Station arrival;
	private String departTime;
	private String arrivalTime;	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn (name ="trainId")
	private Train train;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date scheduleDate;

}
