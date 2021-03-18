package com.vaccnow.covidvaccination.dto;

import java.time.LocalDateTime;

import com.vaccnow.covidvaccination.constants.PaymentStatus;

public class AppointmentDTO {

	private int id;

	private String userEmail;

	private BranchDTO branch;

	private VaccineDTO vaccine;

	private LocalDateTime slotDate;

	private String paymentMethod;

	private PaymentStatus paymentStatus;

	private boolean isVaccinated;

	private long version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public BranchDTO getBranch() {
		return branch;
	}

	public void setBranch(BranchDTO branch) {
		this.branch = branch;
	}

	public VaccineDTO getVaccine() {
		return vaccine;
	}

	public void setVaccine(VaccineDTO vaccine) {
		this.vaccine = vaccine;
	}

	public LocalDateTime getSlotDate() {
		return slotDate;
	}

	public void setSlotDate(LocalDateTime slotDate) {
		this.slotDate = slotDate;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public boolean isVaccinated() {
		return isVaccinated;
	}

	public void setVaccinated(boolean isVaccinated) {
		this.isVaccinated = isVaccinated;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

}