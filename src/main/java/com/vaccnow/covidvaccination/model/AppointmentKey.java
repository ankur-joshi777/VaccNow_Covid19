package com.vaccnow.covidvaccination.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Embeddable;

@Embeddable
public class AppointmentKey implements Serializable {

	private static final long serialVersionUID = 1L;

	private LocalDateTime slotDate;

	private Long branchId;

	private String userEmail;

	public LocalDateTime getSlotDate() {
		return slotDate;
	}

	public void setSlotDate(LocalDateTime slotDate) {
		this.slotDate = slotDate;
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((branchId == null) ? 0 : branchId.hashCode());
		result = prime * result + ((slotDate == null) ? 0 : slotDate.hashCode());
		result = prime * result + ((userEmail == null) ? 0 : userEmail.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AppointmentKey other = (AppointmentKey) obj;
		if (branchId == null) {
			if (other.branchId != null)
				return false;
		} else if (!branchId.equals(other.branchId))
			return false;
		if (slotDate == null) {
			if (other.slotDate != null)
				return false;
		} else if (!slotDate.equals(other.slotDate))
			return false;
		if (userEmail == null) {
			if (other.userEmail != null)
				return false;
		} else if (!userEmail.equals(other.userEmail))
			return false;
		return true;
	}

}