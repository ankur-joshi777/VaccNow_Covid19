package com.vaccnow.covidvaccination.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class AppointmentKey implements Serializable {

	private static final long serialVersionUID = 1L;

	private LocalDateTime slotDate;

	private Long branchId;

	private String userEmail;

}