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
	private int carriages;
	private int seats;
	private String trainType;
	@OneToMany(cascade= CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinTable(name="TRAINCLASSES", 
				joinColumns= {@JoinColumn(name="trainId", referencedColumnName="trainId")}, 
				inverseJoinColumns= {@JoinColumn(name="TRAIN_CLASS_ID",referencedColumnName="trainClassid")}) 
	private List<TrainClasses> availableClasses;
	private String trainNo;
	private String basis;
	
	

}
