package com.vaccnow.covidvaccination.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.vaccnow.covidvaccination.dto.VaccineDTO;
import com.vaccnow.covidvaccination.model.Vaccine;
import com.vaccnow.covidvaccination.repository.BranchRepository;
import com.vaccnow.covidvaccination.repository.VaccineRepository;
import com.vaccnow.covidvaccination.response.ResponseBody;
import com.vaccnow.covidvaccination.service.VaccineService;

@Service
public class VaccineServiceImpl implements VaccineService {

	@Autowired
	private BranchRepository branchRepository;

	@Autowired
	private VaccineRepository vaccineRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ResponseBody<VaccineDTO> getAllVaccinesForBranch(int branchId, int pageNumber, int pageSize) {
		if (!branchRepository.findById(branchId).isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					String.format("couldn't find branch with id [%s]", branchId));
		try {
			PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
			Page<Vaccine> vaccines = vaccineRepository.findByBranchesId(branchId, pageRequest);

			List<VaccineDTO> branchDtos = vaccines.stream().map(vaccine -> modelMapper.map(vaccine, VaccineDTO.class))
					.collect(Collectors.toList());

			return new ResponseBody<>(branchDtos, pageNumber, pageSize, vaccines.getTotalPages(),
					vaccines.getTotalElements());
		} catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					"error while fetching vaccines for branch");
		}
	}

}
