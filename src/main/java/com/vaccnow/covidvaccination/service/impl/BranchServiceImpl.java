package com.vaccnow.covidvaccination.service.impl;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.vaccnow.covidvaccination.constants.Constants;
import com.vaccnow.covidvaccination.dto.BranchDTO;
import com.vaccnow.covidvaccination.dto.TimeSlotDTO;
import com.vaccnow.covidvaccination.model.Branch;
import com.vaccnow.covidvaccination.repository.BranchRepository;
import com.vaccnow.covidvaccination.response.ResponseBody;
import com.vaccnow.covidvaccination.service.BranchService;

@Service
public class BranchServiceImpl implements BranchService {

	@Autowired
	private BranchRepository branchRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ResponseBody<BranchDTO> getAllBranches(int pageNumber, int pageSize) {
		try {
			PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
			Page<Branch> vaccinationCentres = branchRepository.findAll(pageRequest);
			List<BranchDTO> branchDtos = vaccinationCentres.stream()
					.map(branch -> modelMapper.map(branch, BranchDTO.class)).collect(Collectors.toList());
			return new ResponseBody<>(branchDtos, pageNumber, pageSize, vaccinationCentres.getTotalPages(),
					vaccinationCentres.getTotalElements());
		} catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "error while fetching all branches");
		}
	}

	@Override
	public ResponseBody<TimeSlotDTO> getAvailableTimesForBranch(Integer branchId) {
		Optional<Branch> optionalBranch = branchRepository.findById(branchId);
		if (!optionalBranch.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					String.format("couldn't find branch with id [%s]", branchId));
		try {
			List<TimeSlotDTO> slots = new ArrayList<>();
//			BranchDTO branchDto = modelMapper.map(optionalBranch.get(), BranchDTO.class);
			LocalTime from = optionalBranch.get().getTimeFrom();
			LocalTime to = optionalBranch.get().getTimeTo();

			while (from.isBefore(to)) {
				slots.add(new TimeSlotDTO(from));
				from = from.plusMinutes(Constants.MINUTES_INTERVAL);
			}
			return new ResponseBody<>(slots);
		} catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					"error while fetching available times for branches");
		}
	}

}
