package com.vaccnow.covidvaccination.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vaccnow.covidvaccination.constants.PaymentStatus;
import com.vaccnow.covidvaccination.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

	@Query("from Appointment where slotDate = :slotDate "
			+ "and branch.id = :branchId and paymentStatus in (:paymentStatus) ")
	Appointment isSlotAvailable(@Param(value = "branchId") Integer branchId,
			@Param(value = "slotDate") LocalDateTime slotDate,
			@Param(value = "paymentStatus") List<PaymentStatus> paymentStatus);

	@Query("from Appointment where slotDate between :startDate and :endDate and isVaccinated=1 ")
	List<Appointment> getOpenAppointments(@Param(value = "startDate") LocalDateTime startDate,
			@Param(value = "endDate") LocalDateTime endDate);

	@Modifying
	@Query("update Appointment set isVaccinated=1 where id = :id")
	void updateStatus(@Param(value = "id") Integer id);

	@Query("from Appointment where branch.id = :branchId")
	List<Appointment> findAllByBranchId(@Param(value = "branchId") Integer branchId);

	@Query("from Appointment where slotDate between :start and :end")
	List<Appointment> findAllByDay(@Param(value = "start") LocalDate start, @Param(value = "end") LocalDate end);

	@Query("from Appointment where isVaccinated=1 and slotDate between :start and :end")
	List<Appointment> findAllConfirmedByDay(@Param(value = "start") LocalDate start,
			@Param(value = "end") LocalDate end);

}
