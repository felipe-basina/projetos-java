package spring.boot.mvc.jpa.weblogic.controller;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import spring.boot.mvc.jpa.weblogic.repository.UsuarioRepository;

@Controller
public class AjaxController {

	private static Logger log = LoggerFactory.getLogger(AjaxController.class);

	@Autowired
	private EntityManager em;

	@Autowired
	private UsuarioRepository usrRepository;
	
	static {
		log.debug("\n\n Log inicializado! \n\n");
	}

	@RequestMapping(value = "/ajax-request", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getLastId() {
		Long lastId = 0L;
		try {
			lastId = usrRepository.getLastId();
		} catch (Exception ex) {
			log.info("\n".concat(ex.getMessage()), ex);
		}

		if (lastId == null) {
			lastId = 0L;
		}
		return String.valueOf(lastId);
	}

	@RequestMapping(value = "/ajax-request2", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getLastIdd(@RequestParam(value="vTest", defaultValue="default-value") String param) {
		log.info("\n\n\tparameter-value -> ".concat(param));
		Long lastId = -1L;
		try {
			Query consulta = em.createNamedQuery("Usuario.getLastIdd");
			Object temp = consulta.getSingleResult();
			if (temp != null) {
				lastId = ((Long) temp).longValue();
			}
		} catch (Exception ex) {
			log.info("\nErro:".concat(ex.getMessage()), ex);
		}
		return String.valueOf(lastId);
	}
}