package com.example.reservation;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {
		SpringBootReservationApplication.class, TestContext.class })
@WebAppConfiguration
public class SpringBootReservationApplicationTests {

	private MockMvc mockMvc;
	
	private ReservationRepository reservationRepository;

	@Autowired
	private MessageSource message;
	
	@Before
	public void init() {
		reservationRepository = Mockito.mock(ReservationRepository.class);
		mockMvc = MockMvcBuilders.standaloneSetup(
				new ReservationController(reservationRepository, message)).build();
	}

	@Test
	public void initReservation() throws Exception {
		List<Reservation> reservations = new ArrayList<Reservation>();
		reservations.add(new Reservation("João"));
		reservations.add(new Reservation("Maria"));
		reservations.add(new Reservation("José"));

		Mockito.when(reservationRepository.findAll()).thenReturn(reservations);

		mockMvc.perform(get("/reservation/"))
				.andExpect(status().isOk())
				.andExpect(view().name("form"))
				.andExpect(model().attribute("reservations", hasSize(reservations.size())))
				.andExpect(
						model().attribute(
								"reservations",
								hasItem(hasProperty("name", is("João")))))
				.andExpect(
						model().attribute(
								"reservations",
								hasItem(hasProperty("name", is("Maria")))))
				.andExpect(
						model().attribute(
								"reservations",
								hasItem(hasProperty("name", is("José")))));

		verify(reservationRepository, times(1)).findAll();
		verifyNoMoreInteractions(reservationRepository);
	}

	@Test
	public void getAllReservations() throws Exception {
		List<Reservation> reservations = new ArrayList<Reservation>();
		reservations.add(new Reservation("João"));
		reservations.add(new Reservation("Maria"));
		reservations.add(new Reservation("José"));

		Mockito.when(reservationRepository.findAll()).thenReturn(reservations);

		mockMvc.perform(get("/reservation/all"))
				.andExpect(status().isOk())
				.andExpect(
						content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$[0].name").value("João"))
				.andExpect(jsonPath("$[1].name").value("Maria"))
				.andExpect(jsonPath("$[2].name").value("José"))
				.andExpect(jsonPath("$", hasSize(reservations.size())));

		verify(reservationRepository, times(1)).findAll();
		verifyNoMoreInteractions(reservationRepository);
	}
	
	@Test
	public void saveReservationNullOrEmptyParameter() throws Exception {
		mockMvc.perform(post("/reservation/save"))
				.andExpect(status().isOk())
				.andExpect(view().name("form"))
				.andExpect(
						model().attribute(ReservationController.ERRR_MESSAGE,
								message.getMessage("NotEmpty.msg", 
										null, Locale.getDefault())));
	}

	@Test
	public void saveReservationMinSizeParameter() throws Exception {
		mockMvc.perform(post("/reservation/save").param("name", "Fe"))
				.andExpect(status().isOk())
				.andExpect(view().name("form"))
				.andExpect(
						model().attribute(ReservationController.ERRR_MESSAGE,
								message.getMessage("Size.min.msg", 
										null, Locale.getDefault())));
	}

	// http://stackoverflow.com/questions/32253133/mock-objects-returns-null	
	@Test
	public void saveReservation() throws Exception {
		String name = "Oober";
		Long id = 4L;
		
		Reservation save = new Reservation(name);
		Reservation saved = save;
		saved.setId(id);
		
		mockMvc.perform(post("/reservation/save").param("name", name))
				.andExpect(status().isOk())
				.andExpect(view().name("form"))
				//.andExpect(model().attribute("reservation", is(saved)))
				.andExpect(
						model().attribute(ReservationController.SUCC_MESSAGE,
								message.getMessage("controller.reservation.save.ok", 
										null, Locale.getDefault())));
	}
	
	@Test
	public void deleteReservationNotFound() throws Exception {
		Long id = -1L;
		
		mockMvc.perform(get("/reservation/delete/{reservationId}", id))
				.andExpect(status().isOk())
				.andExpect(view().name("form"))
				.andExpect(
						model().attribute(ReservationController.ERRR_MESSAGE,
								message.getMessage("controller.reservation.delete.notfound", 
										new Object[] { id }, Locale.getDefault())));	
	}
	
	@Test
	public void deleteReservation() throws Exception {
		Long id = 3L;
		
		List<Reservation> reservations = new ArrayList<Reservation>();
		reservations.add(new Reservation("João"));
		reservations.add(new Reservation("Maria"));

		Reservation deleted = new Reservation("deleted");
		deleted.setId(id);
		
		Mockito.when(reservationRepository.findAll()).thenReturn(reservations);
		Mockito.when(reservationRepository.findOne(id)).thenReturn(deleted);
		
		mockMvc.perform(get("/reservation/delete/{reservationId}", 3))
				.andExpect(status().isOk())
				.andExpect(view().name("form"))
				.andExpect(model().attribute("reservations", hasSize(2)))
				.andExpect(
						model().attribute(ReservationController.SUCC_MESSAGE,
								message.getMessage("controller.reservation.delete.ok", 
										new Object[] { id }, Locale.getDefault())));	
	}

}