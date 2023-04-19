package com.fintechapp.controller;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fintechapp.entities.TransactionEntity;
import com.fintechapp.repo.TransactionRepo;
import com.fintechapp.utils.PDFService;

@Controller

public class PDFController {
	
	     
	    @Autowired
	    TransactionRepo transactionRepository;
	 
	    @GetMapping(value = "/openpdf/statement", produces = MediaType.APPLICATION_PDF_VALUE)
	    public ResponseEntity<InputStreamResource> employeeReport(@RequestParam String accNumber)  throws IOException {
	        List<TransactionEntity> employees = (List<TransactionEntity>) transactionRepository.findByAccNumber(accNumber);
	 
	        ByteArrayInputStream bis = PDFService.employeePDFReport(employees);
	 
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Content-Disposition", "inline; filename=accountstatement"+accNumber+".pdf");
	 
	        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
	                .body(new InputStreamResource(bis));
	}

}
