package com.vaccnow.covidvaccination.dto;

import java.time.LocalDateTime;

import com.vaccnow.covidvaccination.constants.PaymentStatus;

import lombok.Data;

@Data
public class AppointmentDTO {

	private int id;

	private String userEmail;

	private BranchDTO branch;

	private VaccineDTO vaccine;

	private LocalDateTime slotDate;

	private String paymentMethod;

	private PaymentStatus paymentStatus;

	private boolean isVaccinated;

	private long version;

}