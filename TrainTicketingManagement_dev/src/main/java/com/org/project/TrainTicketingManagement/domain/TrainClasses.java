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
@Table(name="TRAINCLASS")
public class TrainClasses extends Trace{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int trainClassid;
	private String code;
	private String description;

}
