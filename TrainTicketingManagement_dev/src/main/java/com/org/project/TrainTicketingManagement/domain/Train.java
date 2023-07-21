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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="TRAIN")
public class Train {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long trainId;
	private String trainName;
	private int carriagesNo;
	private int seats;
	private String trainType;
	private String trainNo;
	private String basis;
	
	@OneToMany(mappedBy = "train", cascade = CascadeType.ALL)
	private List<Carriages> carriages;
	
	

}
