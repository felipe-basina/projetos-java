package com.example.reservation;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.validation.BindingResult;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@SpringBootApplication
public class SpringBootReservationApplication extends
		SpringBootServletInitializer implements WebApplicationInitializer {

	private static Class<SpringBootReservationApplication> applicationClass = SpringBootReservationApplication.class;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootReservationApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}

}

class ReservationForm {

	@NotNull(message = "{NotEmpty.msg}")
	@NotEmpty(message = "{NotEmpty.msg}")
	@Size(min = 3, message = "{Size.min.msg}")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

@Component
class DummyData {

	@Bean
	protected CommandLineRunner initData(final ReservationRepository repository) {
		return new CommandLineRunner() {

			@Override
			public void run(String... arg0) throws Exception {
				for (String name : Arrays.asList("José", "João", "Maria")) {
					repository.save(new Reservation(name));
				}

				for (Reservation reservation : repository.findAll()) {
					System.out.println(reservation);
				}
			}
		};
	}
}

@RestController
@RequestMapping(value = "/reservation")
class ReservationController {

	public static final String ERRR_MESSAGE = "errrMessage";
	public static final String SUCC_MESSAGE = "succMessage";

	private ReservationRepository reservationRepository;

	private MessageSource message;

	@Autowired
	public ReservationController(ReservationRepository reservationRepository,
			MessageSource message) {
		this.reservationRepository = reservationRepository;
		this.message = message;
	}

	@RequestMapping(value = { "/", "/init" })
	public ModelAndView init(Map<String, Object> map) {
		map.put("reservations", this.getReservations());
		return new ModelAndView("form", map);
	}

	@RequestMapping(value = "/all")
	public List<Reservation> getReservations() {
		return (List<Reservation>) reservationRepository.findAll();
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody ModelAndView saveReservation(
			@Valid @ModelAttribute ReservationForm form, BindingResult result,
			Map<String, Object> map) {

		if (result.hasErrors()) {
			map.put(ERRR_MESSAGE, result.getAllErrors().get(0)
					.getDefaultMessage());
			map.put("reservations", this.getReservations());
			return new ModelAndView("form", map);
		}

		reservationRepository.save(new Reservation(form.getName()));

		map.put(SUCC_MESSAGE, message.getMessage(
				"controller.reservation.save.ok", null, Locale.getDefault()));
		map.put("reservations", this.getReservations());

		return new ModelAndView("form", map);
	}

	@RequestMapping(value = "/delete/{reservationId}")
	public @ResponseBody ModelAndView deleteReservation(
			@PathVariable Long reservationId, Map<String, Object> map) {

		Reservation reservation = null;
		try {
			reservation = reservationRepository.findOne(reservationId);
		} catch (Exception ex) {
			System.out.println(message.getMessage(
					"controller.reservation.delete.notfound",
					new Object[] { reservationId }, Locale.getDefault()));
		}

		if (reservation == null) {
			map.put(ERRR_MESSAGE, message.getMessage(
					"controller.reservation.delete.notfound",
					new Object[] { reservationId }, Locale.getDefault()));
		} else {
			try {
				reservationRepository.delete(reservationId);

				map.put(SUCC_MESSAGE, message.getMessage(
						"controller.reservation.delete.ok",
						new Object[] { reservationId }, Locale.getDefault()));
			} catch (Exception ex) {
				map.put(ERRR_MESSAGE,
						message.getMessage(
								"controller.reservation.delete.notfound",
								new Object[] { reservationId },
								Locale.getDefault())
								+ ":\n" + ex.getMessage());
			}
		}

		return this.init(map);
	}
}

@Configuration
@EnableWebMvc
class WebConfiguration extends WebMvcConfigurerAdapter {

	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/pages/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Bean
	public MessageSource messageSource() {
		String[] propriedades = { "classpath:config-messages", 
				"classpath:ValidationMessages" };

		ReloadableResourceBundleMessageSource reloadRes = new ReloadableResourceBundleMessageSource();
		reloadRes.setBasenames(propriedades);
		reloadRes.setDefaultEncoding("ISO-8859-1");
		return reloadRes;
	}
}

/*
 * @Entity class Reservation {
 * 
 * @Id
 * 
 * @GeneratedValue(strategy = GenerationType.AUTO) private Long id;
 * 
 * private String name;
 * 
 * public Reservation(String name) { super(); this.name = name; }
 * 
 * public Reservation() { super(); }
 * 
 * public Long getId() { return id; }
 * 
 * public void setId(Long id) { this.id = id; }
 * 
 * public String getName() { return name; }
 * 
 * public void setName(String name) { this.name = name; }
 * 
 * @Override public String toString() { StringBuilder builder = new
 * StringBuilder(); builder.append("Reservation [id="); builder.append(id);
 * builder.append(", name="); builder.append(name); builder.append("]"); return
 * builder.toString(); }
 * 
 * }
 */

@Repository
interface ReservationRepository extends JpaRepository<Reservation, Long> {

}