package com.vaccnow.covidvaccination.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.vaccnow.covidvaccination.AbstractController;
import com.vaccnow.covidvaccination.TestDataSet;
import com.vaccnow.covidvaccination.dto.AppointmentDTO;
import com.vaccnow.covidvaccination.response.ResponseBody;
import com.vaccnow.covidvaccination.service.AppointmentService;

class ReportingControllerTest extends AbstractController {

	@MockBean
	AppointmentService appointmentService;

	@Test
	void testAppliedVaccinationPerBranch() throws Exception {
		AppointmentDTO appointmentDTO = TestDataSet.getAppointmentDTO();
		List<AppointmentDTO> appointments = new ArrayList<>();
		appointments.add(appointmentDTO);
		Mockito.when(appointmentService.getAppliedVaccinationByBranch(Mockito.any()))
				.thenReturn(new ResponseBody<>(appointments));
		mockMvc.perform(get("/api/v1/reportings/applied-vaccination-per-branch").param("branchId", "1"))
				.andExpect(status().isOk()).andExpect(jsonPath("$.data[0].id").value(appointmentDTO.getId()));

	}

	@Test
	void testAppliedVaccinationPerDay() throws Exception {
		AppointmentDTO appointmentDTO = TestDataSet.getAppointmentDTO();
		List<AppointmentDTO> appointments = new ArrayList<>();
		appointments.add(appointmentDTO);
		Mockito.when(appointmentService.getAppliedVaccinationPerDay(Mockito.any(), Mockito.any()))
				.thenReturn(new ResponseBody<>(appointments));
		mockMvc.perform(get("/api/v1/reportings/applied-vaccination-per-day")
				.param("startDate", LocalDateTime.now().toString()).param("endDate", LocalDateTime.now().toString()))
				.andExpect(status().isOk()).andExpect(jsonPath("$.data[0].id").value(appointmentDTO.getId()));

	}

	@Test
	void testConfirmedVaccinations() throws Exception {
		AppointmentDTO appointmentDTO = TestDataSet.getAppointmentDTO();
		List<AppointmentDTO> appointments = new ArrayList<>();
		appointments.add(appointmentDTO);
		Mockito.when(appointmentService.getConfirmedVaccinations(Mockito.any(), Mockito.any()))
				.thenReturn(new ResponseBody<>(appointments));
		mockMvc.perform(get("/api/v1/reportings/confirmed-vaccinations").param("startDate", LocalDateTime.now().toString())
				.param("endDate", LocalDateTime.now().toString())).andExpect(status().isOk())
				.andExpect(jsonPath("$.data[0].id").value(appointmentDTO.getId()));

	}
}
