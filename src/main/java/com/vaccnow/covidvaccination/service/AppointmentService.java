package com.vaccnow.covidvaccination.service;

import java.time.LocalDateTime;

import com.vaccnow.covidvaccination.dto.AppointmentDTO;
import com.vaccnow.covidvaccination.response.ResponseBody;

public interface AppointmentService {

	ResponseBody<AppointmentDTO> scheduleVaccination(AppointmentDTO appointmentDTO);

	ResponseBody<AppointmentDTO> getAppliedVaccinationByBranch(Integer branchId);

	ResponseBody<AppointmentDTO> getAppliedVaccinationPerDay(LocalDateTime startDate, LocalDateTime endDate);

	ResponseBody<AppointmentDTO> getConfirmedVaccinations(LocalDateTime startDate, LocalDateTime endDate);

}
