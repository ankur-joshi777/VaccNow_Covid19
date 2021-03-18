package com.vaccnow.covidvaccination.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vaccnow.covidvaccination.constants.Constants;
import com.vaccnow.covidvaccination.dto.AppointmentDTO;
import com.vaccnow.covidvaccination.response.ResponseBody;
import com.vaccnow.covidvaccination.service.AppointmentService;

@RestController
@RequestMapping(path = Constants.API_V1 + Constants.REPORTING_PATH)
public class ReportingController {

	@Autowired
	AppointmentService appointmentService;

	@GetMapping(value = "/applied-vaccination-per-branch")
	public ResponseEntity<ResponseBody<AppointmentDTO>> getAppliedVaccinationPerBranch(@RequestParam Integer branchId) {

		return ResponseEntity.ok(appointmentService.getAppliedVaccinationByBranch(branchId));
	}

	@GetMapping(value = { "/applied-vaccination-per-day" })
	public ResponseEntity<ResponseBody<AppointmentDTO>> getAppliedVaccinationPerday(
			@RequestParam(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
			@RequestParam(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

		return ResponseEntity.ok(appointmentService.getAppliedVaccinationPerDay(startDate, endDate));
	}

	@GetMapping(value = { "/confirmed-vaccinations" })
	public ResponseEntity<ResponseBody<AppointmentDTO>> getConfirmedVaccinations(
			@RequestParam(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
			@RequestParam(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

		return ResponseEntity.ok(appointmentService.getConfirmedVaccinations(startDate, endDate));

	}

}
