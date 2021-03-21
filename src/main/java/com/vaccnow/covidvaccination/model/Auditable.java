package com.vaccnow.covidvaccination.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public class Auditable {

	@CreatedDate
	@Column(name = "createdOn", columnDefinition = "datetime", nullable = false)
	private LocalDate createdOn;

	@CreatedBy
	@Column(name = "createdBy", columnDefinition = "varchar(50)", nullable = true)
	private String createdBy;

	@LastModifiedDate
	@Column(name = "updatedOn", columnDefinition = "datetime", nullable = false)
	private LocalDate updatedOn;

	@Column(name = "updatedBy", columnDefinition = "varchar(50)", nullable = true)
	@LastModifiedBy
	private String updatedBy;


}