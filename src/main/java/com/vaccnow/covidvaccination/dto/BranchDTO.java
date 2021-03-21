package com.vaccnow.covidvaccination.dto;

import java.time.LocalTime;
import java.util.List;

import lombok.Data;

@Data
public class BranchDTO {

	private int id;

	private String name;

	private LocalTime timeFrom;

	private LocalTime timeTo;

	private List<VaccineDTO> vaccines;

	}
