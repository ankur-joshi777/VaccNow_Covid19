package com.vaccnow.covidvaccination.dto;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TimeSlotDTO {

	public TimeSlotDTO(LocalTime slotTime) {
		this.slotTime = slotTime;
	}

	@JsonFormat(pattern = "HH-mm")
	private LocalTime slotTime;

	private Boolean available;

	public LocalTime getSlotTime() {
		return slotTime;
	}

	public void setSlotTime(LocalTime slotTime) {
		this.slotTime = slotTime;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

}