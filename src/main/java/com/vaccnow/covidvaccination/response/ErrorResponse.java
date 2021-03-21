package com.vaccnow.covidvaccination.response;

import java.util.Map;

import lombok.Data;

@Data
public class ErrorResponse {

	private String errorCode;

	private String errorMessage;

	private String detailErrorMessage;

	private Map<String, Object> info;

}
