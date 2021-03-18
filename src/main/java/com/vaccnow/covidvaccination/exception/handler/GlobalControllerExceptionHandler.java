package com.vaccnow.covidvaccination.exception.handler;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import com.vaccnow.covidvaccination.constants.Constants;
import com.vaccnow.covidvaccination.constants.ErrorCodes;
import com.vaccnow.covidvaccination.response.ErrorResponse;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);
	private static final String GENERIC_ERROR_MESSAGE = "Something went wrong. Please try after sometime.";
	private static final String MANDATORY_ARGUMENT_MESSAGE = "Mandatory arguments are missing or invalid.";
	private static final String DB_ERROR_MESSAGE = "Data integrity was violated. The request was rejected.";
	private static final String DUPLICATE_KEY_MESSAGE = "Record already exists.";
	private static final String OPT_LOCK_ERROR_MESSAGE = "Requested data is stale. Kindly refresh.";

	private void populateStackTrace(ErrorResponse errorResponse, Throwable ex) {
		if (ex.getCause() != null) {
			final Map<String, Object> info = new HashMap<>();
			info.put("causeErrorClass", ex.getCause().getClass().getCanonicalName());
			info.put("causeErrorMessage", ex.getCause().getMessage());
			info.put("causeStackTrace", ex.getCause().getStackTrace());
			errorResponse.setInfo(info);
		}
	}

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public ResponseEntity<ErrorResponse> handleThrowable(Exception th) {
		LOGGER.error("unknown server exception", th);
		ErrorResponse response = new ErrorResponse();
		response.setErrorCode(ErrorCodes.SERVER_ERROR);
		response.setErrorMessage(GENERIC_ERROR_MESSAGE);
		response.setDetailErrorMessage(th.getMessage());
		populateStackTrace(response, th);
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException mave) {
		LOGGER.error(Constants.LOGGER_FORMAT, "MethodArgumentNotValidException", mave.getLocalizedMessage());
		ErrorResponse response = new ErrorResponse();
		response.setErrorCode(ErrorCodes.BAD_REQUEST);
		response.setErrorMessage(MANDATORY_ARGUMENT_MESSAGE);
		Map<String, Object> errors = new HashMap<>();
		mave.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		response.setInfo(errors);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ResponseStatusException.class)
	ResponseEntity<ErrorResponse> handleStatusException(ResponseStatusException ex, WebRequest request) {
		LOGGER.error(ex.getReason(), ex);
		ErrorResponse response = new ErrorResponse();
		response.setErrorMessage(ex.getReason());
		response.setErrorCode(ex.getStatus().getReasonPhrase());

		populateStackTrace(response, ex);
		return new ResponseEntity<>(response, ex.getStatus());
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseBody
	public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException diex) {
		LOGGER.error(Constants.LOGGER_FORMAT, "data integrity violation error", diex.getMessage());
		ErrorResponse response = new ErrorResponse();
		response.setErrorCode(ErrorCodes.CONSTRAINT_VIOLATION);
		response.setErrorMessage(DB_ERROR_MESSAGE);
		if (diex.getCause() instanceof ConstraintViolationException
				&& 2627 == ((ConstraintViolationException) diex.getCause()).getErrorCode()) {
			response.setErrorCode(ErrorCodes.DUPLICATE_KEY);
			response.setErrorMessage(DUPLICATE_KEY_MESSAGE);
		}
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ObjectOptimisticLockingFailureException.class)
	@ResponseBody
	public ResponseEntity<ErrorResponse> handleOptimisticEntityLockException(
			ObjectOptimisticLockingFailureException ofle) {
		LOGGER.error(Constants.LOGGER_FORMAT, "optimistic locking failed error", ofle.getMessage());
		ErrorResponse response = new ErrorResponse();
		response.setErrorCode(ErrorCodes.CONCURRENT_LOCK);
		response.setErrorMessage(OPT_LOCK_ERROR_MESSAGE);
		return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	}
}
