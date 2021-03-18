package com.vaccnow.covidvaccination.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.vaccnow.covidvaccination.constants.Constants;
import com.vaccnow.covidvaccination.constants.PaymentStatus;
import com.vaccnow.covidvaccination.dto.AppointmentDTO;
import com.vaccnow.covidvaccination.model.Appointment;
import com.vaccnow.covidvaccination.model.Branch;
import com.vaccnow.covidvaccination.model.Vaccine;
import com.vaccnow.covidvaccination.repository.AppointmentRepository;
import com.vaccnow.covidvaccination.repository.BranchRepository;
import com.vaccnow.covidvaccination.repository.VaccineRepository;
import com.vaccnow.covidvaccination.response.ResponseBody;
import com.vaccnow.covidvaccination.service.AppointmentService;
import com.vaccnow.covidvaccination.service.EmailService;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private BranchRepository branchRepository;

	@Autowired
	private VaccineRepository vaccineRepository;

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private EmailService emailService;

	@Override
	public boolean getSpecificAvailabilitybyBranchIdAndDate(Integer branchId, LocalDateTime slotDate) {
		Appointment appointment = appointmentRepository.isSlotAvailable(branchId, slotDate,
				Arrays.asList(PaymentStatus.INITIATED, PaymentStatus.SUCCESS));

		return Objects.nonNull(appointment);
	}

	@Override
	public ResponseBody<AppointmentDTO> scheduleVaccination(AppointmentDTO appointmentDTO) {
		Optional<Branch> optionalBranch = branchRepository.findById(appointmentDTO.getBranch().getId());
		Optional<Vaccine> optionalVaccine = vaccineRepository.findById(appointmentDTO.getVaccine().getId());
		if (!(optionalBranch.isPresent() && optionalVaccine.isPresent())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "couldn't find branch id or vaccine id");
		} else if (getSpecificAvailabilitybyBranchIdAndDate(appointmentDTO.getBranch().getId(),
				appointmentDTO.getSlotDate())) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Slot Already Booked");
		}

		try {
			Appointment appointment = modelMapper.map(appointmentDTO, Appointment.class);
			appointment.setBranch(optionalBranch.get());
			appointment.setVaccine(optionalVaccine.get());
			appointment.setPaymentStatus(PaymentStatus.INITIATED);
			Appointment appointmentEntity = appointmentRepository.save(appointment);
			AppointmentDTO appointmentResponse = modelMapper.map(appointmentRepository.save(appointmentEntity),
					AppointmentDTO.class);

			emailService.sendEmail(appointment.getUserEmail(),
					String.format(Constants.EmailMessage.APPOINTMENT_INITIATED, appointment.getBranch().getName(),
							appointment.getVaccine().getName(), appointment.getSlotDate()));
			return new ResponseBody<>(Arrays.asList(appointmentResponse));
		} catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "error while scheduling vaccination");
		}
	}

	@Override
	public ResponseBody<AppointmentDTO> getAppliedVaccinationByBranch(Integer branchId) {
		try {
			List<Appointment> appointments = appointmentRepository.findAllByBranchId(branchId);
			return new ResponseBody<>(
					appointments.stream().map(apponitment -> modelMapper.map(apponitment, AppointmentDTO.class))
							.collect(Collectors.toList()));
		} catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					"error while fetching Schedule Vaccination By Branch");
		}
	}

	@Override
	public ResponseBody<AppointmentDTO> getAppliedVaccinationPerDay(LocalDate startDate, LocalDate endDate) {
		try {
			List<Appointment> appointments = appointmentRepository.findAllByDay(startDate, endDate);
			return new ResponseBody<>(
					appointments.stream().map(apponitment -> modelMapper.map(apponitment, AppointmentDTO.class))
							.collect(Collectors.toList()));
		} catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					"error while fetching Scheduled Vaccination Per Day");
		}
	}

	@Override
	public ResponseBody<AppointmentDTO> getConfirmedVaccinations(LocalDate startDate, LocalDate endDate) {
		try {
			List<Appointment> appointments = appointmentRepository.findAllConfirmedByDay(startDate, endDate);
			return new ResponseBody<>(
					appointments.stream().map(apponitment -> modelMapper.map(apponitment, AppointmentDTO.class))
							.collect(Collectors.toList()));
		} catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					"error while fetching confirmed vaccination over a period");
		}
	}
}
