package com.vaccnow.covidvaccination.service.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.vaccnow.covidvaccination.model.Appointment;
import com.vaccnow.covidvaccination.service.CertificationGenerationService;

@Service
public class CertificationGenerationServiceImpl implements CertificationGenerationService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CertificationGenerationServiceImpl.class);

	public String generateVaccineCertificate(Appointment appointment) {
		String filePath = "Vaccination_Certificate_" + appointment.getSlotDate().toString() + ".pdf";
		String message = "Dear " + appointment.getUserEmail() + ",\n\n"
				+ "This is to certify that you have successfully vaccinated " + " on "
				+ appointment.getSlotDate().toString();
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream(filePath));
			document.open();
			Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, BaseColor.BLACK);
			Chunk chunk = new Chunk("Vaccine Certificate", font);
			document.add(chunk);
			Paragraph paragraph = new Paragraph(message);
			document.add(paragraph);
			document.close();
		} catch (FileNotFoundException | DocumentException ex) {
			LOGGER.error("Exception while generating pdf :: ", ex);
		}
		return filePath;
	}

}
