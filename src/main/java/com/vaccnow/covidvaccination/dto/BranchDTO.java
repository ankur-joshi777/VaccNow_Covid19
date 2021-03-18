package com.vaccnow.covidvaccination.dto;

import java.time.LocalTime;
import java.util.List;

public class BranchDTO {

	private int id;

	private String name;

	private LocalTime timeFrom;

	private LocalTime timeTo;

	private List<VaccineDTO> vaccines;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalTime getTimeFrom() {
		return timeFrom;
	}

	public void setTimeFrom(LocalTime timeFrom) {
		this.timeFrom = timeFrom;
	}

	public LocalTime getTimeTo() {
		return timeTo;
	}

	public void setTimeTo(LocalTime timeTo) {
		this.timeTo = timeTo;
	}

	public List<VaccineDTO> getVaccines() {
		return vaccines;
	}

	public void setVaccines(List<VaccineDTO> vaccines) {
		this.vaccines = vaccines;
	}

}
