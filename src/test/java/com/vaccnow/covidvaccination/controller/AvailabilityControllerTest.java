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
import com.vaccnow.covidvaccination.dto.BranchDTO;
import com.vaccnow.covidvaccination.dto.TimeSlotDTO;
import com.vaccnow.covidvaccination.dto.VaccineDTO;
import com.vaccnow.covidvaccination.response.ResponseBody;
import com.vaccnow.covidvaccination.service.AppointmentService;
import com.vaccnow.covidvaccination.service.BranchService;
import com.vaccnow.covidvaccination.service.VaccineService;

class AvailabilityControllerTest extends AbstractController {

	@MockBean
	private BranchService branchService;

	@MockBean
	private VaccineService vaccineService;

	@MockBean
	AppointmentService appointmentService;

	@Test
	void testGetAllBranches() throws Exception {
		BranchDTO branch = TestDataSet.getBranchDTO();
		List<BranchDTO> branchList = new ArrayList<>();
		branchList.add(branch);
		Mockito.when(branchService.getAllBranches(Mockito.anyInt(), Mockito.anyInt()))
				.thenReturn(new ResponseBody<>(branchList, 1, 10, 1, 10));
		mockMvc.perform(get("/api/v1/branches")).andExpect(status().isOk())
				.andExpect(jsonPath("$.data[0].name").value(branch.getName()));
	}

	@Test
	void testGetAllVaccinesForBranch() throws Exception {
		VaccineDTO vaccine = TestDataSet.getVaccineDTO();
		List<VaccineDTO> vaccineList = new ArrayList<>();
		vaccineList.add(vaccine);
		Mockito.when(vaccineService.getAllVaccinesForBranch(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt()))
				.thenReturn(new ResponseBody<>(vaccineList, 1, 10, 1, 10));
		mockMvc.perform(get("/api/v1/branches/1/vaccines")).andExpect(status().isOk())
				.andExpect(jsonPath("$.data[0].name").value(vaccine.getName()));
	}

	@Test
	void testGetAvailableTimesForBranch() throws Exception {
		TimeSlotDTO timeSlotDTO = TestDataSet.getTimeSlotDTO();
		List<TimeSlotDTO> timeSlots = new ArrayList<>();
		timeSlots.add(timeSlotDTO);
		Mockito.when(branchService.getAvailableTimesForBranch(Mockito.anyInt()))
				.thenReturn(new ResponseBody<>(timeSlots));
		mockMvc.perform(get("/api/v1/branches/1/availability/all")).andExpect(status().isOk());
	}

	@Test
	void testGetSpecificAvailabilitybyBranchIdAndDate() throws Exception {
		Mockito.when(branchService.getSpecificAvailabilitybyBranchIdAndDate(Mockito.any(), Mockito.any()))
				.thenReturn(true);
		mockMvc.perform(
				get("/api/v1/branches/1/specific-availablity").param("slotDate", LocalDateTime.now().toString()))
				.andExpect(status().isOk());
	}

}
