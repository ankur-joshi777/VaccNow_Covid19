package com.vaccnow.covidvaccination.service;

import com.vaccnow.covidvaccination.dto.BranchDTO;
import com.vaccnow.covidvaccination.dto.TimeSlotDTO;
import com.vaccnow.covidvaccination.response.ResponseBody;

public interface BranchService {

	ResponseBody<BranchDTO> getAllBranches(int pageNumber, int pageSize);

	ResponseBody<TimeSlotDTO> getAvailableTimesForBranch(Integer branchId);

}
