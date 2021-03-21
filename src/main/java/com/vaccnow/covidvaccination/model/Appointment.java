package com.vaccnow.covidvaccination.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import com.vaccnow.covidvaccination.constants.PaymentStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "APPOINTMENTS")
@Data
@EqualsAndHashCode(callSuper=false)
public class Appointment extends Auditable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String userEmail;

	@ManyToOne
	private Branch branch;

	@ManyToOne
	private Vaccine vaccine;

	private LocalDateTime slotDate;

	private String paymentMethod;

	private PaymentStatus paymentStatus;

	private boolean isVaccinated;

	@Version
	private long version;

	}