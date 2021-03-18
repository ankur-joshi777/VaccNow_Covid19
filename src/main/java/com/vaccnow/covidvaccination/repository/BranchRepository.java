package com.vaccnow.covidvaccination.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaccnow.covidvaccination.model.Branch;

public interface BranchRepository extends JpaRepository<Branch, Integer> {

}
