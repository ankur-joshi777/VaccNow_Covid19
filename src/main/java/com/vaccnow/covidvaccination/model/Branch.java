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

@Entity
@Table(name = "VACCINATION_BRANCH")
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalTime getTimeFrom() {
		return timeFrom;
	}

	public void setTimeFrom(LocalTime timeFrom) {
		this.timeFrom = timeFrom;
	}

	public LocalTime getTimeTo() {
		return timeTo;
	}

	public void setTimeTo(LocalTime timeTo) {
		this.timeTo = timeTo;
	}

	public List<Vaccine> getVaccines() {
		return vaccines;
	}

	public void setVaccines(List<Vaccine> vaccines) {
		this.vaccines = vaccines;
	}

}
