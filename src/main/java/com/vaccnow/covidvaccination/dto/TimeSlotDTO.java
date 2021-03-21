package com.vaccnow.covidvaccination.dto;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class TimeSlotDTO {

	public TimeSlotDTO(LocalTime slotTime) {
		this.slotTime = slotTime;
	}

	@JsonFormat(pattern = "HH-mm")
	private LocalTime slotTime;

	private Boolean available;


}