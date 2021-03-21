package com.vaccnow.covidvaccination.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "VACCINE")
@Data
@EqualsAndHashCode(callSuper=false)
public class Vaccine extends Auditable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private String manufacturer;

	@ManyToMany(mappedBy = "vaccines")
	private List<Branch> branches;


}
