package com.example.reservation;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestContext {

	@Bean(name = "reservationRepository")
	public ReservationRepository getReservationRepository() {
		return Mockito.mock(ReservationRepository.class);
	}
	
}