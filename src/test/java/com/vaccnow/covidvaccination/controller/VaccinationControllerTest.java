package com.vaccnow.covidvaccination.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import com.vaccnow.covidvaccination.AbstractController;
import com.vaccnow.covidvaccination.TestDataSet;
import com.vaccnow.covidvaccination.dto.AppointmentDTO;
import com.vaccnow.covidvaccination.response.ResponseBody;
import com.vaccnow.covidvaccination.service.AppointmentService;

class VaccinationControllerTest extends AbstractController {

	@MockBean
	AppointmentService appointmentService;

	@Test
	void testScheduleVaccinationTimeslot() throws Exception {
		AppointmentDTO appointmentDTO = TestDataSet.getAppointmentDTO();
		List<AppointmentDTO> appointments = new ArrayList<>();
		appointments.add(appointmentDTO);
		Mockito.when(appointmentService.scheduleVaccination(Mockito.any()))
				.thenReturn(new ResponseBody<>(appointments));
		mockMvc.perform(
				post("/api/v1/vaccinations").content("{\"userEmail\": \"demo1234\",\"paymentMethod\": \"Credit\" }")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.data[0].userEmail").value(appointmentDTO.getUserEmail()));
	}

}
