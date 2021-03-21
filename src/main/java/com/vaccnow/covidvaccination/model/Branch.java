package com.vaccnow.covidvaccination.model;

import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "VACCINATION_BRANCH")
@Data
@EqualsAndHashCode(callSuper=false)
public class Branch extends Auditable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private LocalTime timeFrom;

	private LocalTime timeTo;

	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinTable(name = "BRANCHES_AND_VACCINES", joinColumns = @JoinColumn(name = "branch_id"), inverseJoinColumns = @JoinColumn(name = "vaccine_id"))
	private List<Vaccine> vaccines;

	}
