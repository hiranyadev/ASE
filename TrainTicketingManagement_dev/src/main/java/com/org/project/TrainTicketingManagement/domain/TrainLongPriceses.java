package com.org.project.TrainTicketingManagement.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="LONGPRICESES")
public class TrainLongPriceses {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long longPriceId;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public Station fromStation;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public Station toStation;
	public double price;
	public double longForJourny;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date effectiveDate;

}
