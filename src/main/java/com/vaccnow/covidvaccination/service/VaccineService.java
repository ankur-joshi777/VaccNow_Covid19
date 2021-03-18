package com.vaccnow.covidvaccination.service;

import com.vaccnow.covidvaccination.dto.VaccineDTO;
import com.vaccnow.covidvaccination.response.ResponseBody;

public interface VaccineService {

	ResponseBody<VaccineDTO> getAllVaccinesForBranch(int branchId, int pageNumber, int pageSize);

}
