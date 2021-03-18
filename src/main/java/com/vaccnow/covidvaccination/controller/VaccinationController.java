package com.vaccnow.covidvaccination.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vaccnow.covidvaccination.constants.Constants;
import com.vaccnow.covidvaccination.dto.AppointmentDTO;
import com.vaccnow.covidvaccination.response.ResponseBody;
import com.vaccnow.covidvaccination.service.AppointmentService;

@RestController
@RequestMapping(path = Constants.API_V1 + Constants.VACCINATION_PATH)
public class VaccinationController {

	@Autowired
	private AppointmentService appointmentService;

	@PostMapping
	public ResponseEntity<ResponseBody<AppointmentDTO>> scheduleVaccination(
			@RequestBody AppointmentDTO appointmentDTO) {

		return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.scheduleVaccination(appointmentDTO));
	}

}
