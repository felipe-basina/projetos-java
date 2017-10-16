package spring.boot.mvc.bootstrap.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {

	private static Logger log = LoggerFactory.getLogger(TestRestController.class);
	
	private static final String PREFIX_LOG = ".....";

	
	public TestRestController() {
	}
	
	@RequestMapping(value = { "/main" }, method = { RequestMethod.GET })
	@ResponseBody
	public String main() {
		log.debug(PREFIX_LOG.concat("[/main] -> main"));
		return "ok from main";
	}
	
	@RequestMapping(value = { "/dashboard" }, method = { RequestMethod.GET })
	@ResponseBody
	public String dashboards() {
		log.debug(PREFIX_LOG.concat("[/dashboard] -> dashboards"));
		return "ok from dashboards";
	}
	
	@RequestMapping(value = { "/shortcuts" }, method = { RequestMethod.GET })
	@ResponseBody
	public String shortcuts() {
		log.debug(PREFIX_LOG.concat("[/shortcuts] -> shortcuts"));
		return "ok from shortcuts";
	}
}