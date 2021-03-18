package com.vaccnow.covidvaccination.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.vaccnow.covidvaccination.dto.AppointmentDTO;
import com.vaccnow.covidvaccination.response.ResponseBody;

public interface AppointmentService {

	boolean getSpecificAvailabilitybyBranchIdAndDate(Integer branchId, LocalDateTime slotDate);

	ResponseBody<AppointmentDTO> scheduleVaccination(AppointmentDTO appointmentDTO);

	ResponseBody<AppointmentDTO> getAppliedVaccinationByBranch(Integer branchId);

	ResponseBody<AppointmentDTO> getAppliedVaccinationPerDay(LocalDate startDate, LocalDate endDate);

	ResponseBody<AppointmentDTO> getConfirmedVaccinations(LocalDate startDate, LocalDate endDate);

}
