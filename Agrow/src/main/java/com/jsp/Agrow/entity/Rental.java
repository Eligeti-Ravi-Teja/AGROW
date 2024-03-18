package com.jsp.Agrow.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
@Entity
@Data
public class Rental {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	LocalDateTime startTime;
	
	LocalDateTime endTime;
	
	@ManyToOne
	Equipment equipments;
	@OneToOne
	PaymentHistory paymentDetails;
	
}
