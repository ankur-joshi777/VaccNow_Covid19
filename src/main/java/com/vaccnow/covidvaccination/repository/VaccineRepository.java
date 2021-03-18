package com.vaccnow.covidvaccination.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vaccnow.covidvaccination.model.Vaccine;

public interface VaccineRepository extends JpaRepository<Vaccine, Integer> {

	Page<Vaccine> findByBranchesId(int branchId, Pageable pageable);

}
