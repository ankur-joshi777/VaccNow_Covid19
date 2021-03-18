package com.vaccnow.covidvaccination.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.vaccnow.covidvaccination.constants.Constants;
import com.vaccnow.covidvaccination.model.Appointment;
import com.vaccnow.covidvaccination.repository.AppointmentRepository;
import com.vaccnow.covidvaccination.service.CertificationGenerationService;
import com.vaccnow.covidvaccination.service.EmailService;

@Component
public class VaccinationScheduledJobUtil {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private CertificationGenerationService certificationGenerationService;

	@Autowired
	private EmailService emailService;

	@Scheduled(fixedRate = 15 * 60 * 1000) // 15mins
	public void completeScheduledVaccination() {
		List<Appointment> appointments = appointmentRepository
				.getOpenAppointments(LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT), LocalDateTime.now());

		appointments.stream().forEach(appointment -> {
			appointment.setVaccinated(true);
			appointmentRepository.save(appointment);

			emailService.sendEmail(appointment.getUserEmail(),
					String.format(Constants.EmailMessage.APPOINTMENT_COMPLETED, appointment.getBranch().getName(),
							appointment.getVaccine().getName(), appointment.getSlotDate()));
			certificationGenerationService.generateVaccineCertificate(appointment);
		});
	}

}
