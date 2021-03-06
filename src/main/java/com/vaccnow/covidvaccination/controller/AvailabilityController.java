package com.vaccnow.covidvaccination.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vaccnow.covidvaccination.constants.Constants;
import com.vaccnow.covidvaccination.dto.BranchDTO;
import com.vaccnow.covidvaccination.dto.TimeSlotDTO;
import com.vaccnow.covidvaccination.dto.VaccineDTO;
import com.vaccnow.covidvaccination.repository.BranchRepository;
import com.vaccnow.covidvaccination.response.ResponseBody;
import com.vaccnow.covidvaccination.service.BranchService;
import com.vaccnow.covidvaccination.service.VaccineService;

@RestController
@RequestMapping(path = Constants.API_V1 + Constants.BRANCH_PATH)
public class AvailabilityController {

	@Autowired
	BranchService branchService;

	@Autowired
	VaccineService vaccineService;

	@Autowired
	BranchRepository branchRepository;

	@GetMapping
	public ResponseEntity<ResponseBody<BranchDTO>> getAllBranches(
			@RequestParam(required = false, defaultValue = "0") int pageNumber,
			@RequestParam(required = false, defaultValue = "10") int pageSize) {

		return ResponseEntity.ok(branchService.getAllBranches(pageNumber, pageSize));
	}

	@GetMapping("/{branchId}/vaccines")
	public ResponseEntity<ResponseBody<VaccineDTO>> getAllVaccinesForBranch(@PathVariable Integer branchId,
			@RequestParam(required = false, defaultValue = "0") int pageNumber,
			@RequestParam(required = false, defaultValue = "10") int pageSize) {

		return ResponseEntity.ok(vaccineService.getAllVaccinesForBranch(branchId, pageNumber, pageSize));
	}

	@GetMapping("/{branchId}/availability/all")
	public ResponseEntity<ResponseBody<TimeSlotDTO>> getAvailableTimesForBranch(@PathVariable Integer branchId) {
		return ResponseEntity.ok(branchService.getAvailableTimesForBranch(branchId));
	}

	@GetMapping("{branchId}/specific-availablity")
	public ResponseEntity<Boolean> getSpecificAvailabilitybyBranchIdAndDate(@PathVariable Integer branchId,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime slotDate) {
		return ResponseEntity.ok(branchService.getSpecificAvailabilitybyBranchIdAndDate(branchId, slotDate));
	}

}
