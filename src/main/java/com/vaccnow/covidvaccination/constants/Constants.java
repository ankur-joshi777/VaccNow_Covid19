package com.vaccnow.covidvaccination.constants;

public class Constants {

	private Constants() {
	}

	public static final String API_V1 = "api/v1/";
	public static final String BRANCH_PATH = "branches";
	public static final String VACCINATION_PATH = "vaccinations";
	public static final String REPORTING_PATH = "reporting";
	public static final String LOGGER_FORMAT = "{}:{}";
	public static final int MINUTES_INTERVAL = 15;

	public static class EmailMessage {
		private EmailMessage() {
		}

		public static final String APPOINTMENT_DETAILS = "Details are as below:\n Branch name: [%s] \n Vaccine name: [%s] \n Date and Time: [%s]";
		public static final String APPOINTMENT_INITIATED = "Your appointment is initiated. " + APPOINTMENT_DETAILS;
		public static final String APPOINTMENT_CONFIRMED = "Your appointment is confirmed. " + APPOINTMENT_DETAILS;
		public static final String APPOINTMENT_COMPLETED = "Your appointment is completed. " + APPOINTMENT_DETAILS;
	}

}
