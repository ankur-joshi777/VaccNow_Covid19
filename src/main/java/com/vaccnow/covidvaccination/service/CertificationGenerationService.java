package com.vaccnow.covidvaccination.service;

import com.vaccnow.covidvaccination.model.Appointment;

public interface CertificationGenerationService {

	String generateVaccineCertificate(Appointment appointment);
}
