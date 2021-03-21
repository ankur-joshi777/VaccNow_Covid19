package com.vaccnow.covidvaccination;

import java.time.LocalDate;
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

import com.vaccnow.covidvaccination.constants.PaymentStatus;
import com.vaccnow.covidvaccination.model.Appointment;
import com.vaccnow.covidvaccination.model.Branch;
import com.vaccnow.covidvaccination.model.Vaccine;
import com.vaccnow.covidvaccination.repository.AppointmentRepository;
import com.vaccnow.covidvaccination.repository.BranchRepository;
import com.vaccnow.covidvaccination.repository.VaccineRepository;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
public class CovidVaccinationApplication implements CommandLineRunner {
	@Autowired
	BranchRepository branchRepository;
	@Autowired
	AppointmentRepository appointmentRepository;
	@Autowired
	VaccineRepository vaccineRepository;

	public static void main(String[] args) {
		SpringApplication.run(CovidVaccinationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Vaccine vaccine = new Vaccine();
		vaccine.setName("Vaccine_A");
		vaccine.setManufacturer("Manufacturer_X");

		Branch branch = new Branch();
		branch.setName("Branch_1");
		branch.setTimeFrom(LocalTime.of(9, 0));
		branch.setTimeTo(LocalTime.of(18, 0));
		List<Vaccine> vaccineList = new ArrayList<>();
		vaccineList.add(vaccine);
		branch.setVaccines(vaccineList);
		branchRepository.save(branch);

		Appointment appointment = new Appointment();
		appointment.setBranch(branch);
		appointment.setVaccine(vaccine);
		appointment.setSlotDate(LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 15)));
		appointment.setUserEmail("user1@mail.com");
		appointment.setPaymentStatus(PaymentStatus.INITIATED);
		appointment.setVaccinated(false);
		appointment.setPaymentMethod("Cash");
		appointmentRepository.save(appointment);

		Vaccine vaccine2 = new Vaccine();
		vaccine2.setName("Vaccine_B");
		vaccine2.setManufacturer("Manufacturer_X");
		Vaccine vaccine3 = new Vaccine();
		vaccine3.setName("Vaccine_C");
		vaccine3.setManufacturer("Manufacturer_Y");
		Branch branch2 = new Branch();
		branch2.setName("Branch_2");
		branch2.setTimeFrom(LocalTime.of(9, 0));
		branch2.setTimeTo(LocalTime.of(18, 0));
		List<Vaccine> vaccineList2 = new ArrayList<>();
		vaccineList2.add(vaccine2);
		vaccineList2.add(vaccine3);
		branch2.setVaccines(vaccineList2);
		branchRepository.save(branch2);

		Appointment appointment2 = new Appointment();
		appointment2.setBranch(branch2);
		appointment2.setVaccine(vaccine2);
		appointment2.setSlotDate(LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 30)));
		appointment2.setUserEmail("user2@mail.com");
		appointment2.setPaymentStatus(PaymentStatus.INITIATED);
		appointment2.setVaccinated(false);
		appointment2.setPaymentMethod("Credit");
		appointmentRepository.save(appointment2);

		Appointment appointment3 = new Appointment();
		appointment3.setBranch(branch2);
		appointment3.setVaccine(vaccine3);
		appointment3.setSlotDate(LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 00)));
		appointment3.setUserEmail("user3@mail.com");
		appointment3.setPaymentStatus(PaymentStatus.INITIATED);
		appointment3.setVaccinated(false);
		appointment3.setPaymentMethod("Credit");
		appointmentRepository.save(appointment3);
	}
}
