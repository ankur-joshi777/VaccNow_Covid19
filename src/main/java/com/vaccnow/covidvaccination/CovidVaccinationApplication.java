package com.vaccnow.covidvaccination;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.vaccnow.covidvaccination.model.Appointment;
import com.vaccnow.covidvaccination.model.Branch;
import com.vaccnow.covidvaccination.model.Vaccine;
import com.vaccnow.covidvaccination.repository.AppointmentRepository;
import com.vaccnow.covidvaccination.repository.BranchRepository;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
public class CovidVaccinationApplication implements CommandLineRunner {
	@Autowired
	BranchRepository branchRepository;
	@Autowired
	AppointmentRepository appointmentRepository;

	public static void main(String[] args) {
		SpringApplication.run(CovidVaccinationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Vaccine vaccine = new Vaccine();
		vaccine.setName("Sample_Vaccine");
		vaccine.setManufacturer("Sample_Manufacturer");

		Branch branch = new Branch();
		branch.setName("Sample_Branch");
		branch.setTimeFrom(LocalTime.of(9, 0));
		branch.setTimeFrom(LocalTime.of(18, 0));
		List<Vaccine> vaccineList = new ArrayList<>();
		vaccineList.add(vaccine);
		branch.setVaccines(vaccineList);
		branchRepository.save(branch);

		Appointment appointment = new Appointment();
		appointment.setBranch(branch);
		appointment.setVaccine(vaccine);
		appointment.setSlotDate(LocalDateTime.now());
		appointment.setUserEmail("Sample_email@xyz.com");
		appointmentRepository.save(appointment);
	}
}
