package com.vaccnow.covidvaccination.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.vaccnow.covidvaccination.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

	@Override
	public void sendEmail(String emailId, String content) {
		LOGGER.info("email sent to {} with content {}", emailId, content);
	}

}
