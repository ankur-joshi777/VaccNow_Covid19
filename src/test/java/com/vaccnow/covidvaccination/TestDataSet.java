package com.vaccnow.covidvaccination;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.vaccnow.covidvaccination.dto.AppointmentDTO;
import com.vaccnow.covidvaccination.dto.BranchDTO;
import com.vaccnow.covidvaccination.dto.TimeSlotDTO;
import com.vaccnow.covidvaccination.dto.VaccineDTO;

public class TestDataSet {

	public static BranchDTO getBranchDTO() {
		BranchDTO branchDTO = new BranchDTO();
		branchDTO.setId(100);
		branchDTO.setName(UUID.randomUUID().toString());
		branchDTO.setTimeFrom(LocalTime.of(9, 0));
		branchDTO.setTimeFrom(LocalTime.of(18, 0));
		List<VaccineDTO> vaccineList = new ArrayList<>();
		vaccineList.add(getVaccineDTO());
		branchDTO.setVaccines(vaccineList);
		return branchDTO;
	}

	public static VaccineDTO getVaccineDTO() {
		VaccineDTO vaccineDto = new VaccineDTO();
		vaccineDto.setId(100);
		vaccineDto.setName(UUID.randomUUID().toString());
		return vaccineDto;
	}

	public static TimeSlotDTO getTimeSlotDTO() {
		TimeSlotDTO timeSlotDTO = new TimeSlotDTO(LocalTime.now());
		return timeSlotDTO;
	}

	public static AppointmentDTO getAppointmentDTO() {
		AppointmentDTO appointmentDTO = new AppointmentDTO();
		appointmentDTO.setBranch(getBranchDTO());
		appointmentDTO.setVaccine(getVaccineDTO());
		appointmentDTO.setId(1);
		appointmentDTO.setSlotDate(LocalDateTime.now());
		appointmentDTO.setUserEmail("test_email@xyz.com");
		return appointmentDTO;
	}
}
