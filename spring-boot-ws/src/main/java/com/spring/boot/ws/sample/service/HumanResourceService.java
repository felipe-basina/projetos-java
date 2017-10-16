package com.spring.boot.ws.sample.service;

import java.util.Date;

public interface HumanResourceService {

    String bookHoliday(Date startDate, Date endDate, String name);

}