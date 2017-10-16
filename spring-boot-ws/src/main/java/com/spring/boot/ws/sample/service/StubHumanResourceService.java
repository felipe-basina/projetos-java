package com.spring.boot.ws.sample.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class StubHumanResourceService implements HumanResourceService {

    private final Logger logger = LoggerFactory
	    .getLogger(StubHumanResourceService.class);

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    @Override
    public String bookHoliday(Date startDate, Date endDate, String name) {
	
	String message = "Booking holiday from [" + sdf.format(startDate) 
		+ " to " + sdf.format(endDate) 
		+ "] for [" + name + "] ";
	
	this.logger.info(message);
	
	return message;
    }

}